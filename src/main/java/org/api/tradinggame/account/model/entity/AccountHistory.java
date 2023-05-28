package org.api.tradinggame.account.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.tradinggame.account.model.entity.idclass.AccountHistoryId;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_history")
@Getter
@Setter
@NoArgsConstructor
@IdClass(AccountHistoryId.class)
@AllArgsConstructor
public class AccountHistory implements Serializable {

    @Id
    private String date;

    @Id
    @JsonBackReference
    private Account account;

    @Column(name = "account_balance")
    private Double balance;
}
