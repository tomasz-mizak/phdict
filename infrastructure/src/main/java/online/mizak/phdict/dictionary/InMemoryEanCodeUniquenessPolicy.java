package online.mizak.phdict.dictionary;

class InMemoryEanCodeUniquenessPolicy implements EanCodeUniquenessPolicy {

    private final ProductRepository productRepository;

    InMemoryEanCodeUniquenessPolicy(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isUnique(String eanCode) {
        if (eanCode == null) throw new IllegalArgumentException("eanCode is null");
        return productRepository.findByEanCode(eanCode).isEmpty();
    }
}
