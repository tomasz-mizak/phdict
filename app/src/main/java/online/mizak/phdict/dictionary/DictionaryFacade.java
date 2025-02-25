package online.mizak.phdict.dictionary;

import lombok.extern.slf4j.Slf4j;
import online.mizak.phdict.dictionary.dto.BulkImportReport;
import online.mizak.phdict.dictionary.dto.CreateDictionaryProduct;
import online.mizak.phdict.dictionary.dto.DuplicatePair;
import online.mizak.phdict.dictionary.dto.ProductDto;
import online.mizak.phdict.dictionary.dto.RejectedProduct;
import online.mizak.phdict.dictionary.exception.ConflictException;
import online.mizak.phdict.dictionary.exception.NotFoundException;
import online.mizak.phdict.utils.Color;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
public class DictionaryFacade {

    enum CreateProductExceptionType {
        ALREADY_EXISTS
    }

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
            throw new ConflictException("Cannot create new dictionary product, because product with eanCode '%s' already exists.".formatted(createDictionaryProduct.eanCode()), CreateProductExceptionType.ALREADY_EXISTS);
        }
        var newProductEntry = Product.of(createDictionaryProduct.eanCode(), createDictionaryProduct.tradeName(), createDictionaryProduct.flyerURL(), new Product.Issuer(issuer));
        productRepository.save(newProductEntry);
        log.info("New product created: {}", newProductEntry.toDto());
    }

    public BulkImportReport createDictionaryProducts(List<CreateDictionaryProduct> products, String issuer) {

        List<RejectedProduct> failedProducts = new ArrayList<>();
        List<DuplicatePair> duplicatePairs = new ArrayList<>();

        int savedProducts = 0;
        for (CreateDictionaryProduct createDictionaryProduct : products) {
            var optionalProduct = productRepository.findByEanCode(createDictionaryProduct.eanCode());
            if (optionalProduct.isPresent()) {
                String message = "Cannot create new dictionary product, because product with eanCode '%s' already exists.".formatted(createDictionaryProduct.eanCode());
                failedProducts.add(new RejectedProduct(createDictionaryProduct, message));
            } else {
                var newProductEntry = Product.of(createDictionaryProduct.eanCode(), createDictionaryProduct.tradeName(), createDictionaryProduct.flyerURL(), new Product.Issuer(issuer));
                productRepository.save(newProductEntry);
                savedProducts++;
            }
        }

        if (savedProducts > 0) log.info("Created {} bulk products.", savedProducts);

        var importDetails = new BulkImportReport(products.size(), savedProducts, failedProducts.size(), failedProducts, duplicatePairs);
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
