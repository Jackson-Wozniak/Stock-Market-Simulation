import Page from "../shared/page-defaults/Page";
import 'chart.js/auto'
import { Line } from 'react-chartjs-2';
import { useEffect, useState } from 'react';

const CUSTOM_SIM_URL = 'http://localhost:8000/api/v1/market/sim/testing';

const colors = ['#0000FF', '#00FF00', '#FF0000', '#A020F0'];

function getColor(index: number) {
  return colors[index % colors.length];
}

type StockData = Record<string, number>;

const StockSimulatorDashboard: React.FC<{}> = ({}) => {
    const [chartData, setChartData] = useState<any>(null);

    useEffect(() => {
        const fetchStockData = async () => {
          try {
            const res = await fetch(CUSTOM_SIM_URL, {
              method: 'GET',
              headers: {
                'Content-Type': 'application/json',
              },
            });

            if (!res.ok) {
              throw new Error(`HTTP error! Status: ${res.status}`);
            }

            const json: StockData[][] = await res.json();

            const labels = json[0].map(entry => Object.keys(entry)[0]);

            const datasets = json.map((stock, i) => {
              const data = stock.map(entry => Object.values(entry)[0]);
              const color = getColor(i);
              return {
                label: `Stock ${i + 1}`,
                data,
                borderColor: color,
                backgroundColor: color,
                fill: false,
                borderWidth: 2,
              };
            });

            setChartData({
              labels,
              datasets,
            });
          } catch (error) {
            console.error('Fetch failed:', error);
          }
        };
        fetchStockData();
    }, []);

    const options: any = {
        responsive: true,
        animation: false,
        plugins: {
          legend: {
            display: false,
          },
        },
        elements: {
          point: {
            radius: 0,
          },
        },
        scales: {
          y: {
            min: 0,
            max: 300,
            grid: {
              borderColor: "#121212",
              borderWidth: 4,
            },
            ticks: {
              color: "#121212",
              font: {
                size: 18,
              },
            },
          },
          x: {
            grid: {
              borderColor: "#121212",
              borderWidth: 4,
            },
            ticks: {
              color: "#121212",
              font: {
                size: 18,
              },
            },
          },
        },
    };

    return (
        <Page>
            {chartData ? (<Line data={chartData} options={options} />) : (
                <p style={{ color: '#FBFCF8' }}>Loading chart...</p>
            )}
        </Page>
    )
}

export default StockSimulatorDashboard;