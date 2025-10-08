
## :books: Table of Contents

<ol>
    <li><a href="#design-overview">Market Engine Overview</a></li>
    <li><a href="#folder-structure">Folder Structure Guide</a></li>
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

<h2> 📂: Folder Structure Guide <a id="folder-structure"></a></h2>

```md
.
└── stock-market-service/src/org/api/stockmarket/
    ├── common/ -> holds generic tools and utils
    ├── engine/ -> the core of the app, including @Scheduled class to handle tick rate and market advancement
    └── modules/
        ├── indexfunds/
        ├── news/
        ├── simulatedmarket/ -> all in-memory market simulation interfaces
        └── stocks/
```

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


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

> <code>GET</code> <code><b>/api/v1/newsRelease/{ticker}</b></code> <code><b>200 OK -> List[News]</b></code>
>
> <code>GET</code> <code><b>/api/v1/newsRelease/</b></code> <code><b>200 OK -> List[News]</b></code>

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
