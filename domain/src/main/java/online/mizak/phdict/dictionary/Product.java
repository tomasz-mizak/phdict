package online.mizak.phdict.dictionary;

import online.mizak.phdict.dictionary.dto.ProductDto;

class Product {
    private final Long id;
    private final String eanCode;
    private final String tradeName;
    private String flyerURL;

    Product(Long id, String eanCode, String tradeName, String flyerURL) {
        this.id = id;
        this.eanCode = eanCode;
        this.tradeName = tradeName;
        this.flyerURL = flyerURL;
    }

    static Product of(String eanCode, String tradeName, String flyerURL) {
        return new Product(null, eanCode, tradeName, flyerURL);
    }

    static Product of(String eanCode, String tradeName) {
        return of(eanCode, tradeName, null);
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

    ProductDto toDto() {
        return new ProductDto(eanCode, tradeName, flyerURL);
    }

    void updateFlyer(String flyerURL) {
        if (flyerURL == null || flyerURL.isBlank()) return;
        this.flyerURL = flyerURL;
    }
}
