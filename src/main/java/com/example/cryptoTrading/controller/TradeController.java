package com.example.cryptoTrading.controller;

import com.example.cryptoTrading.dto.TradeRequest;
import com.example.cryptoTrading.entity.Trade;
import com.example.cryptoTrading.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/trade")
public class TradeController {
    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/execute")
    public Trade executeTrade(@RequestBody TradeRequest tradeRequest) {
        return tradeService.executeTrade(tradeRequest.getUserId(), tradeRequest.getCryptoPair(), tradeRequest.getType(), tradeRequest.getAmount());
    }
}
