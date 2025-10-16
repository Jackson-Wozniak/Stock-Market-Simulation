package org.api.stockmarket.modules.stocks.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.stocks.entity.PriceRecord;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.repository.PriceRecordRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PriceRecordService {

    private final PriceRecordRepository priceRecordRepository;

    public void savePricesEOD(List<Stock> stocks, ZonedDateTime date){
        List<PriceRecord> records = stocks.stream()
                        .map(stock -> new PriceRecord(stock, date)).toList();
        priceRecordRepository.saveAll(records);
    }

    public void archiveRecordsAtOrBeforeDate(ZonedDateTime date){
        /*
        TODO:
            - get all records in DB that are before date (inclusive)
            - delete these records from the DB
            - save all records to an archive CSV file
         */
    }

    public List<PriceRecord> findRecordsByStock(String ticker){
        return priceRecordRepository.findAll().stream()
                .filter(record -> record.getTicker().equalsIgnoreCase(ticker)).toList();
    }
}
