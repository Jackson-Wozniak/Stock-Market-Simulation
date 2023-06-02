package org.api.tradinggame.account.repository;

import org.api.tradinggame.account.model.entity.StockOwned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockOwnedRepository extends JpaRepository<StockOwned, String> {
}
