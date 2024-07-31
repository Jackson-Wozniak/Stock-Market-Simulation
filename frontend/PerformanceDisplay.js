const DURATION_FILE_PATH = "log-parser/duration_stats.txt"

function performanceMetrics(){
    fetch(DURATION_FILE_PATH)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            generateBarChart(data)
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
    });
}

function generateBarChart(durations){
    const labels = Object.keys(durations);
    const data = {
      labels: labels,
      datasets: [{
        label: 'Performance Metrics',
        data: Object.values(durations),
        backgroundColor: [
          'rgba(35, 101, 51, 0.6)',
          'rgba(0, 16, 100, 0.6)',
          'rgba(255, 102, 0, .6)',
          'rgba(217, 33, 33, .6)',
        ],
        borderColor: [
          'rgb(35, 101, 51)',
          'rgb(0, 16, 100)',
          'rgb(255, 102, 0)',
          'rgb(217, 33, 33)',
        ],
        borderWidth: 4
      }]
    };

    const ctx = document.querySelector('#bar-chart').getContext('2d');
    return myChart = new Chart(ctx, {
        type: 'bar',
        data: data,
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
                    startAtZero: true,
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
                        },
                        autoSkip: false,
                        maxRotation: 40,
                        minRotation: 40
                    }
                    
                }
            }
        }
    });
}
