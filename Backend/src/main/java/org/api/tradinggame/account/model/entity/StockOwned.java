package org.api.tradinggame.account.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.tradinggame.account.utils.CalculateCostBasisAndProfits;

import jakarta.persistence.*;
import java.io.Serializable;

/*
    Entity for handling the stocks that a user owns
    Includes the ticker symbol and amount owned for each stock type, as well as
    a call back to the account username for sorting each inventory
 */
@Entity
@Table(name = "stock_owned")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockOwned implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "owned")
    private Integer amountOwned;

    @Column(name = "cost_basis")
    private Double costBasis;

    public StockOwned(Account account, String ticker, int amountOwned, double costBasis) {
        this.account = account;
        this.ticker = ticker;
        this.amountOwned = amountOwned;
        this.costBasis = costBasis;
    }

    public void updateCostBasisAndAmountOwned(int amountToBuy, double currentStockPrice) {
        this.setCostBasis(CalculateCostBasisAndProfits.newCostBasis(
                this.amountOwned, amountToBuy, this.costBasis, currentStockPrice));
        this.setAmountOwned(this.amountOwned + amountToBuy);
    }
}
