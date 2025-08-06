<div align="center">
  <a href="https://unsplash.com/photos/K5mPtONmpHM?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink">
    <kbd> <img src="https://user-images.githubusercontent.com/105665813/195911442-19d6aa60-6fb5-4bdb-b380-39299162bb47.jpg" width="450" height="300"> </kbd>
  </a>

  <h3 align="center">Stock-Market-Simulator</h3>

  <a href="https://github.com/Jackson-Wozniak/Stock-Market-Simulation/edit/main/Backend"><strong>Explore The Code»</strong></a>
    </br>
    <p>
      <img src="https://img.shields.io/github/commit-activity/m/Jackson-Wozniak/Stock-Market-Simulation" alt="license" />
      <img src="https://img.shields.io/github/issues/Jackson-Wozniak/Stock-Market-Simulation" alt="license" />
      <img src="https://img.shields.io/github/license/Jackson-Wozniak/Stock-Market-Simulation" alt="license" />
      <img src="https://img.shields.io/github/languages/count/Jackson-Wozniak/Stock-Market-Simulation?color=brightgreen" alt="license" />
    </p> 
    <a href="https://github.com/Jackson-Wozniak">Github</a>
    ·
    <a href="https://github.com/Jackson-Wozniak/Stock-Market-Simulation/issues">Report Bug</a>
    ·
    <a href="https://github.com/Jackson-Wozniak/Stock-Market-Simulation/issues">Request Feature</a>
</div>

## :books: Table of Contents

<ol>
    <li><a href="#overview">Market Engine Overview</a></li>
    <li><a href="#pricing-model">Pricing Model Details</a></li>
    <li><a href="#results">Statistical Analysis & Results</a></li>
    <li><a href="#performance-optimization">Optimizing For Performance</a></li>
    <li><a href="#local-dev">Local Deployment</a></li>
    <li>
      <a href="#api-endpoints">Api Documentation</a>
      <ul>
        <li><a href="#market">Market</a></li>
        <li><a href="#stocks">Stocks</a></li>
        <li><a href="#stock-history">Stock History, News & Earnings Reports</a></li>
        <li><a href="#index-funds">Index Funds</a></li>
        <li><a href="#trading">Trading</a></li>
        <li><a href="#leaderboard">Leaderboards</a></li>
      </ul>
    </li>
</ol>    

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


## :notebook: Market Engine Overview <a id="overview"></a>

### Market Calendar Tracking

The Market Engine is the core of this stock market, handling the execution of all features in the market. To accurately track
time passage, the engine uses a universal 'Market State' that is handled by an internal service class, handling time and date changes
on each 'market tick.' A market tick is one run of the market, where the default tick is run every 1 second. Each tick, the market
time is incremented by 5 minutes. However, during after hours this is then upped to 30 minutes per tick, ensuring that the downtime between
trading days is accounted for, yet minimized. For the rest of this README, you can assume that when I reference time, it is the
internal Market Timing, as opposed to real-world time.

The market opens at 9am, and runs trading until closing at 5pm. On each market tick, the pricing model runs one change for each stock in the market,
and also saves an 'Intra Day Pricing Record' to be able to populate pricing charts. After each End-Of-Day, the closing prices are saved as a price record to be able to showcase market movement over long-term simulations. End-Of-Month and End-Of-Year milestones are also tracked, where the market trajectory is altered based on the movement over the course of the month. This trajectory than has a slight affect on the pricing model, simulating 'Bull' and 'Bear' markets that occur in the real world. Alongside this, both intra-day and end-of-day pricing records are archived at the end of
the month and year, where they are moved out of the database and into a long-term CSV file. This can be read in more detail in the 
<a href="#performance-optimization">Optimizing For Performance</a> section.

### News Stories

Alongside these pricing-related operations, the market executor also handles News and Earnings reports. Earnings run once at the end of
each fiscal quarter, and have a slight affect on the pricing model of each stock. The News executor plays a vital role in the sentiment of stocks,
releasing a range of news stories for stocks across the market, each of which having a realistic affect on the short-term sentiment of the stock.
These short-term factors regress toward zero over time, simulating the fact that news stories impact decays over time.

### Custom Simulations

To enable real-time testing, this application provides a multitude of customizable simulations through the MarketSimulationController. These
simulations are contained in-memory and have no effect on the database of stocks. This allows for extensive testing of different market features,
and is especially useful for testing the pricing model and seeing how different factors involved in price changes alter the direction of a stock.

