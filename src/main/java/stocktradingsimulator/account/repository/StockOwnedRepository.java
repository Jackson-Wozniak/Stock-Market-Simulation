package stocktradingsimulator.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.account.entity.StockOwned;

public interface StockOwnedRepository extends JpaRepository<StockOwned, String> {
}
