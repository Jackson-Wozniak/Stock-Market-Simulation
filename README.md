# StockMarketSimulator

A server side implementation for a stock market and stock trading simulator

---

## Features
* User accounts can be used to view current stock prices and trade stocks
* Individual stock information can be viewed in JSON format
* Stock Prices change dynamically on a time interval. Price changes are largely random but are affected by certain stock attributes, such as market cap
and optimism

Default stocks are based on real world companies, however their prices do not reflect real world data

---

## Technologies
* Java Spring Boot
* Spring Data JPA
* MySQL
* Lombok
* JUNIT

---

## API Endpoints

### Stocks

* Stock prices change on an interval (10 seconds). After 24 intervals pass, this is considered the end of the day and EOD prices are saved
* Stock prices change based on three factors: market cap, optimism, and volatility
* Market Cap: Large and small cap stocks experience higher movement, in an upward or downward trajectory
* Optimism: Stock optimism is graded each time a day ends. Stocks that saw a price increase since the last day receive postitive optimism, 
whereas those with price decreases receive negative optimism
* Volatility: Each stock is judged on whether it is volatile or not. This is an unchangeable boolean value, and is based on the nature of the real world company. Volatile stocks receive a slight increase in movement each time their prices change

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

```diff

Note: {___} in url represents path variable

* Specific Stock By Name: GET | http://localhost:8080/api/v1/stocks/{ticker}

* Specific Stock Price: GET | http://localhost:8080/api/v1/stocks/price/{ticker}

* All Stocks: GET | http://localhost:8080/api/v1/stocks/all

* Stocks By Sector: GET | http://localhost:8080/api/v1/stocks/sector/{sector}

* Stocks By Market Cap: GET | http://localhost:8080/api/v1/stocks/marketCap/{marketCap}

```

---

### Accounts

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

```diff

* Get Account By Name : GET | http://localhost:8080/api/v1/account/get/{username}

* Create Account : POST | http://localhost:8080/api/v1/account/create/{username}

* Deposit Funds To Account: POST | http://localhost:8080/api/v1/account/deposit

```

Deposit Funds Request Payload:

```JSON
{
    "username": "default",
    "amountToAdd" : 100000
}
```
