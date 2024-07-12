package org.api.stocktradingservice.account.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountCredentialsRequest {

    private final String username;
    private final String password;
}
