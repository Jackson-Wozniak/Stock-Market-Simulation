package org.api.stockmarket.modules.stocks.enums;

@Deprecated
public enum Volatility {
    STABLE {
        @Override
        public String toString(){
            return "Stable";
        }
    },
    NORMAL {
        @Override
        public String toString(){
            return "Normal";
        }
    },
    VOLATILE {
        @Override
        public String toString(){
            return "Volatile";
        }
    },
    EXTRA_VOLATILE {
        @Override
        public String toString(){
            return "Extra Volatile";
        }
    };

    public int multiplier(){
        return switch(this){
            case STABLE -> 0;
            case NORMAL -> 1;
            case VOLATILE -> 2;
            case EXTRA_VOLATILE -> 3;
        };
    }

    public static Volatility map(String str){
        return switch (str.replace(" ", "_").toLowerCase()){
            case "stable" -> STABLE;
            case "normal" -> NORMAL;
            case "volatile" -> VOLATILE;
            case "extra_volatile" -> EXTRA_VOLATILE;
            default -> null;
        };
    }
}
