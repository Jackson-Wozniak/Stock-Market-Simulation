package io.github.stockmarket.market.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.news.dto.NewsReleaseDTO;
import io.github.stockmarket.market.stocks.model.Stock;

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
