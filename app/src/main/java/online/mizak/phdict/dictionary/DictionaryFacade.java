package online.mizak.phdict.dictionary;

import lombok.extern.slf4j.Slf4j;
import online.mizak.phdict.dictionary.dto.BulkImportReport;
import online.mizak.phdict.dictionary.dto.CreateDictionaryProduct;
import online.mizak.phdict.dictionary.dto.DuplicatePair;
import online.mizak.phdict.dictionary.dto.ProductDto;
import online.mizak.phdict.dictionary.dto.RejectedProduct;
import online.mizak.phdict.dictionary.exception.NotFoundException;
import online.mizak.phdict.utils.Color;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Slf4j
@Transactional
public class DictionaryFacade {

    private final ProductRepository productRepository;
    private final ImportReportRepository importReportRepository;

    DictionaryFacade(
            ProductRepository productRepository,
            ImportReportRepository importReportRepository
    ) {
        this.productRepository = productRepository;
        this.importReportRepository = importReportRepository;
    }

    // # - - - Product - - - # //

    public Long showProductsCount() {
        return productRepository.countAll();
    }

    public List<ProductDto> showAllProducts() {
        return productRepository.findAll().stream().map(Product::toDto).toList();
    }

    // todo, repo filter method
    public List<ProductDto> showAllProducts(String issuer) {
        return productRepository.findAll().stream().filter(p -> p.getIssuer().value().equals(issuer)).map(Product::toDto).toList();
    }

    public ProductDto showDictionaryProductByEanCode(String eanCode) {
        return productRepository.findByEanCode(eanCode).map(Product::toDto).orElseThrow(() -> new NotFoundException("Product by EAN code not found", ErrorCode.NO_PRODUCT_AVAILABLE));
    }

    public void createDictionaryProduct(CreateDictionaryProduct createDictionaryProduct, String issuer) {
        var optionalProduct = productRepository.findByEanCode(createDictionaryProduct.eanCode());
        if (optionalProduct.isPresent()) {
            var exisitingProduct = optionalProduct.get();
            exisitingProduct.updateFlyer(createDictionaryProduct.flyerURL());
        }
        var newProductEntry = Product.of(createDictionaryProduct.eanCode(), createDictionaryProduct.tradeName(), createDictionaryProduct.flyerURL(), new Product.Issuer(issuer));
        productRepository.save(newProductEntry);
        log.info("New product created: {}", newProductEntry.toDto());
    }

    public BulkImportReport createDictionaryProducts(List<CreateDictionaryProduct> products, String issuer) {
        List<Product> productsToSave = new ArrayList<>();
        List<RejectedProduct> failedProducts = new ArrayList<>();
        List<DuplicatePair> duplicatePairs = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            CreateDictionaryProduct productToCreate = products.get(i);
            try {

                // Persisted duplicate.
                var persistedProduct = productRepository.findByEanCode(productToCreate.eanCode());
                if (persistedProduct.isPresent()) {
                    var exisitingProduct = persistedProduct.get();
                    duplicatePairs.add(new DuplicatePair(productToCreate, exisitingProduct.toDto()));
                    throw new RuntimeException("Product with ID '%d' have identical eanCode '%s'.".formatted(exisitingProduct.getId(), productToCreate.eanCode()));
                }

                // Batch duplicate
                OptionalInt batchProductIndex = IntStream.range(0, productsToSave.size())
                        .filter(index -> productsToSave.get(index).getEanCode().equals(productToCreate.eanCode()))
                        .findFirst();
                if (batchProductIndex.isPresent()) {
                    var exisitingProduct = productsToSave.get(batchProductIndex.getAsInt());
                    duplicatePairs.add(new DuplicatePair(productToCreate, exisitingProduct.toDto()));
                    throw new RuntimeException("Product index #%d is duplicated in batch, on index #%d. ".formatted(i, batchProductIndex.getAsInt()));
                }

                var newProductEntry = Product.of(productToCreate.eanCode(), productToCreate.tradeName(), productToCreate.flyerURL(), new Product.Issuer(issuer));
                productsToSave.add(newProductEntry);

            } catch (Exception e) {
                log.warn(Color.yellowBold("{}"), e.getMessage());
                failedProducts.add(new RejectedProduct(productToCreate, e.getMessage()));
            }
        }
        productRepository.saveAll(productsToSave);
        if (!productsToSave.isEmpty()) log.info("Created {} bulk products.", productsToSave.size());
        var importDetails = new BulkImportReport(products.size(), productsToSave.size(), failedProducts.size(), failedProducts, duplicatePairs);
        if (!failedProducts.isEmpty() || !duplicatePairs.isEmpty()) {
            var importReport = ImportReport.bulkReport(importDetails);
            importReportRepository.save(importReport);
        }
        return importDetails;
    }

    // # - - - Import Report - - - #

    public List<BulkImportReport> showAllImportReports() {
        return importReportRepository.findAll().stream().map(e -> (BulkImportReport) e.getDetails()).toList();
    }

}
