package org.api.stockmarket.engine.repository;

import org.api.stockmarket.engine.entity.MarketSingletonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketSingletonRepository extends JpaRepository<MarketSingletonEntity, Integer> {
}
