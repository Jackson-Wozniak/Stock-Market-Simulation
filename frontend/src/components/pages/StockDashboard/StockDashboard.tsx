import { Box, CircularProgress, Typography } from "@mui/material";
import Page from "../../layout/Page";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import type { StockFullDTO } from "../../../types/StockDTOs";
import { fetchFullStockByTicker } from "../../../api/StockClient";

const StockDashboard: React.FC = () => {
    const { ticker } = useParams();
    const [stock, setStock] = useState<StockFullDTO | undefined>();

    useEffect(() => {
        const fetchStock = async () => {
            fetchFullStockByTicker(ticker!!).then((res: StockFullDTO) => setStock(res));
        }
        fetchStock();
    }, []);

    if(stock == null){
        return <CircularProgress/>
    }

    return (
        <Page>
            <Box sx={{
                width: "100%", height: "100%", display: "flex", flexDirection: "column",
                alignItems: "center", justifyContent: "center"
            }}>
                <Typography color="black">{stock.ticker}</Typography>
            </Box>
        </Page>
    )
}

export default StockDashboard;