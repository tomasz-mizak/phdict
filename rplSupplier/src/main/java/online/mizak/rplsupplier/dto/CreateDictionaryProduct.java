package online.mizak.rplsupplier.dto;

public record CreateDictionaryProduct(
        String eanCode,
        String tradeName,
        String flyerURL,
        String characteristicsURL
) {
}
