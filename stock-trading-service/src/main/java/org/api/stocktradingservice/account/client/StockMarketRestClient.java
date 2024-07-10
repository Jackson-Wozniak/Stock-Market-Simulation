package org.api.stocktradingservice.account.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class StockMarketRestClient {

    private final RestTemplate restTemplate;
    private static final String ROOT_URL = "http://localhost:8000/ap1/v1";
    private static final String GET_STOCK_PATH = "/stocks";

    public StockResponse retrieveStockInfo(String ticker){
        return null;
    }

    public MarketResponse retrieveMarketInfo(){
        return null;
    }
}
