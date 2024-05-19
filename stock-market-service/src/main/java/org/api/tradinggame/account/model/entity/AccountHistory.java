package org.api.tradinggame.account.model.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.api.tradinggame.account.model.entity.idclass.AccountHistoryId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Id
    @JsonBackReference
    private Account account;

    @Column(name = "account_balance")
    private Double balance;
}
