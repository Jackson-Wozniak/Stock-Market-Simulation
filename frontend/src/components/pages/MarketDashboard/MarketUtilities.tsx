import Box from "@mui/material/Box";
import { useEffect, useState } from "react";
import { mapDTO, type MarketState, type MarketStateDTO } from "../../../types/MarketDTOs";
import { fetchMarketState, fetchPauseMarket, fetchResumeMarket, fetchUpdateInterval } from "../../../api/MarketClient";
import { Button, CircularProgress, IconButton, Typography } from "@mui/material";
import PauseIcon from '@mui/icons-material/Pause';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import FastForwardIcon from '@mui/icons-material/FastForward';

const dateFormatter = new Intl.DateTimeFormat("en-US", {
    weekday: "long",
    month: "long",
    day: "numeric",
    year: "numeric",
    timeZone: "UTC",
});

const timeFormatter = new Intl.DateTimeFormat("en-US", {
    hour: "2-digit",
    minute: "2-digit",
    hour12: true,
    timeZone: "UTC",
});

const DEFAULT_INTERVAL = 10_000;

const MarketUtilities: React.FC = () => {
    const [marketState, setMarketState] = useState<MarketState | undefined>();

    useEffect(() => {
        const intervalMs = marketState?.currentIntervalMs ?? DEFAULT_INTERVAL;
        const intervalId = setInterval(pollMarket, intervalMs);

        return () => clearInterval(intervalId);
    }, [marketState?.currentIntervalMs]);

    const updateMarketStatus = async () => {
        if(marketState == null) return;

        if(marketState.running){
            fetchPauseMarket().then((res: MarketStateDTO) => setMarketState(mapDTO(res)));
        }else{
            fetchResumeMarket().then((res: MarketStateDTO) => setMarketState(mapDTO(res)));
        }
    }

    const updateMarketInterval = async (doIncrease: boolean) => {
        if(marketState == null) return;

        if(doIncrease){
            fetchUpdateInterval(marketState.currentIntervalMs + 500).then((res: MarketStateDTO) => setMarketState(mapDTO(res)));
        }else{
            fetchUpdateInterval(marketState.currentIntervalMs - 500).then((res: MarketStateDTO) => setMarketState(mapDTO(res)));
        }
    }

    const pollMarket = async () => {
        fetchMarketState().then((res: MarketStateDTO) => setMarketState(mapDTO(res)));
    }

    if(marketState == null){
        return <CircularProgress/>
    }

    return (
        <Box sx={{width: "100%", height: "20%"}}>
            <Typography>{dateFormatter.format(marketState.date)}</Typography>
            <Typography>{timeFormatter.format(marketState.date)}</Typography>
            <Typography>Market runs every {marketState.currentIntervalMs / 1000} seconds</Typography>
            <IconButton onClick={() => updateMarketInterval(false)}><FastForwardIcon sx={{ transform: 'scaleX(-1)' }} /></IconButton>
            <IconButton onClick={updateMarketStatus}>
                {marketState.running ? (<PauseIcon/>) : <PlayArrowIcon/>}
            </IconButton>
            <IconButton onClick={() => updateMarketInterval(true)}><FastForwardIcon/></IconButton>
        </Box>
    )
}

export default MarketUtilities;