package org.api.stockmarket.modules.news.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

import org.api.stockmarket.modules.news.entity.EarningsReport;

@Getter
@Setter
public class EarningsDTO {

    private double estimatedEPS;
    private double actualEPS;
    private String reportMessage;
    private ZonedDateTime dateOfRelease;

    public EarningsDTO(EarningsReport earnings){
        this.estimatedEPS = earnings.getEstimatedEPS();
        this.actualEPS = earnings.getActualEPS();
        this.reportMessage = earnings.getReportMessage();
        this.dateOfRelease = earnings.getDateOfRelease();
    }
}
