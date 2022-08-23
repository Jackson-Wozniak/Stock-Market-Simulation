package stocktradingsimulator.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
