
export interface MarketStateDTO{
    date: string,
    running: boolean,
    currentIntervalMs: number,
    trajectory: string,
}

export interface MarketState{
    date: Date,
    running: boolean,
    currentIntervalMs: number,
    trajectory: string,
}

export function mapDTO(dto: MarketStateDTO): MarketState{
    return {
        date: new Date(dto.date),
        running: dto.running,
        currentIntervalMs: dto.currentIntervalMs,
        trajectory: dto.trajectory,
    }
}