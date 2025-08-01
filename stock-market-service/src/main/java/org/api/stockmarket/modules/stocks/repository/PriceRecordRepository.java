package org.api.stockmarket.modules.stocks.repository;

import org.api.stockmarket.modules.stocks.entity.PriceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRecordRepository extends JpaRepository<PriceRecord, Long> {
}
