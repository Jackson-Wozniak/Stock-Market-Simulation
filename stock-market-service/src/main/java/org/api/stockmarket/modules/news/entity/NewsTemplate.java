package org.api.stockmarket.modules.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "newsTemplate")
@Table(name = "news_templates")
@Getter
@Setter
@NoArgsConstructor
public class NewsTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
