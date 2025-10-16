package org.api.stockmarket.modules.news.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.core.entity.BaseEntity;

@Entity(name = "newsTemplate")
@Table(name = "news_templates")
@Getter
@Setter
@NoArgsConstructor
public class NewsTemplate extends BaseEntity {
    @Column(name = "event_type")
    private String eventType;

    @Column(name = "impact_weight")
    private Integer impactWeight;

    @Column(name = "template")
    private String template;

    public NewsTemplate(String eventType, int impactWeight, String template){
        this.eventType = eventType;
        this.impactWeight = impactWeight;
        this.template = template;
    }
}
