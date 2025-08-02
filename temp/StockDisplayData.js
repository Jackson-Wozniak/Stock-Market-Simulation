const CUSTOM_SIM_URL = 'http://localhost:8000/api/v1/market/sim/testing'

const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

let dates = [];
let datasets = [];
let prices = []
let myChart = null;
let stockIndex = 0;

async function stockPrices(){
    const data = await fetch(CUSTOM_SIM_URL, {
        method: 'GET',
        headers : myHeaders
    });

    if (!data.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const json = await data.json();

    if (myChart === null) {
        dates = json[0].map(entry => Object.keys(entry)[0]);

        json.forEach(map => {
            map.map(entry => Object.entries(entry)[0][1]).forEach(val => prices.push(val));

            let randomColor = getRandomColor();
            datasets.push({
                label: `Stock ${datasets.length + 1}`,
                data: [...prices],
                borderColor: randomColor,
                backgroundColor: randomColor,
                fill: false,
                borderWidth: 2
            });
            prices = []
            return;
        })
        myChart = createChart();
    }else{
        json.forEach(map => {
            map.map(entry => Object.entries(entry)[0][1]).forEach(val => prices.push(val));

            let randomColor = getRandomColor();
            datasets.push({
                label: `Stock ${datasets.length + 1}`,
                data: [...prices],
                borderColor: randomColor,
                backgroundColor: randomColor,
                fill: false,
                borderWidth: 2
            });
            prices = []
            return;
        })

        myChart.data.labels = dates;
        myChart.data.datasets = datasets;
        myChart.update();
    }
    prices = [];
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
                    min: 0,
                    max: 200,
                    grid: {
                        borderColor: '#FBFCF8',
                        borderWidth : 4
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
                        borderWidth : 4
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

let index = 0;
function getRandomColor(){
    //return colors[stockIndex - 1];
    let i = index;
    index += 1;
    if(i == 3) index = 0;
    return colors[i];
}