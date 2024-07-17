package com.example.cryptoTrading.service;

import com.example.cryptoTrading.entity.Trade;

import java.util.List;

public interface TradeService {
    List<Trade> getTradesByUserId(Long userId);
    Trade executeTrade(Long userId, String cryptoPair, String type, Double amount);
}
