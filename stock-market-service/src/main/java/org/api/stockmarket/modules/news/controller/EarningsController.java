package org.api.stockmarket.stocks.news.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.api.stockmarket.stocks.news.entity.EarningsReport;
import org.api.stockmarket.stocks.news.service.EarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/earnings")
@AllArgsConstructor
public class EarningsController {

    private final EarningsService earningsService;

    @GetMapping
    public List<EarningsReport> getAllEarningsReportHistory() {
        return earningsService.findAllEarningsReports();
    }

    @RequestMapping(value = "/stock/{ticker}")
    public List<EarningsReport> getAllEarningsHistoryFromStock(@PathVariable String ticker) {
        return earningsService.findEarningsByStock(ticker);
    }

    //date is formatted as month_day_year here instead of month/day/year
    @RequestMapping(value = "/date/{date}")
    public List<EarningsReport> getEarningsOnDate(@PathVariable String date) {
        ZonedDateTime parsedDate = ZonedDateTime.parse(date);
        return earningsService.findAllEarningsByDate(parsedDate);
    }
}
