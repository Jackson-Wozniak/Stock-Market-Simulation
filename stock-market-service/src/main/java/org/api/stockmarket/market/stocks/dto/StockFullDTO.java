package org.api.stockmarket.market.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.market.news.dto.NewsReleaseDTO;
import org.api.stockmarket.market.stocks.model.Stock;

import java.util.List;

@Getter
@Setter
public class StockFullDTO extends StockDetailedDTO{
    private List<PriceRecordDTO> priceRecords;
    private List<NewsReleaseDTO> newsReleases;

    public StockFullDTO(Stock stock){
        super(stock);
        priceRecords = stock.getPriceRecords().stream().map(PriceRecordDTO::new).toList();
        newsReleases = stock.getNewsReleases().stream().map(NewsReleaseDTO::new).toList();
    }
}
