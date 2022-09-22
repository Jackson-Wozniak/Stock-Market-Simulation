package stocktradingsimulator.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.stock.model.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
}
