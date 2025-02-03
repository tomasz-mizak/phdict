package online.mizak.rplsupplier.dto;

public record ProductDto(
        String eanCode,
        String tradeName,
        String flyerURL
) {
}
