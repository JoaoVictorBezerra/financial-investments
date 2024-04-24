package tech.projects.financialinvestments.service;

import org.springframework.stereotype.Service;
import tech.projects.financialinvestments.dto.CreateStockDTO;
import tech.projects.financialinvestments.entity.Stock;
import tech.projects.financialinvestments.repository.StockRepository;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public String createStock(CreateStockDTO createStockDTO) {
        boolean stockAlreadyExists = verifyStockExistence(createStockDTO.stockId());
        if(stockAlreadyExists) {
            throw new RuntimeException("Stock already exists");
        }
        Stock stock = new Stock(createStockDTO.stockId(), createStockDTO.description());
        stockRepository.save(stock);
        return stock.getId();
    }

    public Stock findStockById(String stockId) {
        return stockRepository.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found."));
    }

    private boolean verifyStockExistence(String stockId) {
        return stockRepository.findById(stockId).isPresent();
    }
}
