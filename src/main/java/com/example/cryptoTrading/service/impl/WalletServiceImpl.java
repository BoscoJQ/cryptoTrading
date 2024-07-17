package com.example.cryptoTrading.service.impl;

import com.example.cryptoTrading.entity.Wallet;
import com.example.cryptoTrading.dao.WalletRepository;
import com.example.cryptoTrading.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }
}
