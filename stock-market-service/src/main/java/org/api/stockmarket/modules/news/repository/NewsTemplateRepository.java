package org.api.stockmarket.modules.news.repository;

import org.api.stockmarket.modules.news.entity.NewsTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTemplateRepository extends JpaRepository<NewsTemplate, Long> {
}
