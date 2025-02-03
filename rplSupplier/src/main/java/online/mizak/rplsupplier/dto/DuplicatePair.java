package online.mizak.rplsupplier.dto;

public record DuplicatePair(
        CreateDictionaryProduct productToAdd,
        ProductDto existingProduct
) {
}
