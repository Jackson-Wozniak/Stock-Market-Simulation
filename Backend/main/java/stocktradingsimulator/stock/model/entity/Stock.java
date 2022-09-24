package stocktradingsimulator.stock.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "momentum")
    private Integer momentum;

    @Column(name = "momentum_streak")
    private Integer momentumStreakInDays;

    @Column(name = "volatile")
    private Boolean volatileStock;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<News> newsHistory;
}