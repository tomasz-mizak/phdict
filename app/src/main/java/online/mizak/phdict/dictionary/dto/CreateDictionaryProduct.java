package online.mizak.phdict.dictionary.dto;

public record CreateDictionaryProduct(
        String eanCode,
        String tradeName,
        String flyerURL,
        String characteristicsURL
) {
}
