package stocktradingsimulator.account.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    Basic class for handling requests to sell stocks
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellStock {

    private String username;
    private String ticker;
    private Integer amountToBuy;
}
