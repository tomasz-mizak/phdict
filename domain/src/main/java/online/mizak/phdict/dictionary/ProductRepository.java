package online.mizak.phdict.dictionary;

import java.util.List;
import java.util.Optional;

interface ProductRepository {

    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findByEanCode(String eanCode);

}
