package org.api.stockmarket.indexfund.enums;

public enum FundTracking {
    TOTAL_MARKET {
        @Override
        public String toString(){
            return "Total Market";
        }
    },
    MARKET_CAP {
        @Override
        public String toString(){
            return "Market Cap";
        }
    },
    SECTOR {
        @Override
        public String toString(){
            return "Sector";
        }
    },
    VOLATILITY {
        @Override
        public String toString(){
            return "Volatility";
        }
    }
}
