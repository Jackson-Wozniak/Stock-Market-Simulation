package org.api.stockmarket.stocks.news.defaults;

import org.api.stockmarket.stocks.stock.entity.Stock;

import java.util.List;
import java.util.Random;

/*
This class contains a default list of news events that will be used to
change stock prices. They will be tracked based on the stock and the date of the event.
These will happen randomly, although I may make earnings reports a regular occurrence later on.
 */
public class DefaultNewsEvents {

    public static String negativeNewsEvents(Stock stock) {
        List<String> events = List.of(
                "Lawsuit announced against " + stock.getCompanyName()
                        + " today, investigations ongoing.",

                "CEO of " + stock.getCompanyName() + " has announced that they will be stepping down. " +
                        "Stock price sees slide as investors are wary of inexperienced replacement"
        );
        Random random = new Random();
        return events.get(random.nextInt(events.size()));
    }

    public static String positiveNewsEvents(Stock stock) {
        List<String> events = List.of(
                stock.getCompanyName() + " announces buyout of small " + stock.getSector() + " company." +
                        " There price soared as a result"
        );
        Random random = new Random();
        return events.get(random.nextInt(events.size()));
    }
}
