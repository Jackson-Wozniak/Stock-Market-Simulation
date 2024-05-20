package org.api.tradinggame.account.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    Basic class for handling requests to buy stocks
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuyStockRequest {

    private String username;
    private String ticker;
    private int sharesToBuy;
}
