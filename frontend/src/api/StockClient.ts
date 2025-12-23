import type { StockFullDTO } from "../types/StockDTOs";

export async function fetchFullStockByTicker(ticker: string): Promise<StockFullDTO>{
    const response = await fetch(`http://localhost:8000/api/v1/stocks/${ticker}?view=full`);

    const json = await response.json() as StockFullDTO;

    return json;
}