package org.api.stockmarket.modules.news.configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.data.NewsTemplateReader;
import org.api.stockmarket.modules.news.entity.NewsTemplate;
import org.api.stockmarket.modules.news.service.NewsTemplateService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

@Configuration
@AllArgsConstructor
@DependsOn({"marketConfiguration"})
public class NewsTemplateConfiguration {
    private final NewsTemplateService newsTemplateService;
    private final NewsTemplateReader newsTemplateReader;

    @PostConstruct
    public void saveNewsTemplates(){
        List<NewsTemplate> templates = newsTemplateReader.readTemplates();

        newsTemplateService.saveAllTemplates(templates);
    }
}
