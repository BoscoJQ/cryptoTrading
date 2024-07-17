package com.example.cryptoTrading.dto;

public class TradeRequest {
    private Long userId;
    private String cryptoPair;
    private String type;
    private Double amount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
