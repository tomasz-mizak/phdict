package online.mizak.rplsupplier.dto;

public record RejectedProduct(
    CreateDictionaryProduct createDictionaryProduct,
    String reason
) {

}
