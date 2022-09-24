package stocktradingsimulator.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import stocktradingsimulator.indexfund.enums.FundTracking;
import stocktradingsimulator.indexfund.model.IndexFund;
import stocktradingsimulator.indexfund.utils.Capitalize;

@Getter
@Setter
public class SectorIndexFund extends IndexFund {

    private String sector;

    public SectorIndexFund(String sector, double price){
        super(Capitalize.capitalize(sector) + " Fund",
                price,
                FundTracking.SECTOR);
        this.sector = sector;
    }
}
