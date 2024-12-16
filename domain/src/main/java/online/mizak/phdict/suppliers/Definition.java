package online.mizak.phdict.suppliers;

class Definition {

    private final Long id;
    private final String name;
    private final String description;
    private final String url;

    private final SupplyPolicy supplyPolicy;

    Definition(Long id, String name, String description, String url, SupplyPolicy supplyPolicy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.supplyPolicy = supplyPolicy;
    }

    Definition(Long id, String name, String description, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.supplyPolicy = null;
    }

}
