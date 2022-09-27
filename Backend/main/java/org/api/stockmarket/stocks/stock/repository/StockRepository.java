package org.api.stockmarket.stocks.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.api.stockmarket.stocks.stock.model.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
}
