package org.api.stockmarket.stocks.stock.repository;

import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.api.stockmarket.stocks.stock.model.entity.idclass.StockHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHistoryRepository extends JpaRepository<StockHistory, StockHistoryId> {
}
