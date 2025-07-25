package org.api.stockmarket.stocks.news.repository;

import org.api.stockmarket.stocks.news.entity.EarningsReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarningsRepository extends JpaRepository<EarningsReport, Long> {
}
