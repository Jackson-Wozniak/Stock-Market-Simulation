package org.api.stockmarket.modules.stocks.repository;

import org.api.stockmarket.modules.stocks.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
