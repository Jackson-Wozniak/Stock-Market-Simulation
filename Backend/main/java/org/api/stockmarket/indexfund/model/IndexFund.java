package org.api.stockmarket.indexfund.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
Right Now these are only created when a user requests to see one. I will
eventually change this to save them in a database and update the prices
each day at the end of the day, similar to real world mutual funds
 */
public class IndexFund {

    private String name;
    private double price;
    private FundTracking fundTracking;
}
