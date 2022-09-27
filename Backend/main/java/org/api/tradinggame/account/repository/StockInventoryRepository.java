package org.api.tradinggame.account.repository;

import org.api.tradinggame.account.model.entity.StockInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockInventoryRepository extends JpaRepository<StockInventory, String> {
}
