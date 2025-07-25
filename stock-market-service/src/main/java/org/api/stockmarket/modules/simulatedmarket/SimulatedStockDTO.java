package org.api.stockmarket.modules.simulatedmarket;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.common.utils.DateConversion;

import java.util.LinkedHashMap;
import java.util.Map;
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
                        entry -> DateConversion.toDashedDate(entry.getKey()),
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
