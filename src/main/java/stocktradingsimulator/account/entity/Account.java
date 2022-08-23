package stocktradingsimulator.account.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    private String username;

    @Column(name = "balance")
    private Double accountBalance;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<StockOwned> stocksOwned;

    public Account(String username){
        this.username = username;
        this.accountBalance = 0.0;
        //this.stocksOwned = Set.of();
    }
}
