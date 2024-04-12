package tech.projects.financialinvestments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.projects.financialinvestments.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
}
