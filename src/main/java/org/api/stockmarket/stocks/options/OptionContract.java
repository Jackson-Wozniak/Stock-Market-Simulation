package org.api.stockmarket.stocks.options;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.tradinggame.account.model.entity.Account;

import javax.persistence.*;

@Entity(name = "options")
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
public class OptionContract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    @OneToOne
    @JoinColumn(name = "stock_ticker")
    private Stock stock;

    @Column(name = "price")
    private Double strikePrice;

    @Column(name = "bought_at")
    private Double priceBoughtAt;

    @Column(name = "premium")
    private Double premium;

    @Column(name = "date_expires")
    private String dateExpires;
}
