package org.api.stocktradingservice.account.repository;

import org.api.stocktradingservice.account.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
