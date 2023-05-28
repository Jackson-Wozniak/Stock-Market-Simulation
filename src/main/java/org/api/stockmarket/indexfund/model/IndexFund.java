package org.api.stockmarket.indexfund.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;

@Entity
@Table(name = "index_fund")
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Id
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "fund_tracking")
    @Enumerated(EnumType.STRING)
    private FundTracking fundTracking;
}
