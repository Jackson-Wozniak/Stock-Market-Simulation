package org.api.stockmarket.modules.stocks.utils;

import org.api.stockmarket.modules.stocks.entity.Company;

public class StockFactoryUtils {
    public static int defaultInnovationFactor(Company company){
        int baseFactor = 0;
        baseFactor += company.getInvestorRating().defaultInnovationFactor();
        baseFactor += company.getInvestmentStyle().defaultInnovationFactor();
        baseFactor += company.getMarketCap().defaultInnovationFactor();
        baseFactor += defaultInnovationFactorFromSector(company.getSector());

        return baseFactor;
    }

    public static int defaultInvestorConfidence(Company company){
        int baseFactor = 0;
        baseFactor += company.getInvestorRating().defaultInvestorConfidenceFactor();
        baseFactor += company.getInvestmentStyle().defaultInvestorConfidenceFactor();
        baseFactor += company.getMarketCap().defaultInvestorConfidenceFactor();
        baseFactor += defaultInnovationFactorFromSector(company.getSector());

        return baseFactor;
    }

    public static int defaultLiquidity(Company company){
        int baseFactor = 0;
        baseFactor += company.getInvestorRating().defaultLiquidityFactor();
        baseFactor += company.getInvestmentStyle().defaultLiquidityFactor();
        baseFactor += company.getMarketCap().defaultLiquidityFactor();
        baseFactor += defaultInnovationFactorFromSector(company.getSector());

        return baseFactor;
    }

    public static int defaultTradingDemand(Company company){
        int baseFactor = 0;
        baseFactor += company.getInvestorRating().defaultTradingDemandFactor();
        baseFactor += company.getInvestmentStyle().defaultTradingDemandFactor();
        baseFactor += company.getMarketCap().defaultTradingDemandFactor();
        baseFactor += defaultInnovationFactorFromSector(company.getSector());

        return baseFactor;
    }

    private static int defaultInnovationFactorFromSector(String sector){
        return switch (sector.toLowerCase()){
            case "technology" -> 15;
            case "finance" -> -5;
            case "biotech" -> 10;
            case "consumer discretionary" -> -10;
            case "healthcare" -> 2;
            case "communication services" -> -3;
            case "energy" -> -12;
            case "consumer staples" -> -8;
            case "industrials" -> -18;
            case "materials" -> -16;
            default -> 0;
        };
    }
}
