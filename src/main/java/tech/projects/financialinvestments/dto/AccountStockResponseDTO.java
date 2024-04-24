package tech.projects.financialinvestments.dto;

public record AccountStockResponseDTO(
        String stockId,
        Integer quantity,
         double total
) {
}
