package org.api.stocktradingservice.account.model.entity.idclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.api.stocktradingservice.account.model.entity.Account;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AccountHistoryId implements Serializable {

    @Column(name = "market_date")
    private String date;

    @Column(name = "username")
    private String username;
}
