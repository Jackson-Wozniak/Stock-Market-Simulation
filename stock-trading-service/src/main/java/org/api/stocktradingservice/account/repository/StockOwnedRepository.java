package org.api.stocktradingservice.account.repository;

import org.api.stocktradingservice.account.model.entity.StockOwned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockOwnedRepository extends JpaRepository<StockOwned, String> {
}
