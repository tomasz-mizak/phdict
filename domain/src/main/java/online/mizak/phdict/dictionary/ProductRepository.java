package online.mizak.phdict.dictionary;

import java.util.List;
import java.util.Optional;

interface ProductRepository {

    Product save(Product product);
    void saveAll(List<Product> products);
    List<Product> findAll();
    Long countAll();
    Optional<Product> findByEanCode(String eanCode);

}
