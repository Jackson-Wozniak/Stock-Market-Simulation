package org.api.stockmarket.simulator;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.tree.Tree;
import org.api.stockmarket.market.utils.DateConversion;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimulatedStockDTO {

    private final Map<String, Double> map;

    public SimulatedStockDTO(SimulatedStock stock){
        this.map = stock.getPriceHistory().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
