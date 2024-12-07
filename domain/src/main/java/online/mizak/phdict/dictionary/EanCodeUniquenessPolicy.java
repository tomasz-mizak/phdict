package online.mizak.phdict.dictionary;

interface EanCodeUniquenessPolicy {
    boolean isUnique(String eanCode);
}
