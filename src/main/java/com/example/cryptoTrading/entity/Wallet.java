package com.example.cryptoTrading.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Double usdtBalance = 50000.0;

    @Column(nullable = false)
    private Double btcBalance = 0.0;

    @Column(nullable = false)
    private Double ethBalance = 0.0;

    public Wallet() {}

    public Wallet(Long id, User user, Double usdtBalance, Double btcBalance, Double ethBalance) {
        this.id = id;
        this.user = user;
        this.usdtBalance = usdtBalance;
        this.btcBalance = btcBalance;
        this.ethBalance = ethBalance;
    }

    public Wallet(User user, Double usdtBalance, Double btcBalance, Double ethBalance) {
        this.user = user;
        this.usdtBalance = usdtBalance;
        this.btcBalance = btcBalance;
        this.ethBalance = ethBalance;
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

    public Double getUsdtBalance() {
        return usdtBalance;
    }

    public void setUsdtBalance(Double usdtBalance) {
        this.usdtBalance = usdtBalance;
    }

    public Double getBtcBalance() {
        return btcBalance;
    }

    public void setBtcBalance(Double btcBalance) {
        this.btcBalance = btcBalance;
    }

    public Double getEthBalance() {
        return ethBalance;
    }

    public void setEthBalance(Double ethBalance) {
        this.ethBalance = ethBalance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", user=" + user +
                ", usdtBalance=" + usdtBalance +
                ", btcBalance=" + btcBalance +
                ", ethBalance=" + ethBalance +
                '}';
    }
}
