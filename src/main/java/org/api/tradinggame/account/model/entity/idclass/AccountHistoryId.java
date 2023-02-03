package org.api.tradinggame.account.model.entity.idclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.api.tradinggame.account.model.entity.Account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AccountHistoryId implements Serializable {

    @Column(name = "market_date")
    private String date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;
}
