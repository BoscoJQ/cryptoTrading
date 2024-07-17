package com.example.cryptoTrading.service.impl;

import com.example.cryptoTrading.dto.BinanceResponse;
import com.example.cryptoTrading.dto.HuobiResponse;
import com.example.cryptoTrading.entity.AggregatedPrice;
import com.example.cryptoTrading.dao.AggregatedPriceRepository;
import com.example.cryptoTrading.service.PriceAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PriceAggregationServiceImpl implements PriceAggregationService {

    private final AggregatedPriceRepository aggregatedPriceRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public PriceAggregationServiceImpl(AggregatedPriceRepository aggregatedPriceRepository) {
        this.aggregatedPriceRepository = aggregatedPriceRepository;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedRate = 10000)
    public void fetchAndStorePrices() {
        List<String> cryptoPairs = Arrays.asList("BTCUSDT", "ETHUSDT");

        for (String cryptoPair : cryptoPairs) {
            AggregatedPrice bestPrice = getBestAggregatedPriceForPair(cryptoPair);
            if (bestPrice != null) {
                bestPrice.setCryptoPair(cryptoPair);
                bestPrice.setTimestamp(LocalDateTime.now());
                aggregatedPriceRepository.save(bestPrice);
                System.out.println("Saved price for " + cryptoPair + ": " + bestPrice);
            } else {
                System.out.println("No price found for " + cryptoPair);
            }
        }
    }

    private AggregatedPrice getBestAggregatedPriceForPair(String cryptoPair) {
        String binanceUrl = "https://api.binance.com/api/v3/ticker/bookTicker";
        String huobiUrl = "https://api.huobi.pro/market/tickers";

        BinanceResponse[] binancePrices = restTemplate.getForObject(binanceUrl, BinanceResponse[].class);
        HuobiResponse huobiPrices = restTemplate.getForObject(huobiUrl, HuobiResponse.class);

        double binanceBid = Arrays.stream(binancePrices)
                .filter(p -> p.getSymbol().equalsIgnoreCase(cryptoPair))
                .mapToDouble(BinanceResponse::getBidPrice)
                .max()
                .orElse(Double.NaN);

        double binanceAsk = Arrays.stream(binancePrices)
                .filter(p -> p.getSymbol().equalsIgnoreCase(cryptoPair))
                .mapToDouble(BinanceResponse::getAskPrice)
                .min()
                .orElse(Double.NaN);

        double huobiBid = huobiPrices.getData().stream()
                .filter(p -> p.getSymbol().equalsIgnoreCase(cryptoPair.toLowerCase()))
                .mapToDouble(HuobiResponse.Data::getBid)
                .max()
                .orElse(Double.NaN);

        double huobiAsk = huobiPrices.getData().stream()
                .filter(p -> p.getSymbol().equalsIgnoreCase(cryptoPair.toLowerCase()))
                .mapToDouble(HuobiResponse.Data::getAsk)
                .min()
                .orElse(Double.NaN);

        double bestBid = Math.max(binanceBid, huobiBid);
        double bestAsk = Math.min(binanceAsk, huobiAsk);

        AggregatedPrice aggregatedPrice = new AggregatedPrice();
        aggregatedPrice.setCryptoPair(cryptoPair);
        aggregatedPrice.setBidPrice(bestBid);
        aggregatedPrice.setAskPrice(bestAsk);

        return aggregatedPrice;
    }

    @Override
    public AggregatedPrice getBestAggregatedPrice(String cryptoPair) {
        return aggregatedPriceRepository.findTopByCryptoPairOrderByTimestampDesc(cryptoPair);
    }
}



