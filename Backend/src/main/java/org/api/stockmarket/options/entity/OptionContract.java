package org.api.stockmarket.options.entity;

/*
Option contract price will be based on the Black-Scholes Model, this means it will follow the European
    Options style and can only be exercised at expiry. The contract will be able to be sold for a profit
    early, but cannot be exercised until the date of expiration. Instead of exercising the option, the user
    will also have the choice to auto sell the option, simulating the purchase and immediate sale
    of 100 shares at the strike price. If the option is OTM, it will expire and they will lose the money
    they bought the option for.

Implied volatility will be based on these considerations:
    - volatility value of the stock (this is static)
    - recent news events altering the price of the stock
    - time to earnings. There will be a countdown of days to earnings, and implied volatility
        will be raised as the date nears, and lowers once past
    - changes in momentum and investor rating

The risk-free rate will be set at 3.5% originally, however this may change based on market factors later
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.options.enums.OptionType;
import org.api.stockmarket.stocks.stock.entity.Stock;

@Entity(name = "option")
@Table(name = "options")
@NoArgsConstructor
@Getter
@Setter
public class OptionContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_ticker", referencedColumnName = "ticker")
    private Stock stock;

    @Enumerated(EnumType.STRING)
    private OptionType optionType;

    @Column(name = "expirationDate")
    private String expirationDate;

    public OptionContract(
            Stock stock,
            OptionType optionType,
            String expirationDate){
        this.stock = stock;
        this.optionType = optionType;
        this.expirationDate = expirationDate;
    }
}
