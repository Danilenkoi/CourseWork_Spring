package ru.skypro.coursework.coursework_spring.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
public class BidModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bidder_name", nullable = false, length = 60)
    private String bidderName;

    @Column(name = "date", nullable = false)
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime bidDate;

    @Column(name = "lot_id")
    private int lotId;

    public BidModel(int lotId, String bidderName) {
        this.bidderName = bidderName;
        this.lotId = lotId;
    }

    public BidModel() {
    }

    public int getId() {
        return id;
    }

    public String getBidderName() {
        return bidderName;
    }

    public int getLotId() {
        return lotId;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public LocalDateTime getBidDate() {
        return bidDate;
    }

}
