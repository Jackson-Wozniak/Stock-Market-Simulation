package org.api.stockmarket.modules.stocks.repository;

import org.api.stockmarket.modules.stocks.entity.PricingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingModelRepository extends JpaRepository<PricingModel, Long> {
}
