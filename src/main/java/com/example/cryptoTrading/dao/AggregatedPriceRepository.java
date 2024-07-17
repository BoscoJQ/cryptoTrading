package com.example.cryptoTrading.dao;

import com.example.cryptoTrading.entity.AggregatedPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {
    AggregatedPrice findTopByCryptoPairOrderByTimestampDesc(String cryptoPair);
}
