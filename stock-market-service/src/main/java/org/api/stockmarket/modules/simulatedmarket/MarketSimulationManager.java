package org.api.stockmarket.modules.simulatedmarket;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MarketSimulationManager {

    private final SimulatedStockFactory simulatedStockFactory;

//    public List<SimulatedStock> simulate(int days, int stockCount){
//        List<SimulatedStock> stocks = IntStream.range(0, stockCount)
//                .mapToObj(i -> SimulatedStock.defaultInfo())
//                .toList();
//        LocalDate date = LocalDate.now();
//        for(int i = 0; i < days; i++){
//            for(int j = 0; j < MarketIntervals.PRICE_CHANGES_PER_DAY; j++){
//                stocks.forEach(SimulatedStock::changePrice);
//            }
//            LocalDate dateCopy = date;
//            stocks.forEach(stock -> stock.savePrice(dateCopy));
//            date = date.plusDays(1);
//        }
//        return stocks;
//    }
//
//    public SimulatedStock simulate(SimulationRequest req){
//        SimulatedStock stock = SimulatedStock.customInfo(
//                Volatility.map(req.getVolatility()), InvestorRating.map(req.getRating()));
//        stock.getStock().setMomentum(req.getMomentum());
//
//        LocalDate date = LocalDate.now();
//        for(int i = 0; i < req.getDays(); i++){
//            stock.changePrice();
//            LocalDate dateCopy = date;
//            stock.savePrice(dateCopy);
//            date = date.plusDays(1);
//        }
//        return stock;
//    }
//
//    public List<SimulatedStock> simulateAll(){
//        return null;
//    }
}
