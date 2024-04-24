package tech.projects.financialinvestments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.projects.financialinvestments.dto.CreateStockDTO;
import tech.projects.financialinvestments.service.StockService;

import java.net.URI;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createStockDTO) {
        String stockId = stockService.createStock(createStockDTO);
        return ResponseEntity.created(URI.create("/api/stocks/" + stockId)).build();
    }
}
