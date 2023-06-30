package ru.skypro.coursework.coursework_spring.lot;

import ru.skypro.coursework.coursework_spring.bid.Bid;
import ru.skypro.coursework.coursework_spring.status.LotStatus;
import ru.skypro.coursework.coursework_spring.model.BidModel;

public class FullLot {
    private int id;
    private LotStatus status;
    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;
    private int currentPrice;
    private Bid lastBid;

    public FullLot(int id, LotStatus status, String title, String description, int startPrice, int bidPrice, int currentPrice, BidModel lastBid) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.bidPrice = bidPrice;
        this.currentPrice = currentPrice;
        this.lastBid = new Bid(lastBid.getBidderName(), lastBid.getBidDate());
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

    public Bid getLastBid() {
        return lastBid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(LotStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setLastBid(Bid lastBid) {
        this.lastBid = lastBid;
    }

    @Override
    public String toString() {
        return "FullLot{" +
                "id=" + id +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", bidPrice=" + bidPrice +
                ", currentPrice=" + currentPrice +
                ", lastBid=" + lastBid +
                '}';
    }
}
