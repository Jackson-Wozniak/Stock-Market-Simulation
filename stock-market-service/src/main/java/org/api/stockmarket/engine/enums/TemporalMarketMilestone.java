package org.api.stockmarket.engine.enums;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public enum TemporalMarketMilestone {
    NONE("Middle of Day"),
    END_OF_DAY("End of Day"),
    END_OF_MONTH("End of Month"),
    END_OF_QUARTER("End of Quarter"),
    END_OF_YEAR("End of Year");

    private final String name;

    TemporalMarketMilestone(String name){
        this.name = name;
    }

    public static TemporalMarketMilestone of(ZonedDateTime dateTime){
        return TemporalMarketMilestone.NONE;
    }
}
