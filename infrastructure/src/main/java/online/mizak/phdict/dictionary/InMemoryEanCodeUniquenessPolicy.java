package online.mizak.phdict.dictionary;

class InMemoryEanCodeUniquenessPolicy implements EanCodeUniquenessPolicy {

    private final ProductRepository productRepository;

    InMemoryEanCodeUniquenessPolicy(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isUnique(String eanCode) {
        return productRepository.findByEanCode(eanCode).isEmpty();
    }
}