### Index Funds

Index funds currently operate very simply, by returning the average price of all stocks under the umbrella of that index fund. This can be traded
just like any other stock in the Trading Service, so they act much like an Exchange Traded Fund (ETF). There are a broad range of options for index
funds, and the total market index fund is used as a basis for gauging the market sentiment.

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


## :chart_with_upwards_trend: Pricing Model <a id="pricing-model"></a>

### Company Attributes

To ensure a realistic simulation, each company is assigned a default attribute for each of the company attributes in the simulation. These
are investment style (growth, value, etc.), investor rating (buy, sell, hold, etc.), sector and market cap. These attributes are not changed
throughout the simulation's running, and are instead used as a basis for the expected direction a stock will move in, before random noise and
environment variables (such as news stories) are taken into account.

### Pricing Attributes and Factors

All 5 factors are tracked using a range of values from [-50, 50]. The closer to the edges of these values, the higher the change that the
stock trends in that direction, with the price changes scaling up as the factors reach closer to 50. A stock with factor values of 0
can be expected to trend nearly stable, relying primarily on randomized noise for their long-term movements.

Alongside this, each factor is also assigned a weight, where the total weight of all 5 stocks adds up to 1.0. This weight provides
more accuracy, scaling the importance of each factor based on the companies attributes and which is most important at a point in time.


### 1. Pricing Volatility

The pricing volatility is represented as ENUM value, with values of [Stable, Low, Normal, High, Extreme]. The volatility of a stock
is determined at the beginning of the simulation, and is given as one of the attributes of the company in the CSV file. This pricing volatility
affects the scale of random noise, with volatile stocks seeing more random noise during trading. This volatility has no direct influence
on the direction of the stock, as this solely seeks to amplify the movement a stock may see based on its other attributes. Each of the 5
main factors are also assigned a 'base noise' value that is added onto the volatility value, used to place a floor on how much random noise a stock
has (to ensure that stocks do not remain deterministic no matter how stable their attributes may be).

### 2. Investor Confidence

Representing the long-term confidence and health of a company, investor confidence seeks to track the long-term sentiment of the company, relying
primarily on the financial health and stability of a companies profile. Companies with consistent positive news will see an uptake in their
investor confidence, and likewise the basis of the default investor confidence is primarily based on a companies Investment Style and Investor Rating, which are both provided in the CSV file as company attributes. Direct financial data is not simulated in this application, so the
movement of a stocks investor confidence is largely based on the rating assigned to a company at the start, and the style of investment it is.

### 3. Trading Demand

Trading demand encapsulates the short-term demand for a stock, representing both speculative demand and the buying frequency a stock sees. This
is derived from the companies base attributes, but is primarily altered based on recent price movement as well as news sentiment. Some influence
will be given to the performance of related companies and stocks, but this has not yet been introduced into the model.

*This stock accounts for factor regression outlined in the News Sentiment section*

### 4. Liquidity

This factor tracks how easy it is to trade a stock, partnering closely with trading demand to simulate the fact that a very popular stock will
see reliable upward price movement as more people look to buy in, and less shares become available. This is initially derived from the companies
attributes, with a heavy emphasis on market cap, as you could expect the share availability for a large company is more consistent than their smaller
counterparts. Alongside this, as a companies trading demand moves, a strong correlation can be seen with the movement of a stocks liquidity factor.

*This stock accounts for factor regression outlined in the News Sentiment section*

### 5. News Sentiment

News sentiment begins at 0 for all stocks, and only changes based on the release of news stories. Some news stories will affect multiple stocks,
for example legislation may put pressure on all technology stocks. To accurately account for a news stories timeline, the influence of news
will regress over time, also regressing other short-term factors. This regression tends toward 0, so that news stories are rolled out of
relevance as time passes.

*This stock accounts for factor regression outlined in the News Sentiment section*

### 6. Innovation Potential

Innovation potential seeks to track the confidence investors have on how much growth potential a company has. Stocks in growth sectors will see a
higher baseline innovation factor, as you can assume a Technology company would provide more innovation than a similarly rated company in a sector
such as traditional energy.

### Pricing Formula

```diff
New Price = currentPrice
          +  newsFactorDelta
          + investorConfidenceDelta
          + innovationDelta
          + tradingDemandDelta
          + liquidityDelta
```

