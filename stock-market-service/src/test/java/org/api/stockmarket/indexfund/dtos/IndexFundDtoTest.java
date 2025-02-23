package org.api.stockmarket.indexfund.dtos;

import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.indexfund.model.subclass.SectorIndexFund;
import org.api.stockmarket.indexfund.model.subclass.TotalMarketIndexFund;
import org.api.stockmarket.indexfund.model.subclass.VolatilityIndexFund;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexFundDtoTest {

    /*
    The parent (default) IndexFundDto class should only be instantiated for total market funds
     */
    @Test
    void parentDto(){
        TotalMarketIndexFund parent = new TotalMarketIndexFund(100.00);
        MarketCapIndexFund child = new MarketCapIndexFund(MarketCap.Large, 100.00);

        assertEquals(IndexFundDto.class, parent.toDto().getClass());
        assertEquals(MarketCapFundDto.class, child.toDto().getClass());
    }

    @Test
    void marketCapDto(){
        MarketCapIndexFund fund = new MarketCapIndexFund(MarketCap.Large, 100.00);

        assertEquals(MarketCapFundDto.class, fund.toDto().getClass());
    }

    @Test
    void volatilityDto(){
        VolatilityIndexFund fund = new VolatilityIndexFund(Volatility.NORMAL, 100.00);

        assertEquals(VolatileIndexFundDto.class, fund.toDto().getClass());
    }

    @Test
    void sectorDto(){
        SectorIndexFund fund = new SectorIndexFund("-", 100.00);

        assertEquals(SectorIndexFundDto.class, fund.toDto().getClass());
    }
}