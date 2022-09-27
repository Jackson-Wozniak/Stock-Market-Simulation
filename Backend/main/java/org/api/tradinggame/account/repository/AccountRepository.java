package org.api.tradinggame.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.api.tradinggame.account.model.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
