package org.api.stockmarket.stocks.news.repository;

import org.api.stockmarket.stocks.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
