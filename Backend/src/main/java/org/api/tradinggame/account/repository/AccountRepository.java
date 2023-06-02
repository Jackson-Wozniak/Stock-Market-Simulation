package org.api.tradinggame.account.repository;

import org.api.tradinggame.account.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
