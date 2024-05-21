package org.api.stockmarket.stocks.earnings.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.defaults.DefaultEarningsMessages;
import org.api.stockmarket.stocks.stock.entity.Stock;

import jakarta.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Random;

@Entity
@Table(name = "earnings_reports")
@Getter
@Setter
@NoArgsConstructor
public class EarningsReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    @JsonBackReference
    private Stock stock;

    @Column(name = "estimated_eps")
    private Double estimatedEPS;

    @Column(name = "actual_eps")
    private Double actualEPS;

    @Column(name = "report_message")
    private String reportMessage;

    @Column(name = "date_of_release")
    private ZonedDateTime dateOfRelease;

    private static final Random random = new Random();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static final double MIN_EPS = 1.0;
    private static final double MAX_RANDOM_EPS = 3.5;
    private static final double MAX_ACTUAL_EPS = 4.5;

    public EarningsReport(Stock stock, ZonedDateTime dateOfRelease) {
        this.stock = stock;
        this.estimatedEPS = calculateEstimatedEPS();
        this.actualEPS = calculateActualEPS();
        this.reportMessage = generateEarningsMessage();
        this.dateOfRelease = dateOfRelease;
    }

    @JsonIgnore
    public boolean isPositiveEarnings(){
        return this.actualEPS - this.getEstimatedEPS() >= 1;
    }

    @JsonIgnore
    public boolean isNegativeEarnings(){
        return this.actualEPS - this.getEstimatedEPS() <= 1;
    }

    public double calculateEstimatedEPS() {
        double randomEPS = MAX_RANDOM_EPS + (MAX_RANDOM_EPS - MIN_EPS) * random.nextDouble();
        return Double.parseDouble(decimalFormat.format(randomEPS));
    }

    public double calculateActualEPS() {
        double randomEPS = MAX_ACTUAL_EPS + (MAX_ACTUAL_EPS - MIN_EPS) * random.nextDouble();
        return Double.parseDouble(decimalFormat.format(randomEPS));
    }

    public String generateEarningsMessage() {
        if (actualEPS - estimatedEPS >= 1) {
            return DefaultEarningsMessages.getPositiveEarningsReport(
                    stock, estimatedEPS, actualEPS, dateOfRelease);
        }
        if (actualEPS - estimatedEPS <= -1) {
            return DefaultEarningsMessages.getNegativeEarningsReport(
                    stock, estimatedEPS, actualEPS, dateOfRelease);
        }
        return DefaultEarningsMessages.getNeutralEarningsReport(
                stock, estimatedEPS, actualEPS, dateOfRelease);
    }
}
