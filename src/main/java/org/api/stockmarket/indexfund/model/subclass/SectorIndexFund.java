package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.utils.Capitalize;

import jakarta.persistence.*;

@Entity
@Table(name = "sector_index_fund")
@NoArgsConstructor
@Getter
@Setter
public class SectorIndexFund extends IndexFund {

    @Column(name = "sector")
    private String sector;

    public SectorIndexFund(String sector, double price) {
        super(Capitalize.capitalize(sector) + " Fund",
                price,
                FundTracking.SECTOR);
        this.sector = sector;
    }
}
