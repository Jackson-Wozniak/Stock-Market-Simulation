package org.api.stocktradingservice.account.client;

import org.api.stocktradingservice.account.response.StockResponse;
import org.springframework.web.client.RestTemplate;

public class StockMarketRestClient {

    private static final String ROOT_URL = "http://localhost:8000/ap1/v1";
    private static final String GET_STOCK_PATH = "/stocks";


}
