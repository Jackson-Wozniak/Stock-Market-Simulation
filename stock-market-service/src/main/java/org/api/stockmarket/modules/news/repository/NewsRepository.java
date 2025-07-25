package org.api.stockmarket.modules.news.repository;

import org.api.stockmarket.modules.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
