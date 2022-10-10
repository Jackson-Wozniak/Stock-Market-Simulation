package org.api.stockmarket.indexfund.repository;

import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndexFundRepository extends JpaRepository<IndexFund, String> {
}
