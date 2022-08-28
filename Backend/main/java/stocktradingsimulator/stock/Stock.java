package stocktradingsimulator.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "stock")
@Table(name = "stock")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Stock {

    @Id
    private String ticker;

    @Column(name = "name")
    private String companyName;

    @Column(name = "sector")
    private String sector;

    @Column(name = "cap")
    private String marketCap;

    @Column(name = "price")
    private Double price;

    @Column(name = "last_day_price")
    private Double lastDayPrice;

    @Column(name = "optimism")
    private Integer optimism;

    @Column(name = "volatile")
    private Boolean volatileStock;
}