All factor delta's are determined by the following formula:

```diff
Factor Delta = ((((Scaled Signal * .06) +  Random Noise) 
             * CurrentPrice)
             * (1 / (1 + (currentPrice / 10,000)^1.5)))   <-- Growth dampender to ensure prices over 10,000 dont grow exponentially
             / PRICE_SCALE_DIMINISHER   <-- Usually 800.0, ensures price delta scales based on market tick rate
```

```diff
ScaledSignal = tanh((factorValue * factorWeight) / 35.0)  <-- value between +-1

FactorNoise = volatility + baseFactorNoise

Sigma = max(.05, (FactorNoise * 1 - (.7 * | ScaledSignal |)))   <-- Ensures floor of noise, scaling up as signal is closer to +-50

RandomNoise = random([-1.0, 1.0]) * Sigma 
```

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


## :bar_chart: Statistical Analysis & Results <a id="results"></a>

### Accurately modelling a stock market

### Results

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


## :clock1: Optimizing For Performance <a id="performance-optimization"></a>

### Stock Caching

Stocks are cached using a ConcurrentHashMap implementation. All interactions with the Stock DB table is done through the StockService,
which stores an internal stock cache that is referenced. This is especially useful for price changes, to enable a scalable implementation that
remains fast even as the market tick rate is shortened and performance becomes paramount.

To see the results of the addition of the stock cache, I will use the performance logger to track how long it took the market to execute one tick:

Without the stock cache, values of ~7sec to ~8sec where most common, meaning that executing a single price change for all stocks took 8 seconds.
In this case, my tick rate was set at a one second delay, but the tick rate was 9 seconds in practice. This limited the ability to accurately
change stock prices over the course of the day, but the true bottleneck was that the number of stocks was limited in the database to ensure
executions did not take too long.

Once the stock cache was introduced, the average performance of one execution hovered around ~80ms. Prices were saved to the database every 10
ticks, meaning that every 10 ticks took ~5sec. This however could be minimized by reducing the frequency of cache validation, as we do not have
any reason to save every 10 ticks when all read/write operations on stocks are done through the cache. Likewise, saving of the cache to the DB
could also be done concurrently, reducing the downtime of the market execution thread and increasing the reliability of the true tick rate.

### Pricing Record Archives

To avoid an exponentially growing amount of data in the database, price records are archived on a rolling basis, where old
price records are archived at the same time new ones are put in the database. At the end of the month, intra-day price records
from two months ago are deleted from the database and inserted into the CSV archive file for long term storage. At the end of the year, 
end-of-day price records from two years ago are deleted and stored in long term storage. This ensures that recent price records are available,
but retrieving old prices is read from the archives instead, keeping DB data clean and relevant.

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


## :pencil2: Local Deployment <a id="local-dev"></a>

To run locally, first ensure that Docker Desktop & Maven is downloaded to your system. Then run the following commands:

- Stock Market Service will run on http://localhost:8000
- Stock Trading Service will run on http://localhost:8010

```
  - git clone https://github.com/Jackson-Wozniak/Stock-Market-Simulation.git
  - docker-compose up
  
  To update docker-compose after changes to code, run:
  - docker-compose up --build
  
  To remove created containers after you are done, run:
  - docker-compose down
```

<br/>
<!-- ------------------------------------------------------------------------------------------------------------------------------------------->


<h2> :electric_plug: API Documentation <a id="api-endpoints"></a></h2>

## Object Definitions
<details>
 <summary><code>Market</code></summary>

```json
{
  "date": String,
  "trajectory": String,
  "lastMonthPrice": double
}
```
</details>

<details>
 <summary><code>Stock</code></summary>

```json
{
  "ticker" : String,
  "companyName" : String,
  "price" : double,
  "lastDayPrice" : double,
  "percentChange" : double
}
```
</details>

<details>
 <summary><code>DetailedStock</code></summary>

```json
{
 "ticker" : String,
  "companyName" : String,
  "sector" : String,
  "marketCap" : String,
  "price" : double,
  "lastDayPrice" : double,
  "momentum" : integer,
  "momentumStreakInDays" : integer,
  "volatileStock" : String,
  "investorRating" : String
}
```
</details>

<details>
 <summary><code>StockPriceHistory</code></summary>

```json
[
  {
    "marketDate": ZonedDateTime,
    "price": double
  }
]
```
</details>

<details>
 <summary><code>News</code></summary>

