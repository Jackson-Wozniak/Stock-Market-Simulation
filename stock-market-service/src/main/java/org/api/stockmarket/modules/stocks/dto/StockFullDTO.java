package org.api.stockmarket.modules.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.news.dto.NewsReleaseDTO;
import org.api.stockmarket.modules.stocks.entity.Stock;

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
