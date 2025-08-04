import { BrowserRouter, Routes, Route } from "react-router-dom"
import StockSimulatorDashboard from "./components/simulator-dashboard/StockSimulatorDashboard"
import MarketDashboard from "./components/market-dashboard/MarketDashboard"

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
