
function getData(){
    const data = fetch('http://localhost:8080/api/v1/stocks/all/detailed')
    .then(res => res.json())
    .then(data => {
        if(myChart == null){
            data[0].stockHistory.forEach(history => {
                dates.push(history.marketDate);
            });     
        }
        data.forEach(stock => {
            // if(stock.marketCap != "Large"){
            //     return;
            // }
            stock.stockHistory.forEach(history => {
                prices.push(history.stockPrice);
            });
            //let randomColor = getRandomColor();
            datasets.push({
                label: stock.ticker,
                data: prices,
                borderColor: "#000000",
                backgroundColor: "#000000"
            });
            prices = [];
        });  
        if(myChart == null){
            myChart = createChart();
        }
        else{
            myChart.data.datasets = datasets;
        }
        return data;
    });
}

let dates = [];
let prices = [];
const datasets = [];
let myChart = null;

function createChart(){
    let min = datasets[0].data[0] * .8;
    let max = datasets[0].data[0] * 1.2;
    const ctx = document.querySelector('#myChart').getContext('2d');
    return myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dates,
            datasets: datasets
        },
        options: {
            borderWidth : 2,
            scales: {
                y: {
                    suggestedMin: min,
                    suggestedMax: max,
                    grid: {
                        borderColor: '#FF4500',
                        borderWidth : 3
                    },
                    ticks : {
                        color : '#FF4500',
                        font: {
                            size: 18,
                        }
                    }  
                },
                x: {
                    suggestedMin: min,
                    suggestedMax: max,
                    grid: {
                        borderColor: '#FF4500',
                        borderWidth : 3
                    },
                    ticks : {
                        color : '#FF4500',
                        font: {
                            size: 18,
                        }
                    }
                    
                }
            }
        }
    });
}
function getRandomColor(){
    return "#" + Math.floor(Math.random()*16777215).toString(16);
}

