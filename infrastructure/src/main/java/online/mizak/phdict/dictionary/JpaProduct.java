package online.mizak.phdict.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
class JpaProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String eanCode;

    private String tradeName;

    private String flyerURL;

    private String issuer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    static JpaProduct toJpaEntity(Product product) {
        return new JpaProduct(
                product.getId(),
                product.getEanCode(),
                product.getTradeName(),
                product.getFlyerURL(),
                product.getIssuer().value(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    Product fromJpaEntity() {
        return new Product(
                id,
                eanCode,
                tradeName,
                flyerURL,
                new Product.Issuer(issuer),
                createdAt,
                updatedAt
        );
    }

}
