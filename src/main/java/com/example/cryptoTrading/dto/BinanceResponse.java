package com.example.cryptoTrading.dto;

public class BinanceResponse {
    private String symbol;
    private String bidPrice;
    private String askPrice;

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getBidPrice() {
        return Double.parseDouble(bidPrice); // Convert to double
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getAskPrice() {
        return Double.parseDouble(askPrice); // Convert to double
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }
}
