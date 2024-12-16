package online.mizak.phdict.suppliers;

record SupplyPolicy(Frequency supplierFrequency, Integer supplierCount) {

    enum Frequency {
        DAILY, WEEKLY
    }

}
