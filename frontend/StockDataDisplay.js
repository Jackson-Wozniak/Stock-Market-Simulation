function getData(){
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
    const ctx = document.querySelector('#myChart').getContext('2d');
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

const commonColors = [
    "#FF5733", // Red Orange
    "#33FF57", // Lime Green
    "#3357FF", // Blue
    "#FF33A8", // Pink
    "#FFD700", // Gold
    "#FF6347", // Tomato
    "#8A2BE2", // Blue Violet
    "#FF4500", // Orange Red
    "#2E8B57", // Sea Green
    "#FF1493", // Deep Pink
    "#1E90FF", // Dodger Blue
    "#32CD32", // Lime Green
    "#FFD700", // Gold
    "#DAA520", // Goldenrod
    "#FF8C00", // Dark Orange
    "#BA55D3", // Medium Orchid
];

function getRandomColor(){
    return "#" + Math.floor(Math.random()*16777215).toString(16);
}

