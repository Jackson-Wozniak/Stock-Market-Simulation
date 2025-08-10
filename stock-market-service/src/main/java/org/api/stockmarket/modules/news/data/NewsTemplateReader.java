package org.api.stockmarket.modules.news.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.stockmarket.modules.news.entity.NewsTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class NewsTemplateReader {
    private final ObjectMapper objectMapper;
    private static final String NEWS_PATH = "text/news_stories/stories.json";

    public NewsTemplateReader(){
        this.objectMapper = new ObjectMapper();
    }

    public List<NewsTemplate> readTemplates(){
        try {
            InputStream inputStream = new ClassPathResource(NEWS_PATH).getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load news templates", e);
        }
    }
}
