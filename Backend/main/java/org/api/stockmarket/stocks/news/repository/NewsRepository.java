package org.api.stockmarket.stocks.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.api.stockmarket.stocks.news.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
