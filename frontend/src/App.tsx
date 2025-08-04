import { BrowserRouter, Routes, Route } from "react-router-dom"
import StockSimulatorDashboard from "./components/stock-simulator/StockSimulatorDashboard"
import MarketDashboard from "./components/market/MarketDashboard"

function App(){
    return (
        <BrowserRouter>
            <Routes>
                <Route index element={<MarketDashboard/>} />
                <Route path="simulator" element={<StockSimulatorDashboard/>} />
            </Routes>
        </BrowserRouter>
    )
}

export default App
