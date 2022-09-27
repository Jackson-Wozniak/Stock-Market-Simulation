package stocktradingsimulator.stocks.earnings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.stocks.earnings.entity.EarningsReport;

public interface EarningsRepository extends JpaRepository<EarningsReport, Long> {
}
