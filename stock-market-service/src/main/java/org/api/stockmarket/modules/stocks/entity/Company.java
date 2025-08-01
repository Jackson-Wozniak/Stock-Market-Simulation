package org.api.stockmarket.modules.stocks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.enums.InvestmentStyle;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.MarketCap;

@Entity(name = "company")
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "sector")
    private String sector;

    @Column(name = "cap")
    @Enumerated(EnumType.STRING)
    private MarketCap marketCap;

    @Column(name = "investor_rating")
    @Enumerated(EnumType.STRING)
    private InvestorRating investorRating;

    @Column(name = "investment_style")
    @Enumerated(EnumType.STRING)
    private InvestmentStyle investmentStyle;

    public Company(Builder builder){
        this.stock = builder.stock;
        this.companyName = builder.companyName;
        this.sector = builder.sector;
        this.marketCap = builder.marketCap;
        this.investorRating = builder.investorRating;
        this.investmentStyle = builder.investmentStyle;
    }

    public static class Builder{
        private final Stock stock;
        private String companyName;
        private String sector;
        private MarketCap marketCap;
        private InvestorRating investorRating;
        private InvestmentStyle investmentStyle;

        public Builder(Stock stock){
            this.stock = stock;
        }

        public Builder companyDetails(String companyName, String sector, MarketCap marketCap){
            this.companyName = companyName;
            this.sector = sector;
            this.marketCap = marketCap;
            return this;
        }

        public Builder investmentProfile(InvestorRating rating, InvestmentStyle style){
            this.investorRating = rating;
            this.investmentStyle = style;
            return this;
        }

        public Company build(){
            return new Company(this);
        }
    }
}
