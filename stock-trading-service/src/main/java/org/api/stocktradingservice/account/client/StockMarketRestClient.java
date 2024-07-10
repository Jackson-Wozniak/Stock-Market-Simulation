package org.api.stocktradingservice.account.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class StockMarketRestClient {

    private final RestTemplate restTemplate;
    private static final String ROOT_URL = "http://localhost:8000/api/v1";
    private static final String STOCK_PATH = ROOT_URL + "/stocks";

    public StockResponse retrieveStockInfo(String ticker){
        return restTemplate.getForObject(STOCK_PATH + "/" + ticker, StockResponse.class);
    }

    public MarketResponse retrieveMarketInfo(){
        return null;
    }
}
