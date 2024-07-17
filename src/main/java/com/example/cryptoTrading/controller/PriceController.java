package com.example.cryptoTrading.controller;

import com.example.cryptoTrading.entity.AggregatedPrice;
import com.example.cryptoTrading.service.PriceAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/price")
public class PriceController {
    private final PriceAggregationService priceAggregationService;

    @Autowired
    public PriceController(PriceAggregationService priceAggregationService) {
        this.priceAggregationService = priceAggregationService;
    }

    @GetMapping("/latest")
    public AggregatedPrice getLatestPrice(@RequestParam String cryptoPair) {
        return priceAggregationService.getBestAggregatedPrice(cryptoPair);
    }


}
