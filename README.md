<div align="center">
  <a href="https://unsplash.com/photos/K5mPtONmpHM?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink">
    <kbd> <img src="https://user-images.githubusercontent.com/105665813/195911442-19d6aa60-6fb5-4bdb-b380-39299162bb47.jpg" width="450" height="300"> </kbd>
  </a>

  <h3 align="center">Stock-Market-Simulator</h3>

  <a href="https://github.com/Jackson-Wozniak/Stock-Market-Simulation/edit/main/Backend"><strong>Explore The Code»</strong></a>
    </br>
    <p>
      <img src="https://img.shields.io/github/commit-activity/m/Jackson-Wozniak/Stock-Market-Simulation" alt="license" />
      <img src="https://img.shields.io/tokei/lines/github/Jackson-Wozniak/Stock-Market-Simulation" alt="license" />
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
    <li><a href="#features">Features</a></li>
    <li><a href="#design">Design Choices</a></li>
    <li><a href="#local-dev">Local Deployment</a></li>
    <li><a href="#contributing">Contributing</a></li>
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
    <li><a href="#results">Results & Data From Simulations</a></li>
</ol>    

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :notebook: Features & Overview <a id="features"></a>

A server side implementation for a stock market and stock trading simulator. Users can create accounts and trade stocks using paper money
with the goal of increasing their profits and outperforming other accounts, while keeping track of market activity to best plan for price trajectories

All stock prices, news, earnings etc. are completely simulated and are not 
reflective of real world market activity. 

Default stock data, such as the name, ticker symbol, market cap and sector are based on real world companies

#### Market
* The market date is tracked on intervals that can be altered. The default "day" is 24 intervals of 10 seconds, and 30 "days" is a month
* The date is formatted as month/day/year
* Stock prices change after each 10 second interval, and certain stock events can happen at the end of each day
* There are 3 market types; Bear, Bull and Normal. Bear markets occur if the average stock price falls 10% in a month, while
  bull markets happen if prices rise 10% monthly. Normal market conditions cover all scenarios in between

#### Stocks
* Stock prices change on an interval (10 seconds)
* Stock prices change based on three factors: market cap, momentum, and volatility
* Market Cap: Large and small cap stocks experience higher movement, in an upward or downward trajectory
* Momentum: When stock prices rise for 3 days, they experience positive momentum, while they experience negative momentum if they fall for 3 days
* Volatility: Each stock is judged on whether it is volatile or not. This is an unchangeable boolean value, and is based on the nature of the real world company. Volatile stocks receive a slight increase in movement each time their prices change

#### News
* At the end of each day, there is a chance that a specific stock will release a news story, which will have a large effect on their price
* Positive news, such as buyouts, will increase the stocks by around price 10%
* Negative news, such as lawsuits or management shakeups will decrease stock price by around 10%
* Bankruptcies will occur if a stock price dips below $1, where a buyout will occur and the stocks price will reset back to the default

#### Earnings Reports
* Stocks release earnings reports on the first day of every 3rd month (3rd, 6th, 9th, 12th)
* Earnings reports effect stock prices and optimism, and are also affected by previous optimism

#### Index Funds
* Index funds track the average price of a specific category of stocks
* These cannot be traded, but only serve to estimate the total market trajectory

#### Accounts

#### Leaderboards

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## 📝: Design Choices <a id="design"></a>

### Microservices

I am currently in the process of migrating the current backend server to multiple microservices.
The three microservices will be:
  1. Stock Market API
  2. Trading API
  3. Trading Bots

Here are the main justifications for doing this:
- Learn microservices & gain experience building applications that talk to other services

- Implement Spring Security, but isolate its features only to the trading API. The market is a publically available API, and so
  its endpoints do not require any authorization/authentication. Likewise, the trading bot service has no endpoints, and so requires
  minimal security measures, at least in the context of APIs. This means Spring Security is only relevant for 1/3 functions

- Although this is a simulation, and there are no plans for deployment, the idea here is that each of the 3 services would be seperate "companies."
  The trading API acts a brokerage firm, the market API simulated a countries stock market, and the trading bot service acts a quant firm that interacts
  with its domestic market through publically-unavailable bots. Seperating the server into these 3 services means that it more closely follows the goal
  of having 3 seperate "companies" that all work together to build a stock market simulation

<br/>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------- -->

## :pencil2: Local Deployment <a id="local-dev"></a>

To run locally, first ensure that Docker Desktop & Maven is downloaded to your system. Then run the following commands:

```
  - git clone https://github.com/Jackson-Wozniak/Stock-Market-Simulation.git
  - cd Backend/
  - mvn clean package -DskipTests
  - cd ../
  - docker-compose up
  
  To update docker-compose after changes to code, run:
  - cd Backend/
  - mvn clean package -DskipTests
  - cd ../
  - docker-compose up --build
  
  To remove created containers after you are done, run:
  - docker-compose down
```

<br/>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<h2> :electric_plug: API Endpoints <a id="api-endpoints"></a></h2>

<details>
    <summary>Stock Market Service API Docs (click to expand)</summary>

### Object Definitions
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
 <summary><code>StockSummary</code></summary>

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
 <summary><code>Stock</code></summary>

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
 <summary><code>DetailedStock</code></summary>

```json
{
  
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

### Market Endpoints

> <code>GET</code> <code><b>/api/v1/market</b></code> <code><b>200 OK -> Market</b></code>

### Simulation Endpoints
> <code>GET</code> <code><b>/api/v1/market/sim/price_history?days={String}&stocks={int}</b></code> <code><b>200 OK -> List[SimulatedStock]</b></code>

### Stock Endpoints

> <code>GET</code> <code><b>/api/v1/stocks/{ticker}</b></code> <code><b>200 OK -> StockSummary</b></code>
>
> <code>GET</code> <code><b>/api/v1/stocks</b></code> <code><b>200 OK -> List[StockSummary]</b></code>
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

</details>

<br/>


<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :notebook: Data Simulation & Market Performance Metrics <a id="results"></a>

### Stock Price Simulation

Below is a chart that uses the market simulation endpoint with 100 stocks over 30 days to display price changes. The simulation is seperate from that actual market
as it does not retrieve stock info from the database, but uses the same price change formula to re-enact the real market simulation

![Line Chart](https://github.com/user-attachments/assets/2f5b306d-a74f-4320-9e44-2f103dc63f03)

The current calculation method is as followed:
```diff
NP = P + (P * R) + (P * (R * V)) + (M * PR)

NP = New Price
P -> Original Price
R -> Random Number. For these simulations the number weres -.0015 to .0015 and -.002 to .002
V -> Volatility of the stock. Values of 0 to 4
M -> Momentum of the stock. Values of -2 to 2
PR -> Positive random number up to .002
```

#### The Benchmark

A common way to randomly simulate stock data is the model of [Geometric Brownian Motion](https://demonstrations.wolfram.com/GeometricBrownianMotionWithNonuniformTimeGrid/). An example of this can be seen below, with the same price and duration as my simulations:

![Line Chart 2](https://user-images.githubusercontent.com/105665813/196005304-e35a47d3-c9a5-4750-801d-64796f326ce3.png)

### Market Performance Metrics

Using the log-parser python script, this chart shows the duration of each market interval (aka how long it takes for the program to change prices and save them to the
database)

Y-Axis shows the number of times the market advanced intervals, with the X-Axis being the time range of that interval

![Bar Chart](https://github.com/user-attachments/assets/5d15f231-940c-4965-b0ef-ce0ea9dff8c6)

