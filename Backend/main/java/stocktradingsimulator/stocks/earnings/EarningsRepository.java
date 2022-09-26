package stocktradingsimulator.stocks.earnings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EarningsRepository extends JpaRepository<EarningsReport, Long> {
}
