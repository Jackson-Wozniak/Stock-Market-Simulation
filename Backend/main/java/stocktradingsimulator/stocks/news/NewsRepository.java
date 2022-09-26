package stocktradingsimulator.stocks.news;

import org.springframework.data.jpa.repository.JpaRepository;
import stocktradingsimulator.stocks.news.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
