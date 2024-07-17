package com.example.cryptoTrading.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "aggregated_price")
public class AggregatedPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cryptoPair;

    @Column(nullable = false)
    private Double bidPrice;

    @Column(nullable = false)
    private Double askPrice;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public AggregatedPrice() {}

    public AggregatedPrice(Long id, String cryptoPair, Double bidPrice, Double askPrice, LocalDateTime timestamp) {
        this.id = id;
        this.cryptoPair = cryptoPair;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.timestamp = timestamp;
    }

    public AggregatedPrice(String cryptoPair, Double bidPrice, Double askPrice, LocalDateTime timestamp) {
        this.cryptoPair = cryptoPair;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getCryptoPair() {
        return cryptoPair;
    }

    public void setCryptoPair(String cryptoPair) {
        this.cryptoPair = cryptoPair;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AggregatedPrice{" +
                "id=" + id +
                ", cryptoPair='" + cryptoPair + '\'' +
                ", bidPrice=" + bidPrice +
                ", askPrice=" + askPrice +
                ", timestamp=" + timestamp +
                '}';
    }
}
