package org.api.stockmarket.modules.news.repository;

import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsReleaseRepository extends JpaRepository<NewsRelease, Long> {
}
