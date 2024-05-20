package org.api.tradinggame.account.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.tradinggame.account.exception.AccountBalanceException;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Stock stock;

    @Column
    private Integer sharesToBuy;

    @Column
    private Double limitPrice;

    public LimitOrder(Account account, Stock stock, int sharesToBuy, double limitPrice) {
        this.account = account;
        this.sharesToBuy = sharesToBuy;
        this.stock = stock;
        this.limitPrice = limitPrice;
        if (!validOrderRequest()) throw new AccountBalanceException("Cannot Process Order");
    }

    public boolean validOrderRequest() {
        return !(sharesToBuy * stock.getPrice() > account.getAccountBalance());
    }
}
