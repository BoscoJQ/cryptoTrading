package com.example.cryptoTrading.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String cryptoPair;

    @Column(nullable = false)
    private String type; // BUY or SELL

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Trade() {}

    public Trade(Long id, User user, String cryptoPair, String type, Double price, Double amount, LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.cryptoPair = cryptoPair;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Trade(User user, String cryptoPair, String type, Double price, Double amount, LocalDateTime timestamp) {
        this.user = user;
        this.cryptoPair = cryptoPair;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCryptoPair() {
        return cryptoPair;
    }

    public void setCryptoPair(String cryptoPair) {
        this.cryptoPair = cryptoPair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", user=" + user +
                ", cryptoPair='" + cryptoPair + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
