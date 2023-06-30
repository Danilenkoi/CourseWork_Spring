package ru.skypro.coursework.coursework_spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import ru.skypro.coursework.coursework_spring.status.LotStatus;
@Entity
@Table(name = "lot")
@DynamicUpdate
public class LotModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private LotStatus status;
    @Size(min = 3, max = 64)
    @Column(name = "title", nullable = false, length = 60)
    private String title;
    @Size(min = 1, max = 4096)
    @Column(name = "description", nullable = false)
    private String description;
    @Positive
    @Column(name = "start_price", nullable = false)
    private int startPrice;
    @Positive
    @Column(name = "bid_price", nullable = false)
    private int bidPrice;
    @Column(name = "current_price", nullable = false)
    private int currentPrice;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "last_bid_id")
    private BidModel lastBidModel;

    public LotModel(LotStatus status, String title, String description, int startPrice, int bidPrice) {
        this.status = status;
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.bidPrice = bidPrice;
        currentPrice = startPrice;
    }

    public LotModel() {

    }

    public void setLastBid(BidModel lastBidModel) {
        this.lastBidModel = lastBidModel;
    }

    public void setStatus(LotStatus status) {
        this.status = status;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getId() {
        return id;
    }

    public LotStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public BidModel getLastBid() {
        return lastBidModel;
    }

}
