package stocktradingsimulator.stocks.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.stocks.stock.model.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
}
