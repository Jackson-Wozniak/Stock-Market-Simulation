import { BrowserRouter, Routes, Route } from "react-router-dom"
import MarketDashboard from "./components/pages/Dashboard/MarketDashboard"

function App(){
    return (
        <BrowserRouter>
            <Routes>
                <Route index element={<MarketDashboard/>} />
            </Routes>
        </BrowserRouter>
    )
}

export default App
