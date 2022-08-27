package stocktradingsimulator.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.account.model.entity.StockInventory;

public interface StockInventoryRepository extends JpaRepository<StockInventory, String> {
}
