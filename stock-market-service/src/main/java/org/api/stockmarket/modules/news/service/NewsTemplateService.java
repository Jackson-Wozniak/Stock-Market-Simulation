package org.api.stockmarket.modules.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.core.utils.RandomUtils;
import org.api.stockmarket.modules.news.entity.NewsTemplate;
import org.api.stockmarket.modules.news.repository.NewsTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class NewsTemplateService {
    private final NewsTemplateRepository newsTemplateRepository;

    public List<NewsTemplate> findAllTemplates(){
        return newsTemplateRepository.findAll();
    }

    public NewsTemplate findRandomTemplate(boolean isPositive){
        List<NewsTemplate> templates = findAllTemplates().stream()
                .filter(template -> isPositive ? template.getImpactWeight() > 0 : template.getImpactWeight() < 0)
                .toList();
        return RandomUtils.getRandomElement(templates);
    }

    public void saveAllTemplates(List<NewsTemplate> templates){
        newsTemplateRepository.saveAll(templates);
    }
}
