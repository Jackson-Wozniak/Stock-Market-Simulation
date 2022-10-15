console.log(getRandomColor());
const data = fetch('http://localhost:8080/api/v1/stocks/all/detailed')
    .then(res => res.json())
    .then(data => {
        console.log(data);
        console.log(data[0]);
        data[0].stockHistory.forEach(history => {
            dates.push(history.marketDate);
        })
        data.forEach(stock => {
            console.log(stock.stockHistory);
            if(stock.marketCap != "Large"){
                return;
            }
            stock.stockHistory.forEach(history => {
                prices.push(history.stockPrice);
            });
            datasets.push({
                label: stock.ticker,
                data: prices,
                borderColor: getRandomColor()
            });
            prices = [];
        });  
        console.log(prices);
        createChart();
        return data;
    });

let dates = [];
let prices = [];
const datasets = [];

function createChart(){
    const ctx = document.querySelector('#myChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dates,
            datasets: datasets
        },
        options: {
            scales: {
                y: {
                    suggestedMin: 90,
                    suggestedMax: 110
                }
            }
        }
    });
}
function getRandomColor(){
    return "#" + Math.floor(Math.random()*16777215).toString(16);
}

