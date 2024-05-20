package org.api.stocktradingservice.account.repository;

import org.api.stocktradingservice.account.model.entity.AccountHistory;
import org.api.stocktradingservice.account.model.entity.idclass.AccountHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, AccountHistoryId> {

    @Modifying
    @Query(value = "truncate table account_history", nativeQuery = true)
    void truncateTable();
}
