package online.mizak.phdict.dictionary;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryProductRepository implements ProductRepository {

    private final ConcurrentHashMap<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Product save(Product product) {
        var id = product.isPersisted() ? product.getId() : idGenerator.incrementAndGet();
        Product persisted = new Product(id, product.getEanCode(), product.getTradeName(), product.getFlyerURL());
        store.put(persisted.getId(), persisted);
        return persisted;
    }

    @Override
    public void saveAll(List<Product> products) {
        products.forEach(product -> {
            var id = product.isPersisted() ? product.getId() : idGenerator.incrementAndGet();
            Product persisted = new Product(id, product.getEanCode(), product.getTradeName(), product.getFlyerURL());
            store.put(persisted.getId(), persisted);
        });
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(store.values());
    }

    @Override
    public Long countAll() {
        return (long) store.size();
    }

    @Override
    public Optional<Product> findByEanCode(String eanCode) {
        return store.values().stream()
                .filter(p -> p.getEanCode().equalsIgnoreCase(eanCode))
                .findFirst();
    }

}
