import type { MarketStateDTO } from "../types/MarketDTOs";

export async function fetchMarketState(){
    const response = await fetch("http://localhost:8000/api/v1/market");

    const json = await response.json() as MarketStateDTO;

    return json;
}

export async function fetchPauseMarket(){
    const response = await fetch("http://localhost:8000/api/v1/market/pause", {
        method: "PUT"
    });

    const json = await response.json() as MarketStateDTO;

    return json;
}

export async function fetchResumeMarket(){
    const response = await fetch("http://localhost:8000/api/v1/market/resume", {
        method: "PUT"
    });

    const json = await response.json() as MarketStateDTO;

    return json;
}

export async function fetchUpdateInterval(interval: number){
    const response = await fetch("http://localhost:8000/api/v1/market/interval?millis=" + interval, {
        method: "PUT"
    });

    const json = await response.json() as MarketStateDTO;

    return json;
}