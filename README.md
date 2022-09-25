# Stock Market Simulator

A server side implementation for a stock market and stock trading simulator

# Table of Contents

1. [Features](#Features)

2. [Technologies](#Technologies)

3. [Market](#Market)

4. [Stocks](#Stocks)

5. [Index Funds](#Index-Funds)

6. [Accounts](#Accounts)

<br/>

# Features <a name="Features"></a>
* User accounts can be used to view current stock prices and trade stocks
* Individual stock information can be viewed in JSON format
* Stock Prices change dynamically on a time interval. Price changes are largely random but are affected by certain stock attributes, such as market cap
and optimism

Default stocks are based on real world companies, however their prices do not reflect real world data

<br/>

# Technologies <a name="Technologies"></a>
* Java Spring Boot
* Spring Data JPA
* MySQL
* Lombok
* JUNIT

<br/>

# API Overview

## Market <a name="Market"></a>

* The market date is tracked on intervals that can be altered. The default "day" is 24 intervals of 10 seconds, and 30 "days" is a month
* The date is formatted as month/day/year
* Stock prices change after each 10 second interval, and certain stock events can happen at the end of each day
* There are 3 market types; Bear, Bull and Normal. Bear markets occur if the average stock price falls 10% in a month, while 
bull markets happen if prices rise 10% monthly. Normal market conditions cover all scenarios in between

### Market Endpoints

* Market Conditions: GET | http://localhost:8080/api/v1/market
```JSON
{
  "id" : 1,
  "date" : "1/4/1",
  "lastMonthAveragePrice" : 100.0,
  "marketTrajectory" : "NORMAL"
}
```

<br/>

## Stocks <a name="Stocks"></a>

* Stock prices change on an interval (10 seconds)
* Stock prices change based on three factors: market cap, momentum, and volatility
* Market Cap: Large and small cap stocks experience higher movement, in an upward or downward trajectory
* Momentum: When stock prices rise for 3 days, they experience positive momentum, while they experience negative momentum if they fall for 3 days
* Volatility: Each stock is judged on whether it is volatile or not. This is an unchangeable boolean value, and is based on the nature of the real world company. Volatile stocks receive a slight increase in movement each time their prices change

### Stock Endpoints

Note: {___} in url represents path variable

* Specific Stock By Name: GET | http://localhost:8080/api/v1/stocks/{ticker}

```JSON
{
  "ticker" : "AAPL",
  "companyName" : "Apple",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 99.742,
  "lastDayPrice" : 99.457,
  "optimism" : -1,
  "volatileStock" : false
}
```

* Specific Stock Price: GET | http://localhost:8080/api/v1/stocks/price/{ticker}

```JSON
{
  104.36
}
```

* All Stocks: GET | http://localhost:8080/api/v1/stocks/all
The data below doesn't show all stocks, but shows the general format

```JSON
[ {
  "ticker" : "AAPL",
  "companyName" : "Apple",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 104.2,
  "lastDayPrice" : 103.01,
  "momentum" : 1,
  "momentumStreakInDays" : 5,
  "volatileStock" : false,
  "newsHistory" : [ ]
}, {
  "ticker" : "AMZN",
  "companyName" : "Amazon",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 104.66,
  "lastDayPrice" : 105.63,
  "momentum" : 1,
  "momentumStreakInDays" : 3,
  "volatileStock" : true,
  "newsHistory" : [ ]
}, {
  "ticker" : "BRK.B",
  "companyName" : "Berkshire Hathaway",
  "sector" : "Insurance",
  "marketCap" : "Large",
  "price" : 100.62,
  "lastDayPrice" : 100.87,
  "momentum" : 1,
  "momentumStreakInDays" : 3,
  "volatileStock" : false,
  "newsHistory" : [ ]
}]
```

* Stocks By Sector: GET | http://localhost:8080/api/v1/stocks/sector/{sector}

```JSON
[ {
  "ticker" : "AAPL",
  "companyName" : "Apple",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 103.94,
  "lastDayPrice" : 104.09,
  "momentum" : 1,
  "momentumStreakInDays" : 6,
  "volatileStock" : false,
  "newsHistory" : [ ]
}, {
  "ticker" : "AMZN",
  "companyName" : "Amazon",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 105.66,
  "lastDayPrice" : 105.29,
  "momentum" : 0,
  "momentumStreakInDays" : 2,
  "volatileStock" : true,
  "newsHistory" : [ ]
}, {
  "ticker" : "GME",
  "companyName" : "GameStop Corp",
  "sector" : "Technology",
  "marketCap" : "Mid",
  "price" : 20.32,
  "lastDayPrice" : 20.38,
  "momentum" : 1,
  "momentumStreakInDays" : 4,
  "volatileStock" : true,
  "newsHistory" : [ ]
}, {
  "ticker" : "GOOG",
  "companyName" : "Google",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 100.58,
  "lastDayPrice" : 100.58,
  "momentum" : 0,
  "momentumStreakInDays" : -2,
  "volatileStock" : false,
  "newsHistory" : [ ]
}, {
  "ticker" : "SLAB",
  "companyName" : "Silicon Laboratories",
  "sector" : "Technology",
  "marketCap" : "Mid",
  "price" : 19.69,
  "lastDayPrice" : 19.67,
  "momentum" : -2,
  "momentumStreakInDays" : -3,
  "volatileStock" : false,
  "newsHistory" : [ ]
}, {
  "ticker" : "TSLA",
  "companyName" : "Tesla",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 98.08,
  "lastDayPrice" : 98.24,
  "momentum" : -2,
  "momentumStreakInDays" : -4,
  "volatileStock" : true,
  "newsHistory" : [ ]
} ]
```

* Stocks By Market Cap: GET | http://localhost:8080/api/v1/stocks/marketCap/{marketCap}

```JSON
[ {
  "ticker" : "GME",
  "companyName" : "GameStop Corp",
  "sector" : "Technology",
  "marketCap" : "Mid",
  "price" : 20.34,
  "lastDayPrice" : 20.38,
  "momentum" : 1,
  "momentumStreakInDays" : 4,
  "volatileStock" : true,
  "newsHistory" : [ ]
}, {
  "ticker" : "OWL",
  "companyName" : "Big Owl Capital",
  "sector" : "Finance",
  "marketCap" : "Mid",
  "price" : 19.67,
  "lastDayPrice" : 19.65,
  "momentum" : -2,
  "momentumStreakInDays" : -4,
  "volatileStock" : false,
  "newsHistory" : [ ]
}, {
  "ticker" : "SLAB",
  "companyName" : "Silicon Laboratories",
  "sector" : "Technology",
  "marketCap" : "Mid",
  "price" : 19.69,
  "lastDayPrice" : 19.67,
  "momentum" : -2,
  "momentumStreakInDays" : -3,
  "volatileStock" : false,
  "newsHistory" : [ ]
} ]
```

<br/>

## Index Funds <a name="Index-Funds"></a>
 
* Index funds track the average price of a specific category of stocks
* These cannot be traded, but only serve to estimate the total market trajectory

### Index Fund Endpoints

Note: {___} in url represents path variable

* Total Market Fund: GET | http://localhost:8080/api/v1/funds/total-market
```JSON
{
  "name" : "Total Market ETF",
  "price" : 78.44,
  "fundTracking" : "TOTAL_MARKET"
}
```
* Index Fund By Market Cap: GET | http://localhost:8080/api/v1/funds/cap/{marketCap}
```JSON
{
  "name" : "Large Cap Index Fund",
  "price" : 101.0,
  "fundTracking" : "MARKET_CAP",
  "marketCap" : "large"
}
```
* Index Fund By Sector: GET | http://localhost:8080/api/v1/funds/sector/{sector}
```JSON
{
  "name" : "Technology Fund",
  "price" : 74.86,
  "fundTracking" : "SECTOR",
  "sector" : "technology"
}
```
* Stable Fund (Non Volatile): GET | http://localhost:8080/api/v1/funds/stable
```JSON
{
  "name" : "Stable Index Fund",
  "price" : 86.02,
  "fundTracking" : "VOLATILITY",
  "volatility" : false
}
```
* Volatile Fund: GET | http://localhost:8080/api/v1/fund/volatile
```JSON
{
  "name" : "Volatile Index Fund",
  "price" : 57.3,
  "fundTracking" : "VOLATILITY",
  "volatility" : true
}
```

<br/>

## Accounts <a name="Accounts"></a>

* Get Account By Name : GET | http://localhost:8080/api/v1/account/get/{username}

```JSON
{
  "username" : "username",
  "accountBalance" : "1000",
  "stocksOwned" : [ {
    "ticker" : "AMZN" ,
    "amountOwned" : 2
  }, {
    "ticker" : "GOOG",
    "amountOwned" : 5
  } ]
}
```

* Create Account : POST | http://localhost:8080/api/v1/account/create/{username}

* Deposit Funds To Account: POST | http://localhost:8080/api/v1/account/deposit
