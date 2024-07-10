package org.api.stocktradingservice.account.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stocktradingservice.account.exception.AccountBalanceException;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class LimitOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @Column(name = "ticker")
    private String ticker;

    @Column
    private Integer sharesToBuy;

    @Column
    private Double limitPrice;

    public LimitOrder(Account account, String ticker, int sharesToBuy, double limitPrice) {
        this.account = account;
        this.sharesToBuy = sharesToBuy;
        this.ticker = ticker;
        this.limitPrice = limitPrice;
        if (!validOrderRequest()) throw new AccountBalanceException("Cannot Process Order");
    }

    public boolean validOrderRequest() {
        //TODO: implement
        //return !(sharesToBuy * stock.getPrice() > account.getAccountBalance());
        return false;
    }
}
