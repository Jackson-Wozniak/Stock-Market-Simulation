package org.api.stocktradingservice.account.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockTransactionRequest {
    private String username;
    private String password;
    private String ticker;
    private int count;
}
