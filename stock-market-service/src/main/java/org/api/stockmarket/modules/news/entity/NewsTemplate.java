package org.api.stockmarket.modules.news.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsTemplate {
    private String eventType;
    private Integer impactWeight;
    private String template;

    public NewsTemplate(String eventType, int impactWeight, String template){
        this.eventType = eventType;
        this.impactWeight = impactWeight;
        this.template = template;
    }
}
