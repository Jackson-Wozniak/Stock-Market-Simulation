package stocktradingsimulator.stock.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    @JsonBackReference
    private Stock stock;

    @Column(name = "news_event")
    private String event;

    @Column(name = "date_released")
    private String dateReleased;

    public News(Stock stock, String event, String dateReleased){
        this.stock = stock;
        this.event = event;
        this.dateReleased = dateReleased;
    }
}