```json
{
  "event": String,
  "dateReleased": ZonedDateTime
}
```
</details>

<details>
 <summary><code>EarningsReport</code></summary>

```json
{
  "estimatedEPS": double,
  "actualEPS": double,
  "reportMessage": String,
  "dateOfRelease": ZonedDateTime
}
```
</details>

<details>
 <summary><code>SimulatedStock</code></summary>

```json
[
  {
    "MM/DD/YYYY": double
  },
]
```
</details>

<details>
 <summary><code>IndexFund</code></summary>

```json
{
  "name": String,
  "price": double,
  "fundTracking": String
}
```
</details>

## Endpoints

*each endpoint is shown in format {Http Method} {URL Path} {StatusCode -> Return Object}*

### Market Endpoints

> <code>GET</code> <code><b>/api/v1/market</b></code> <code><b>200 OK -> Market</b></code>

### Simulation Endpoints
> <code>GET</code> <code><b>/api/v1/market/sim/price_history?days={String}&stocks={int}</b></code> <code><b>200 OK -> List[SimulatedStock]</b></code>

### Stock Endpoints

> <code>GET</code> <code><b>/api/v1/stocks/{ticker}</b></code> <code><b>200 OK -> Stock</b></code>
>
><code>GET</code> <code><b>/api/v1/stocks/{ticker}?is_detailed=true</b></code> <code><b>200 OK -> DetailedStock</b></code>
> 
> <code>GET</code> <code><b>/api/v1/stocks</b></code> <code><b>200 OK -> List[Stock]</b></code>
>
> <code>GET</code> <code><b>/api/v1/stocks/detailed</b></code> <code><b>200 OK -> List[Stock]</b></code>
>
> <code>GET</code> <code><b>/api/v1/stocks/marketCap/{String}</b></code> <code><b>200 OK -> List[Stock]</b></code>
>
> <code>GET</code> <code><b>/api/v1/stocks/sector/{String}</b></code> <code><b>200 OK -> List[Stock]</b></code>
>
> <code>GET</code> <code><b>/api/v1/stocks/price/{ticker}</b></code> <code><b>200 OK -> double</b></code>
>
> <code>GET</code> <code><b>/api/v1/stocks/random</b></code> <code><b>200 OK -> Stock</b></code>
>
> <code>GET</code> <code><b>/api/v1/stocks/history/{ticker}</b></code> <code><b>200 OK -> List[StockPriceHistory]</b></code>

### News Endpoints

> <code>GET</code> <code><b>/api/v1/news/{ticker}</b></code> <code><b>200 OK -> List[News]</b></code>
>
> <code>GET</code> <code><b>/api/v1/news/</b></code> <code><b>200 OK -> List[News]</b></code>

### Earnings Endpoints

> <code>GET</code> <code><b>/api/v1/earnings/{ticker}</b></code> <code><b>200 OK -> List[EarningsReport]</b></code>
>
> <code>GET</code> <code><b>/api/v1/earnings</b></code> <code><b>200 OK -> List[EarningsReport]</b></code>
>
> <code>GET</code> <code><b>/api/v1/earnings/date/{String}</b></code> <code><b>200 OK -> List[EarningsReport]</b></code>

### Index Fund Endpoints

> <code>GET</code> <code><b>/api/v1/funds</b></code> <code><b>200 OK -> List[IndexFund]</b></code>
>
> <code>GET</code> <code><b>/api/v1/funds/total-market</b></code> <code><b>200 OK -> IndexFund</b></code>
>
> <code>GET</code> <code><b>/api/v1/funds/cap</b></code> <code><b>200 OK -> List[IndexFund] </b></code>
>
> <code>GET</code> <code><b>/api/v1/funds/cap/{String}</b></code> <code><b>200 OK -> IndexFund </b></code>
>
> <code>GET</code> <code><b>/api/v1/funds/sector</b></code> <code><b>200 OK -> List[IndexFund] </b></code>
>
> <code>GET</code> <code><b>/api/v1/funds/sector/{String}</b></code> <code><b>200 OK -> IndexFund </b></code>
>
> <code>GET</code> <code><b>/api/v1/funds/volatility</b></code> <code><b>200 OK -> List[IndexFund] </b></code>
>
> <code>GET</code> <code><b>/api/v1/funds/volatility/{String}</b></code> <code><b>200 OK -> IndexFund </b></code>

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
