package org.api.stocktradingservice.account.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepositRequest {

    private String username;
    private String password;
    private double value;
}
