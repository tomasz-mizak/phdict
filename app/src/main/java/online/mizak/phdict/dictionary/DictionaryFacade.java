package online.mizak.phdict.dictionary;

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

}
