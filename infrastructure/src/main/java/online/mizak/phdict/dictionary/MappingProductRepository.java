package online.mizak.phdict.dictionary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class MappingProductRepository implements ProductRepository {

    private final JpaProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(JpaProduct.toJpaEntity(product)).fromJpaEntity();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(JpaProduct::fromJpaEntity).toList();
    }

    @Override
    public Long countAll() {
        return repository.count();
    }

    @Override
    public Optional<Product> findByEanCode(String eanCode) {
        return repository.findByEanCode(eanCode).map(JpaProduct::fromJpaEntity);
    }
}
