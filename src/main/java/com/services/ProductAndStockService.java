package com.services;

import java.util.List;

import com.entities.Product;
import com.entities.ProductWithoutStock;

public interface ProductAndStockService {
	List<Product> getAllProducts();
	void createStock();
	List<ProductWithoutStock> getAllProductsFromOneStock(int idStock);
	
}
