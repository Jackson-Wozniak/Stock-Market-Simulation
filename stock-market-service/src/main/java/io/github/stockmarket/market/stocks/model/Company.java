package io.github.stockmarket.market.stocks.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.github.stockmarket.market.stocks.enums.InvestmentStyle;
import io.github.stockmarket.market.stocks.enums.InvestorRating;
import io.github.stockmarket.market.stocks.enums.MarketCap;

@Getter
@Setter
@NoArgsConstructor
public class Company {
    private Stock stock;
    private String companyName;
    private String sector;
    private MarketCap marketCap;
    private InvestorRating investorRating;
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
