package org.api.stockmarket.stocks.earnings.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;

@Getter
@Setter
public class EarningsDTO {

    private double estimatedEPS;
    private double actualEPS;
    private String reportMessage;
    private String dateOfRelease;

    public EarningsDTO(EarningsReport earnings){
        this.estimatedEPS = earnings.getEstimatedEPS();
        this.actualEPS = earnings.getActualEPS();
        this.reportMessage = earnings.getReportMessage();
        this.dateOfRelease = earnings.getDateOfRelease();
    }
}
