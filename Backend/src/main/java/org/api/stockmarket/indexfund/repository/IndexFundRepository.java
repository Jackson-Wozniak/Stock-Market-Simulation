package org.api.stockmarket.indexfund.repository;

import org.api.stockmarket.indexfund.model.IndexFund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndexFundRepository extends JpaRepository<IndexFund, String> {
}
