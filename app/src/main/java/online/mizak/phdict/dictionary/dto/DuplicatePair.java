package online.mizak.phdict.dictionary.dto;

public record DuplicatePair(
        CreateDictionaryProduct productToAdd,
        ProductDto existingProduct
) {
}
