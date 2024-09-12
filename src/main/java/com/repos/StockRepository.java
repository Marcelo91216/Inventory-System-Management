package com.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Stock;

/**
 * Represents the repository subclass that will be injected by Spring JPA,
 * including vital functions of obtaining and updating data related to a Stock.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
public interface StockRepository extends JpaRepository<Stock, Integer> {
}
