package com.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
