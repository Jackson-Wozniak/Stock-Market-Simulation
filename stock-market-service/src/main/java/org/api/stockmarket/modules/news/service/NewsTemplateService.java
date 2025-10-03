package org.api.stockmarket.modules.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.entity.NewsTemplate;
import org.api.stockmarket.modules.news.repository.NewsTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsTemplateService {
    private final NewsTemplateRepository newsTemplateRepository;

    public List<NewsTemplate> findAllTemplates(){
        return newsTemplateRepository.findAll();
    }

    public void saveAllTemplates(List<NewsTemplate> templates){
        newsTemplateRepository.saveAll(templates);
    }
}
