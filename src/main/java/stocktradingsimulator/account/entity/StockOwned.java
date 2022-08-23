package stocktradingsimulator.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock_owned")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockOwned implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "owned")
    private Integer amountOwned;

    public StockOwned(Account account, String ticker){
        this.account = account;
        this.ticker = ticker;
        this.amountOwned = 1;
    }
}
