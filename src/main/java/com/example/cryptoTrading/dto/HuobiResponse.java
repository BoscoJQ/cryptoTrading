package com.example.cryptoTrading.dto;

import java.util.List;

public class HuobiResponse {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String symbol;
        private double bid;
        private double ask;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getBid() {
            return bid;
        }

        public void setBid(double bid) {
            this.bid = bid;
        }

        public double getAsk() {
            return ask;
        }

        public void setAsk(double ask) {
            this.ask = ask;
        }
    }
}
