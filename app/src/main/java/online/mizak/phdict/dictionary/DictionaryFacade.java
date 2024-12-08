package online.mizak.phdict.dictionary;

import online.mizak.phdict.dictionary.dto.ProductDto;

import java.util.List;

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

    public void createDictionaryProduct(String eanCode, String tradeName) {
        if (!eanCodeUniquenessPolicy.isUnique(eanCode)) throw new IllegalStateException("EAN code must be unique");
        var newProductEntry = Product.of(eanCode, tradeName);
        productRepository.save(newProductEntry);
    }

    public Long showProductsCount() {
        return productRepository.countAll();
    }

    public List<ProductDto> showAllProducts() {
        return productRepository.findAll().stream().map(Product::toDto).toList();
    }

    public ProductDto showDictionaryProductByEanCode(String eanCode) {
        return productRepository.findByEanCode(eanCode).map(Product::toDto).orElseThrow(() -> new IllegalStateException("Product by EAN code not found"));
    }

}
