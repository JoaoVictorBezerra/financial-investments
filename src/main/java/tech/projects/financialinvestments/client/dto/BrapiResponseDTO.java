package tech.projects.financialinvestments.client.dto;

import java.util.List;

public record BrapiResponseDTO(
        List<StockDTO> results
) {
}
