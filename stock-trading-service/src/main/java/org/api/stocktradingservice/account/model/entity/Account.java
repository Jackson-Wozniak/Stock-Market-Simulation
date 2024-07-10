package org.api.stocktradingservice.account.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stocktradingservice.account.exception.AccountBalanceException;
import org.api.stocktradingservice.account.utils.CalculateCostBasisAndProfits;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/*
    Class that is used to buy/sell stocks on the market.
    Keeps a total balance that is used for stock trading, as well as a
    Set of Owned Stocks (Stock Ticker, Amount Owned)
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable, UserDetails {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double accountBalance;

    @Column(name = "total_profits")
    private Double totalProfits;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<StockOwned> stocksOwned;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AccountHistory> accountHistory;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountBalance = 10_000.0;
        this.totalProfits = 0.0;
    }

    public void updateTotalProfits(double costBasis, int sharesToSell, double currentPrice) {
        if (this.totalProfits == null) this.totalProfits = 0.0;
        setTotalProfits(CalculateCostBasisAndProfits.findProfitsAfterSelling(
                this.totalProfits, costBasis, sharesToSell, currentPrice
        ));
    }

    public void updateAccountBalance(double amountToAdd){
        if(this.getAccountBalance() + amountToAdd < 0){
            throw new AccountBalanceException("Must have more money in account");
        }
        this.setAccountBalance(Math.round((this.getAccountBalance() + amountToAdd) * 100.00) / 100.00);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
