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
    <li><a href="#technologies">Technologies</a></li>
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

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :iphone: Technologies <a id="technologies"></a>

* Java Spring Boot
* Spring Data JPA
* MySQL
* Lombok
* JUNIT

<br/> 

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## 📝: Contributing <a id="contributing"></a>

For all info on contributing, please head to [this](https://github.com/Jackson-Wozniak/Stock-Market-Simulation/blob/main/CONTRIBUTING.md) document

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
<details>
<summary>
  <h2> :electric_plug: API Endpoints <a id="api-endpoints"></a></h2><p>(click to see details)</p>
</summary>

  ## :calendar: Market <a id="market"></a>

* The market date is tracked on intervals that can be altered. The default "day" is 24 intervals of 10 seconds, and 30 "days" is a month
* The date is formatted as month/day/year
* Stock prices change after each 10 second interval, and certain stock events can happen at the end of each day
* There are 3 market types; Bear, Bull and Normal. Bear markets occur if the average stock price falls 10% in a month, while 
bull markets happen if prices rise 10% monthly. Normal market conditions cover all scenarios in between

#### :arrow_right: Market Endpoints

<details>
  <summary>Market Conditions: GET | http://localhost:8080/api/v1/market</summary>
  <p>
    
```JSON
{
  "id" : 1,
  "date" : "1/4/1",
  "lastMonthAveragePrice" : 100.0,
  "marketTrajectory" : "NORMAL"
}
```
  </p>
  
</details>
<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :dollar: Stocks <a id="stocks"></a>

* Stock prices change on an interval (10 seconds)
* Stock prices change based on three factors: market cap, momentum, and volatility
* Market Cap: Large and small cap stocks experience higher movement, in an upward or downward trajectory
* Momentum: When stock prices rise for 3 days, they experience positive momentum, while they experience negative momentum if they fall for 3 days
* Volatility: Each stock is judged on whether it is volatile or not. This is an unchangeable boolean value, and is based on the nature of the real world company. Volatile stocks receive a slight increase in movement each time their prices change

#### :arrow_right: Stock Endpoints

Note: {___} in url represents path variable

<details>
<summary>Specific Stock By Name: GET | http://localhost:8080/api/v1/stocks/{ticker}</summary>
  <p>
    
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
    
  </p>
</details>

<details>
  <summary>Specific Stock Price: GET | http://localhost:8080/api/v1/stocks/price/{ticker}</summary>
  <p>
    
```JSON
{
  104.36
}
```
  
  </p>
</details>
  
<details>  
  <summary>All Stocks: GET | http://localhost:8080/api/v1/stocks</summary>
The data below doesn't show all stocks, but shows the general format
  <p>
  
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

  </p>
</details>  

<details>  
  <summary>Detailed Stock Data: GET | http://localhost:8080/api/v1/stocks/detailed</summary>
The data below doesn't show all stocks, but shows the general format
  <p>
  
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

  </p>
</details> 

<details>
  <summary>Stocks By Sector: GET | http://localhost:8080/api/v1/stocks/sector/{sector}</summary>
  <p>

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

  </p>
</details>

<details>
  <summary>Stocks By Market Cap: GET | http://localhost:8080/api/v1/stocks/marketCap/{marketCap}</summary>
  <p>

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
    
  </p>
</details>  

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :chart_with_upwards_trend: Stock History <a id="stock-history"></a>

* Stock prices are saved each day, and the history of a stocks price can be viewed
* Price history is reset at the end of each year

#### :arrow_right: Stock History Endpoints

<details>
  <summary>Stock History By Ticker Symbol: GET | http://localhost:8080/api/v1/stocks/history/{ticker}</summary>
  <p>
  
  ```JSON
  [ {
  "marketDate" : "7/7/1",
  "ticker" : "AMZN",
  "stockPrice" : 97.8
}, {
  "marketDate" : "7/8/1",
  "ticker" : "AMZN",
  "stockPrice" : 98.66
}, {
  "marketDate" : "7/9/1",
  "ticker" : "AMZN",
  "stockPrice" : 101.1
}, {
  "marketDate" : "7/10/1",
  "ticker" : "AMZN",
  "stockPrice" : 101.79
}, {
  "marketDate" : "7/11/1",
  "ticker" : "AMZN",
  "stockPrice" : 102.92
}, {
  "marketDate" : "7/12/1",
  "ticker" : "AMZN",
  "stockPrice" : 101.48
}, {
  "marketDate" : "7/13/1",
  "ticker" : "AMZN",
  "stockPrice" : 102.53
}, {
  "marketDate" : "7/14/1",
  "ticker" : "AMZN",
  "stockPrice" : 102.04
}, {
  "marketDate" : "7/15/1",
  "ticker" : "AMZN",
  "stockPrice" : 102.59
}, {
  "marketDate" : "7/16/1",
  "ticker" : "AMZN",
  "stockPrice" : 100.57
}, {
  "marketDate" : "7/17/1",
  "ticker" : "AMZN",
  "stockPrice" : 100.79
} ]
  ```
  
  </p>
</details>  
  
<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :newspaper: Stock News <a id="Stock-News"></a>

* At the end of each day, there is a chance that a specific stock will release a news story, which will have a large effect on their price
* Positive news, such as buyouts, will increase the stocks by around price 10%
* Negative news, such as lawsuits or management shakeups will decrease stock price by around 10%
* Bankruptcies will occur if a stock price dips below $1, where a buyout will occur and the stocks price will reset back to the default

#### :arrow_right: News Endpoints

Note: {___} in url represents path variable

<details>
  <summary>All News On The Market: GET | http://localhost:8080/api/v1/news</summary>
  <p>
    
```JSON
[ {
  "event" : "Charles Schwab Corporation announces buyout of small Finance company. There price soared as a result",
  "dateReleased" : "1/2/1"
}, {
  "event" : "Lawsuit announced against GameStop today. Investigations are ongoing.",
  "dateReleased" : "2/15/1"
} ]
```

  </p>
</details>
<details>
  <summary>All News On A Specific Stock: GET | http://localhost:8080/api/v1/news/{ticker}</summary>
  <p>
    
```JSON
[ {
  "event" : "Charles Schwab Corporation announces buyout of small Finance company. There price soared as a result",
  "dateReleased" : "1/2/1"
} ]
```
    
  </p>
</details>  

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


## :heavy_dollar_sign: Stock Earnings Reports <a id="Stock-Earnings"></a>

* Stocks release earnings reports on the first day of every 3rd month (3rd, 6th, 9th, 12th)
* Earnings reports effect stock prices and optimism, and are also affected by previous optimism

#### :arrow_right: Earnings Reports Endpoints

Note: {___} in url represents path variable


<details>
  <summary>All Earnings Reports History: GET | http://localhost:8080/api/v1/earnings</summary>
  <p>
  
```JSON
[ {
  "estimatedEPS" : 2.03,
  "actualEPS" : 3.3,
  "reportMessage" : "Date: 1/1/1. Apple announces increased profits in new earnings report today, causing a spike in their stock price. Their EPS of 3.3 exceeded expectations of 2.03 EPS.",
  "dateOfRelease" : "3/1/1"
}, {
  "estimatedEPS" : 2.84,
  "actualEPS" : 4.16,
  "reportMessage" : "Date: 1/1/1. Amazon announces increased profits in new earnings report today, causing a spike in their stock price. Their EPS of 4.16 exceeded expectations of 2.84 EPS.",
  "dateOfRelease" : "6/1/1"
}, {
  "estimatedEPS" : 2.68,
  "actualEPS" : 4.06,
  "reportMessage" : "Date: 1/1/1. Berkshire Hathaway announces increased profits in new earnings report today, causing a spike in their stock price. Their EPS of 4.06 exceeded expectations of 2.68 EPS.",
  "dateOfRelease" : "3/1/1"
}, {
  "estimatedEPS" : 2.72,
  "actualEPS" : 3.09,
  "reportMessage" : "Date: 1/1/1. Costco Wholesale announces stable profits in new earnings report today. Their EPS of 3.09 fell in line with expectations of 2.72 EPS.",
  "dateOfRelease" : "12/1/1"
}]
```    
    
  </p>
</details>

  
<details>
  <summary>All Earnings Report History Of A Specific Stock: GET | http://http://localhost:8080/api/v1/earnings/stock/{ticker}</summary>
    <p>
    
```JSON
[ {
  "estimatedEPS" : 2.84,
  "actualEPS" : 4.16,
  "reportMessage" : "Date: 1/1/1. Amazon announces increased profits in new earnings report today, causing a spike in their stock price. Their EPS of 4.16 exceeded expectations of 2.84 EPS.",
  "dateOfRelease" : "3/1/1"
}, {
  "estimatedEPS" : 2.72,
  "actualEPS" : 3.09,
  "reportMessage" : "Date: 1/1/1. Amazon announces stable profits in new earnings report today. Their EPS of 3.09 fell in line with expectations of 2.72 EPS.",
  "dateOfRelease" : "6/1/1"
} ]
```
    
   </p>
</details>  

<details>
  <summary>All Earnings Report History On A Date: GET | http://http://localhost:8080/api/v1/earnings/date/{date}</summary>
  <p>
    
Date is formatted as month_day_year and will return an error if incorrectly formatted
```JSON
[ {
  "estimatedEPS" : 2.03,
  "actualEPS" : 3.3,
  "reportMessage" : "Date: 1/1/1. Apple announces increased profits in new earnings report today, causing a spike in their stock price. Their EPS of 3.3 exceeded expectations of 2.03 EPS.",
  "dateOfRelease" : "3/1/1"
}, {
  "estimatedEPS" : 2.84,
  "actualEPS" : 4.16,
  "reportMessage" : "Date: 1/1/1. Amazon announces increased profits in new earnings report today, causing a spike in their stock price. Their EPS of 4.16 exceeded expectations of 2.84 EPS.",
  "dateOfRelease" : "3/1/1"
}, {
  "estimatedEPS" : 2.68,
  "actualEPS" : 4.06,
  "reportMessage" : "Date: 1/1/1. Berkshire Hathaway announces increased profits in new earnings report today, causing a spike in their stock price. Their EPS of 4.06 exceeded expectations of 2.68 EPS.",
  "dateOfRelease" : "3/1/1"
} ]
```
    
  </p>
</details>  

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->


## :bar_chart: Index Funds <a id="index-funds"></a>
 
* Index funds track the average price of a specific category of stocks
* These cannot be traded, but only serve to estimate the total market trajectory

#### :arrow_right: Index Fund Endpoints

Note: {___} in url represents path variable

<details>
  <summary>Total Market Fund: GET | http://localhost:8080/api/v1/funds/total-market</summary>
  <p>
    
```JSON
{
  "name" : "Total Market ETF",
  "price" : 78.44,
  "fundTracking" : "TOTAL_MARKET"
}
```
    
  </p>
</details>

<details>
  <summaryIndex Fund By Market Cap: GET | http://localhost:8080/api/v1/funds/cap/{marketCap}</summary>
  <p>
    
```JSON
{
  "name" : "Large Cap Index Fund",
  "price" : 101.0,
  "fundTracking" : "MARKET_CAP",
  "marketCap" : "large"
}
```
    
  </p>
</details>

<details>
  <summary>Index Fund By Sector: GET | http://localhost:8080/api/v1/funds/sector/{sector}</summary>
  <p>
    
```JSON
{
  "name" : "Technology Fund",
  "price" : 74.86,
  "fundTracking" : "SECTOR",
  "sector" : "technology"
}
```
    
  </p>
</details>

<details>
  <summary>Stable Fund (Non Volatile): GET | http://localhost:8080/api/v1/funds/stable</summary>
  <p>
    
```JSON
{
  "name" : "Stable Index Fund",
  "price" : 86.02,
  "fundTracking" : "VOLATILITY",
  "volatility" : false
}
```
    
  </p>
</details>

<details>
  <summary>Volatile Fund: GET | http://localhost:8080/api/v1/fund/volatile</summary>
  <p>
    
```JSON
{
  "name" : "Volatile Index Fund",
  "price" : 57.3,
  "fundTracking" : "VOLATILITY",
  "volatility" : true
}
```
    
  </p>
</details>  

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :credit_card: Trading & Accounts <a id="trading"></a>

#### :arrow_right: Trading & Account Endpoints
<details>
  <summary>Get Account By Name : GET | http://localhost:8080/api/v1/account/{username}</summary>
  <p>

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
    
  </p>
</details>  

<details>
  <summary>Get Account History  By Name : GET | http://localhost:8080/api/v1/account/history/{username}</summary>
  <p>

```JSON
[ {
  "date" : "3/20/1",
  "balance" : -8.06
}, {
  "date" : "3/21/1",
  "balance" : -8.06
}, {
  "date" : "3/22/1",
  "balance" : -8.06
}, {
  "date" : "3/23/1",
  "balance" : 3.4
}, {
  "date" : "3/24/1",
  "balance" : 4.3
}, {
  "date" : "3/25/1",
  "balance" : 4.3
}, {
  "date" : "3/26/1",
  "balance" : 4.3
}, {
  "date" : "3/27/1",
  "balance" : 4.3
}, {
  "date" : "3/28/1",
  "balance" : 4.3
} ]
```


<details>
  <summary>Get Active Limit Orders By Name : GET | http://localhost:8080/api/v1/inventory/orders/get/{username}</summary>
</details> 
    
  </p>
</details>  

<details>
  <summary>Create Account : POST | http://localhost:8080/api/v1/account/{username}</summary>
</details>

<details>
  <summary>Deposit Funds To Account: POST | http://localhost:8080/api/v1/account/deposit</summary>
</details>

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :trophy: Leaderboard <a id="leaderboard"></a>

* A leaderboard tracks total user profits for each account, sorting them by how much each account earns
* Trading stocks for a profit will push you up the leaderboard, with the goal of beating a trading bot

#### :arrow_right: Leaderboard Endpoints
<details>
  <summary>Get Top 10 User Accounts On Leaderboard | http://localhost:8080/api/v1/leaderboard</summary>
  <p>
  
  ```JSON
 [ {
  "ranking" : 1,
  "username" : "user2",
  "totalProfits" : 4.5
}, {
  "ranking" : 2,
  "username" : "user3",
  "totalProfits" : 4.3
}, {
  "ranking" : 3,
  "username" : "user1",
  "totalProfits" : 1.23
}, {
  "ranking" : 4,
  "username" : "user4",
  "totalProfits" : -2.00
}, {
  "ranking" : 5,
  "username" : "user6",
  "totalProfits" : -4.3
}, {
  "ranking" : 6,
  "username" : "user6",
  "totalProfits" : -12.64
}, {
  "ranking" : 7,
  "username" : "user8",
  "totalProfits" : -16.23
}, {
  "ranking" : 8,
  "username" : "user7",
  "totalProfits" : -20.00
}, {
  "ranking" : 9,
  "username" : "user10",
  "totalProfits" : -40.3
}, {
  "ranking" : 10,
  "username" : "user9",
  "totalProfits" : -122.64
} ]

```
  
  </p>
</details>

<details>
  <summary>Get Top 3 User Accounts On Leaderboard | http://localhost:8080/api/v1/leaderboard</summary>
  <p>
  
  ```JSON
[ {
  "ranking" : 1,
  "username" : "user2",
  "totalProfits" : 4.5
}, {
  "ranking" : 2,
  "username" : "user3",
  "totalProfits" : 4.3
}, {
  "ranking" : 3,
  "username" : "user1",
  "totalProfits" : 1.23
} ]
  ```
  
  </p>
</details>

<details>
  <summary>Get Ranking By Account: GET | http://localhost:8080/api/v1/leaderboard/ranking/{username}</summary>
  <p>
  
  ```JSON
  {
    "ranking" : 2,
    "username" : "user3",
    "totalProfits" : 4.3
  }
  ```
  
  </p>
</details> 
</details>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## :notebook: Results & Data From Simulation <a id="results"></a>

### Current Stock Price Simulation

Below is a chart where prices are simulated four times using the current method. Each simulation is one month in duration and their movements are overlayed

![Result_Overlay_4_Large_Cap](https://user-images.githubusercontent.com/105665813/196005634-671f4912-5b00-4f71-a6d3-e1a6cea8c1ba.png)

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
It's worth noting that the large flucuations betweens single days are new announcements that affect stock prices

### The Benchmark

A common way to randomly simulate stock data is the model of [Geometric Brownian Motion](https://demonstrations.wolfram.com/GeometricBrownianMotionWithNonuniformTimeGrid/). An example of this can be seen below, with the same price and duration as my simulations:

![Screenshot (51)](https://user-images.githubusercontent.com/105665813/196005304-e35a47d3-c9a5-4750-801d-64796f326ce3.png)
