package org.api.stockmarket.modules.stocks.repository;

import org.api.stockmarket.modules.stocks.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
