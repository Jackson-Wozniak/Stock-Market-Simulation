package io.github.stockmarket.market.stocks.model;

import io.github.stockmarket.market.stocks.enums.InvestmentStyle;
import io.github.stockmarket.market.stocks.enums.InvestorRating;
import io.github.stockmarket.market.stocks.enums.MarketCap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAttributes {
    private String companyName;
    private String sector;
    private MarketCap marketCap;
    private InvestorRating investorRating;
    private InvestmentStyle investmentStyle;

    public CompanyAttributes(Builder builder){
        this.companyName = builder.companyName;
        this.sector = builder.sector;
        this.marketCap = builder.marketCap;
        this.investorRating = builder.investorRating;
        this.investmentStyle = builder.investmentStyle;
    }

    public static class Builder {
        private String companyName;
        private String sector;
        private MarketCap marketCap;
        private InvestorRating investorRating;
        private InvestmentStyle investmentStyle;

        public Builder companyDetails(String companyName, String sector, MarketCap marketCap) {
            this.companyName = companyName;
            this.sector = sector;
            this.marketCap = marketCap;
            return this;
        }

        public Builder investmentProfile(InvestorRating rating, InvestmentStyle style) {
            this.investorRating = rating;
            this.investmentStyle = style;
            return this;
        }

        public CompanyAttributes build() {
            return new CompanyAttributes(this);
        }
    }
}
