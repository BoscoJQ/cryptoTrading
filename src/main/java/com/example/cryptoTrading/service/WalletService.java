package com.example.cryptoTrading.service;

import com.example.cryptoTrading.entity.Wallet;

public interface WalletService {
    Wallet getWalletByUserId(Long userId);
}
