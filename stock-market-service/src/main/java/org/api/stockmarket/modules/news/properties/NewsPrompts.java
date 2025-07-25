package org.api.stockmarket.stocks.news.properties;

import org.api.stockmarket.stocks.news.model.NewsStory;

import java.util.List;

public class NewsPrompts {

    public static final List<NewsStory> positives = List.of(
            NewsStory.positive(
                    "{name} announced a buyout of a smaller company today, marking their entrance" +
                            " into the new sector. Investors are optimistic that they will be able to" +
                            " turn the company around under new leadership",
                    1.05
            ),
            NewsStory.positive(
                    "{name} announced that they are being bought by new ownership. {ticker} soared " +
                            "as investors see an influx of cash into the company",
                    1.3
            )
    );

    public static final List<NewsStory> negatives = List.of(
            NewsStory.negative(
                    "{name}'s CEO announced today that they plan to step down at the end of the month" +
                            ". {ticker} price slid as investors are wary of the inexperienced replacement.",
                    .85
            ),
            NewsStory.negative(
                    "A lawsuit today was announced against {name}. Investigations are ongoing, yet" +
                            " the stock saw a decrease as investors are uncertain of their prospects",
                    .92
            )
    );
}
