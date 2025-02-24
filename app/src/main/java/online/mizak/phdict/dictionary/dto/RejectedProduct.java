package online.mizak.phdict.dictionary.dto;

public record RejectedProduct(
    CreateDictionaryProduct createDictionaryProduct,
    String reason
) {

}
