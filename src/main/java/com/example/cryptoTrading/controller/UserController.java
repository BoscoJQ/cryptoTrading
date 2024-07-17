package com.example.cryptoTrading.controller;

import com.example.cryptoTrading.entity.Trade;
import com.example.cryptoTrading.entity.User;
import com.example.cryptoTrading.entity.Wallet;
import com.example.cryptoTrading.service.TradeService;
import com.example.cryptoTrading.service.UserService;
import com.example.cryptoTrading.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final TradeService tradeService;
    private final WalletService walletService;;
    private final UserService userService;

    @Autowired
    public UserController(TradeService tradeService, WalletService walletService, UserService userService) {
        this.tradeService = tradeService;
        this.walletService = walletService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody  User user) {
        return userService.createUser(user.getEmail(), user.getPassword());
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/{userId}/trades")
    public List<Trade> getTradingHistory(@PathVariable Long userId) {
        return tradeService.getTradesByUserId(userId);
    }

    @GetMapping("/{userId}/wallet")
    public Wallet getWallet(@PathVariable Long userId) {
        return walletService.getWalletByUserId(userId);
    }

}