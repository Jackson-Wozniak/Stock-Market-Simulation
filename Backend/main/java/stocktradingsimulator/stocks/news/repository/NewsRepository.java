package stocktradingsimulator.stocks.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.stocks.news.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
