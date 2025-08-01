package org.api.stockmarket.modules.stocks.repository;

import org.api.stockmarket.modules.stocks.entity.PriceRecord;
import org.api.stockmarket.modules.stocks.entity.idclass.StockPriceHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockPriceHistoryRepository extends JpaRepository<PriceRecord, StockPriceHistoryId> {

    @Modifying
    @Query(value = "truncate table stock_history", nativeQuery = true)
    void truncateTable();
}
