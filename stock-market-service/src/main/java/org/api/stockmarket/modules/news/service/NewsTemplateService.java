package org.api.stockmarket.modules.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.repository.NewsTemplateRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsTemplateService {
    private final NewsTemplateRepository newsTemplateRepository;
}
