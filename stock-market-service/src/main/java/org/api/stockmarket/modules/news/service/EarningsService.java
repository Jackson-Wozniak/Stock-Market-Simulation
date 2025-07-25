package org.api.stockmarket.stocks.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.news.entity.EarningsReport;
import org.api.stockmarket.stocks.news.repository.EarningsRepository;
import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EarningsService {

    private final EarningsRepository earningsRepository;
    private final StockService stockService;

    public List<EarningsReport> findAllEarningsReports() {
        return earningsRepository.findAll();
    }

    public List<EarningsReport> findAllEarningsByDate(ZonedDateTime date) {
        return earningsRepository.findAll().stream()
                .filter(earnings -> earnings.getDateOfRelease().equals(date))
                .collect(Collectors.toList());
    }

    public List<EarningsReport> findEarningsByStock(String ticker) {
        if(!stockService.stockTickerExists(ticker)) throw new StockNotFoundException(ticker + " doesn't exist");
        return earningsRepository.findAll().stream()
                .filter(earnings -> earnings.getStock().getTicker().equals(ticker))
                .collect(Collectors.toList());
    }

    public void saveEarningsReport(EarningsReport earningsReport) {
        earningsRepository.save(earningsReport);
    }
}
