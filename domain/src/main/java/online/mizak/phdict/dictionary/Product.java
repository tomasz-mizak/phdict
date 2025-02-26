package online.mizak.phdict.dictionary;

import online.mizak.phdict.dictionary.dto.ProductDto;

import java.time.LocalDateTime;

class Product {

    record Issuer(String value) {


    }

    private final Long id;

    private final String eanCode;
    private final String tradeName;
    private final String flyerURL;
    private final String characteristicsURL;
    private final Issuer issuer;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Product(Long id, String eanCode, String tradeName, String flyerURL, String characteristicsURL, Issuer issuer) {
        this.id = id;
        this.eanCode = eanCode;
        this.tradeName = tradeName;
        this.flyerURL = flyerURL;
        this.characteristicsURL = characteristicsURL;
        this.issuer = issuer;
        this.createdAt = LocalDateTime.now();
    }

    Product(Long id, String eanCode, String tradeName, String flyerURL, String characteristicsURL, Issuer issuer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.eanCode = eanCode;
        this.tradeName = tradeName;
        this.flyerURL = flyerURL;
        this.characteristicsURL = characteristicsURL;
        this.issuer = issuer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    static Product of(String eanCode, String tradeName, String flyerURL, String characteristicsURL, Issuer issuer) {
        return new Product(null, eanCode, tradeName, flyerURL, characteristicsURL, issuer);
    }

    static Product of(String eanCode, String tradeName, Issuer issuer) {
        return of(eanCode, tradeName, null, null, issuer);
    }

    boolean isPersisted() {
        return this.id != null;
    }

    Long getId() {
        return this.id;
    }

    String getEanCode() {
        return eanCode;
    }

    String getTradeName() {
        return tradeName;
    }

    String getFlyerURL() {
        return flyerURL;
    }

    public String getCharacteristicsURL() {
        return characteristicsURL;
    }

    Issuer getIssuer() {
        return issuer;
    }

    LocalDateTime getCreatedAt() {
        return createdAt;
    }

    LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    ProductDto toDto() {
        return new ProductDto(eanCode, tradeName, flyerURL, characteristicsURL);
    }

}
