package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.springjwt.models.StockData;

public interface StockRepository extends JpaRepository<StockData, Long> {

}
