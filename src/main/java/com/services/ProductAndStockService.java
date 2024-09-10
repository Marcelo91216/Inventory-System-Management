package com.services;

import java.util.List;

import com.entities.Product;
import com.entities.ProductWithoutStock;
import com.entities.Stock;

public interface ProductAndStockService {
	List<Product> getAllProducts();
	void createStock(Stock stock);
	List<ProductWithoutStock> getAllProductsFromOneStock(int idStock);
	void saveProduct(Product product);	// will recieve a new product with existing stock
	
}
