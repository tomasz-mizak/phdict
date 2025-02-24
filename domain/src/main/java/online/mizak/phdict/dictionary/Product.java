package online.mizak.phdict.dictionary;

import online.mizak.phdict.dictionary.dto.ProductDto;

class Product {

    record Issuer(String value) {

    }

    private final Long id;
    private final String eanCode;
    private final String tradeName;
    private String flyerURL;
    private Issuer issuer;

    Product(Long id, String eanCode, String tradeName, String flyerURL, Issuer issuer) {
        this.id = id;
        this.eanCode = eanCode;
        this.tradeName = tradeName;
        this.flyerURL = flyerURL;
        this.issuer = issuer;
    }

    static Product of(String eanCode, String tradeName, String flyerURL, Issuer issuer) {
        return new Product(null, eanCode, tradeName, flyerURL, issuer);
    }

    static Product of(String eanCode, String tradeName, Issuer issuer) {
        return of(eanCode, tradeName, null, issuer);
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

    String getFlyerURL() { return flyerURL; }

    Issuer getIssuer() {
        return issuer;
    }

    ProductDto toDto() {
        return new ProductDto(eanCode, tradeName, flyerURL);
    }

    void updateFlyer(String flyerURL) {
        if (flyerURL == null || flyerURL.isBlank()) return;
        this.flyerURL = flyerURL;
    }
}
