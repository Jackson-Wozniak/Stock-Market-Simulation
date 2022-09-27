package org.api.tradinggame.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.api.tradinggame.account.model.entity.StockInventory;

public interface StockInventoryRepository extends JpaRepository<StockInventory, String> {
}
