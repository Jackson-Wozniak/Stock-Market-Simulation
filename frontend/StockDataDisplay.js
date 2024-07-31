function stockPrices(){
    const data = fetch('http://localhost:8000/api/v1/market/sim/price_history?stocks=100')
    .then(res => res.json())
    .then(data => {
        if(myChart == null){
            Object.keys(data[0]).forEach(date => dates.push(date));
        }

        data.forEach(obj => {
            let prices = [];
            Object.keys(obj).forEach(date => {
                const value = obj[date];
                prices.push(value);
            });

            let randomColor = getRandomColor();
            datasets.push({
                data: prices,
                borderColor: randomColor,
                backgroundColor: randomColor
            });
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
const datasets = [];
let myChart = null;

function createChart(){
    let min = 80//datasets[0].data[0] * .8;
    let max = 120//datasets[0].data[0] * 1.2;
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
            borderWidth : 2,
            scales: {
                y: {
                    min: 80,
                    max: 120,
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

function getRandomColor(){
    return "#" + Math.floor(Math.random()*16777215).toString(16);
}
