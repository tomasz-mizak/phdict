package online.mizak.phdict.dictionary;

import lombok.extern.slf4j.Slf4j;
import online.mizak.phdict.dictionary.dto.CreateDictionaryProduct;
import online.mizak.phdict.dictionary.dto.ProductDto;
import online.mizak.phdict.dictionary.exception.NotAcceptableException;
import online.mizak.phdict.dictionary.exception.NotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
public class DictionaryFacade {

    private final ProductRepository productRepository;
    private final EanCodeUniquenessPolicy eanCodeUniquenessPolicy;

    DictionaryFacade(
            ProductRepository productRepository,
            EanCodeUniquenessPolicy eanCodeUniquenessPolicy
    ) {
        this.productRepository = productRepository;
        this.eanCodeUniquenessPolicy = eanCodeUniquenessPolicy;
    }

    public void createDictionaryProduct(CreateDictionaryProduct createDictionaryProduct) {
        if (!eanCodeUniquenessPolicy.isUnique(createDictionaryProduct.eanCode())) throw new NotAcceptableException("EAN code must be unique", ErrorCode.PRODUCT_ALREADY_EXISTS);
        var newProductEntry = Product.of(createDictionaryProduct.eanCode(), createDictionaryProduct.tradeName());
        productRepository.save(newProductEntry);
        log.info("New product created: {}", newProductEntry.toDto());
    }

//    @Async // todo, niech czeka z zwróceniem resulta
    public void createDictionaryProducts(List<CreateDictionaryProduct> products) {
        List<Product> productsToSave = new ArrayList<>();
        for (CreateDictionaryProduct product : products) {
            if (!eanCodeUniquenessPolicy.isUnique(product.eanCode())) continue;
            var newProductEntry = Product.of(product.eanCode(), product.tradeName());
            productsToSave.add(newProductEntry);
        }
        productRepository.saveAll(productsToSave);
        log.info("Created {} bulk products.", productsToSave.size());
        // todo, maybe return result info.
    }

    public Long showProductsCount() {
        return productRepository.countAll();
    }

    public List<ProductDto> showAllProducts() {
        return productRepository.findAll().stream().map(Product::toDto).toList();
    }

    public ProductDto showDictionaryProductByEanCode(String eanCode) {
        return productRepository.findByEanCode(eanCode).map(Product::toDto).orElseThrow(() -> new NotFoundException("Product by EAN code not found", ErrorCode.NO_PRODUCT_AVAILABLE));
    }

}
