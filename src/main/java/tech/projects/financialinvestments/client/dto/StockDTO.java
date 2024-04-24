package tech.projects.financialinvestments.client.dto;

public record StockDTO(
        String currency,
        String shortName,
        String longName,
        double regularMarketPrice,
        String logourl,
        String symbol

) {
}
