package org.api.stockmarket.market.repository;

import org.api.stockmarket.market.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
