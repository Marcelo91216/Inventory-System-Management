package com.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Product;
import com.entities.Stock;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByStock(Stock stock);
	
	List<Product> findByOrderByStartAtAsc();
	
	List<Product> findByOrderByStartAtDesc();
}
