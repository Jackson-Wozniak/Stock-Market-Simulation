package org.api.stockmarket.modules.news.repository;

import org.api.stockmarket.modules.news.entity.EarningsReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarningsRepository extends JpaRepository<EarningsReport, Long> {
}
