# StockMarketSimulator

UNFINISHED

# Overview

A server side implementation for a stock market and stock trading simulator

---

## Future or In Progress Features
* User accounts can be used to view current stock prices and trade stocks
* Stock Prices change dynamically on a time interval. Price changes are largely random but are affected by certain stock attributes
* Each individual stock can be tracked, and prices/attributes change dynamically

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

```diff

Note: {___} in url represents path variable

* Specific Stock By Name: GET | http://localhost:8080/api/v1/stocks/{ticker}

* Specific Stock Price: GET | http://localhost:8080/api/v1/stocks/price/{ticker}

* All Stocks: GET | http://localhost:8080/api/v1/stocks/all

* Stocks By Sector: GET | http://localhost:8080/api/v1/stocks/sector/{sector}

* Stocks By Market Cap: GET | http://localhost:8080/api/v1/stocks/marketCap/{marketCap}

```

```JSON
[ {
  "ticker" : "AAPL",
  "companyName" : "Apple",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 99.742,
  "lastDayPrice" : 99.457,
  "optimism" : -1,
  "volatileStock" : false
}, {
  "ticker" : "AMZN",
  "companyName" : "Amazon",
  "sector" : "Technology",
  "marketCap" : "Large",
  "price" : 99.298,
  "lastDayPrice" : 99.065,
  "optimism" : 1,
  "volatileStock" : true
}, {
  "ticker" : "BRK.B",
  "companyName" : "Berkshire Hathaway",
  "sector" : "Insurance",
  "marketCap" : "Large",
  "price" : 100.85,
  "lastDayPrice" : 101.274,
  "optimism" : 1,
  "volatileStock" : false
}, {
  "ticker" : "COST",
  "companyName" : "Costco Wholesale",
  "sector" : "Consumer Discretionary",
  "marketCap" : "Large",
  "price" : 98.023,
  "lastDayPrice" : 97.999,
  "optimism" : -1,
  "volatileStock" : false
} ]

```

---

### Accounts

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
