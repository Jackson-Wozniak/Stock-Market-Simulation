import { Box } from "@mui/material";
import Page from "../../layout/Page";
import MarketUtilities from "./MarketUtilities";
import StockDisplay from "./StockDisplay";

const MarketDashboard: React.FC = () => {
    return (
        <Page>
            <Box sx={{
                width: "100%", height: "100%", display: "flex", flexDirection: "column",
                alignItems: "center", justifyContent: "center"
            }}>
                <MarketUtilities/>
                <StockDisplay/>
            </Box>
        </Page>
    )
}

export default MarketDashboard;