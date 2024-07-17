package com.example.cryptoTrading.service.impl;

import com.example.cryptoTrading.dao.WalletRepository;
import com.example.cryptoTrading.entity.User;
import com.example.cryptoTrading.entity.Wallet;
import com.example.cryptoTrading.dao.UserRepository;
import com.example.cryptoTrading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        Wallet newWallet = new Wallet();
        newWallet.setUser(user);
        newWallet.setUsdtBalance(50000.0);
        walletRepository.save(newWallet);

        return user;
    }
}
