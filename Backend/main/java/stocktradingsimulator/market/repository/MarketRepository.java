package stocktradingsimulator.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.market.entity.Market;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
