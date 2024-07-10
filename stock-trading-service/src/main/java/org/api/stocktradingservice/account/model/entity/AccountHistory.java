package org.api.stocktradingservice.account.model.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.*;
import org.api.stocktradingservice.account.model.entity.idclass.AccountHistoryId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_history")
@Getter
@Setter
@NoArgsConstructor
@IdClass(AccountHistoryId.class)
@AllArgsConstructor
public class AccountHistory implements Serializable {

    @Id        
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;

    @Column(name = "username")
    private String username;

    @ManyToOne
    private Account account;

    @Column(name = "account_balance")
    private Double balance;

    public AccountHistory(ZonedDateTime date, Account account){
        this.date = date;
        this.account = account;
        this.username = account.getUsername();
        this.balance = account.getAccountBalance();
    }
}
