import { BrowserRouter, Routes, Route } from "react-router-dom"
import MarketDashboard from "./components/pages/MarketDashboard/MarketDashboard"
import StockDashboard from "./components/pages/StockDashboard/StockDashboard"

function App(){
    return (
        <BrowserRouter>
            <Routes>
                <Route index element={<MarketDashboard/>} />
                <Route path="stocks/:ticker" element={<StockDashboard/>} />
            </Routes>
        </BrowserRouter>
    )
}

export default App
