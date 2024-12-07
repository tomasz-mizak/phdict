package online.mizak.phdict.dictionary;

class Product {
    private final Long id;
    private final String eanCode;
    private final String tradeName;

    Product(Long id, String eanCode, String tradeName) {
        this.id = id;
        this.eanCode = eanCode;
        this.tradeName = tradeName;
    }

    static Product of(String eanCode, String tradeName) {
        return new Product(null, eanCode, tradeName);
    }

    boolean isPersisted() {
        return this.id != null;
    }

    Long getId() {
        return this.id;
    }

    public String getEanCode() {
        return eanCode;
    }

    public String getTradeName() {
        return tradeName;
    }

}
