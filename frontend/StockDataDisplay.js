const CUSTOM_SIM_URL = 'http://localhost:8000/api/v1/market/sim'
const STABLE = {
    'days' : 90,
    'momentum' : 0,
    'volatility' : 'Stable',
    'rating' : 'Neutral'
}

const NEGATIVE_VOLATILE = {
    'days' : 90,
    'momentum' : -1,
    'volatility' : 'Volatile',
    'rating' : 'Sell'
}

const POSITIVE_VOLATILE = {
    'days' : 90,
    'momentum' : 1,
    'volatility' : 'Extra_Volatile',
    'rating' : 'Buy'
}

const NEUTRAL_VOLATILE = {
    'days' : 90,
    'momentum' : 0,
    'volatility' : 'Volatile',
    'rating' : 'Neutral'
}


const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

let dates = [];
let datasets = [];
let prices = []
let myChart = null;

let STOCKS = [NEGATIVE_VOLATILE, POSITIVE_VOLATILE, STABLE, NEUTRAL_VOLATILE];
let stockIndex = 0;

async function stockPrices(){
    const data = await fetch(CUSTOM_SIM_URL, {
        method: 'POST',
        body: JSON.stringify(STOCKS[stockIndex++]),
        headers : myHeaders
    });

    if (!data.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const json = await data.json();

    if (myChart === null) {
        Object.entries(json).forEach(([date, price]) => {
            dates.push(date);
            prices.push(price);
        });
        for(let i = 0; i < prices.length; i++){
            let randomColor = getRandomColor();
            datasets.push({
                data: prices,
                borderColor: randomColor,
                backgroundColor: randomColor
            });
        }
        myChart = createChart();
    } else {
        Object.entries(json).forEach(([date, price]) => {
            prices.push(price);
            dates.push(date);
        });

        let randomColor = getRandomColor();

        datasets.push({
            label: `Stock ${datasets.length + 1}`,
            data: [...prices],
            borderColor: randomColor,
            backgroundColor: randomColor,
            fill: false,
            borderWidth: 4
        });

        myChart.data.labels = dates;
        myChart.data.datasets = datasets;
        myChart.update();
    }
    prices = []
    dates = []
}

function createChart(){
    const ctx = document.querySelector('#line-chart').getContext('2d');
    return myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dates,
            datasets: datasets
        },
        options: {
            plugins: {
                legend: {
                    display: false
                }
            },
            elements: {
                point:{
                    radius: 0
                }
            },
            animation : false,
            borderWidth : 3,
            scales: {
                y: {
                    min: 70,
                    max: 130,
                    grid: {
                        borderColor: '#FBFCF8',
                        borderWidth : 2
                    },
                    ticks : {
                        color : '#FBFCF8',
                        font: {
                            size: 18,
                        }
                    }  
                },
                x: {
                    grid: {
                        borderColor: '#FBFCF8',
                        borderWidth : 3
                    },
                    ticks : {
                        color : '#FBFCF8',
                        font: {
                            size: 18,
                        }
                    }
                    
                }
            }
        }
    });
}

let colors = ['#0000FF', '#00FF00', '#FF0000', '#A020F0'];

function getRandomColor(){
    //return colors[stockIndex - 1];
    return "#" + Math.floor(Math.random()*16777215).toString(16);
}
