package com.example.cryptoTrading.service.impl;


import com.example.cryptoTrading.entity.AggregatedPrice;
import com.example.cryptoTrading.entity.Trade;
import com.example.cryptoTrading.entity.User;
import com.example.cryptoTrading.entity.Wallet;
import com.example.cryptoTrading.dao.TradeRepository;
import com.example.cryptoTrading.dao.UserRepository;
import com.example.cryptoTrading.dao.WalletRepository;
import com.example.cryptoTrading.service.PriceAggregationService;
import com.example.cryptoTrading.service.TradeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PriceAggregationService priceAggregationService;

    @Autowired
    public TradeServiceImpl(TradeRepository tradeRepository, UserRepository userRepository, WalletRepository walletRepository, PriceAggregationService priceAggregationService) {
        this.tradeRepository = tradeRepository;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.priceAggregationService = priceAggregationService;
    }

    @Override
    public List<Trade> getTradesByUserId(Long userId) {
        return tradeRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Trade executeTrade(Long userId, String cryptoPair, String type, Double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Wallet wallet = walletRepository.findByUserId(userId);
        AggregatedPrice aggregatedPrice = priceAggregationService.getBestAggregatedPrice(cryptoPair);
        Double price = type.equals("BUY") ? aggregatedPrice.getAskPrice() : aggregatedPrice.getBidPrice();

        if (type.equals("BUY") && wallet.getUsdtBalance() < price * amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        if (type.equals("SELL")) {
            if (cryptoPair.equals("BTCUSDT") && wallet.getBtcBalance() < amount) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            if (cryptoPair.equals("ETHUSDT") && wallet.getEthBalance() < amount) {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }

        Trade trade = new Trade();
        trade.setUser(user);
        trade.setCryptoPair(cryptoPair);
        trade.setType(type);
        trade.setPrice(price);
        trade.setAmount(amount);
        trade.setTimestamp(LocalDateTime.now());

        tradeRepository.save(trade);

        if (type.equals("BUY")) {
            wallet.setUsdtBalance(wallet.getUsdtBalance() - price * amount);
            if (cryptoPair.equals("BTCUSDT")) {
                wallet.setBtcBalance(wallet.getBtcBalance() + amount);
            }
            if (cryptoPair.equals("ETHUSDT")) {
                wallet.setEthBalance(wallet.getEthBalance() + amount);
            }
        }

        if (type.equals("SELL")) {
            wallet.setUsdtBalance(wallet.getUsdtBalance() + price * amount);
            if (cryptoPair.equals("BTCUSDT")) {
                wallet.setBtcBalance(wallet.getBtcBalance() - amount);
            }
            if (cryptoPair.equals("ETHUSDT")) {
                wallet.setEthBalance(wallet.getEthBalance() - amount);
            }
        }

        walletRepository.save(wallet);

        return trade;
    }
}
