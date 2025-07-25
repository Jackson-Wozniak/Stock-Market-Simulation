package org.api.stockmarket.engine.repository;

import org.api.stockmarket.engine.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
