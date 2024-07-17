package com.example.cryptoTrading.service;

import com.example.cryptoTrading.entity.AggregatedPrice;

public interface PriceAggregationService {

    AggregatedPrice getBestAggregatedPrice(String cryptoPair);
}
