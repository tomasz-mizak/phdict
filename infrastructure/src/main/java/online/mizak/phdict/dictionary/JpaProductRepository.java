package online.mizak.phdict.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface JpaProductRepository extends JpaRepository<JpaProduct, Long> {
    Optional<JpaProduct> findByEanCode(String eanCode);
}
