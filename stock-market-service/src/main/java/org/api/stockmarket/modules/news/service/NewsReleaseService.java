package org.api.stockmarket.modules.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.api.stockmarket.modules.news.repository.NewsReleaseRepository;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.exception.StockNotFoundException;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsReleaseService {

    private NewsReleaseRepository newsReleaseRepository;

    public void saveNewsReleases(List<NewsRelease> releases){
        newsReleaseRepository.saveAll(releases);
    }

    public void saveNewsRelease(NewsRelease release){
        newsReleaseRepository.save(release);
    }
}
