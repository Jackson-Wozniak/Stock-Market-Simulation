package org.api.stockmarket.stocks.earnings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;

public interface EarningsRepository extends JpaRepository<EarningsReport, Long> {
}
