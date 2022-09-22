package stocktradingsimulator.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.stock.model.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
