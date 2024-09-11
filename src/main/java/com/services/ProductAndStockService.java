package com.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.entities.Product;
import com.entities.ProductWithoutStock;
import com.entities.ProductsGroupedByStock;
import com.entities.Stock;

public interface ProductAndStockService {
	
	List<Product> getAllProducts();
	
	void createStock(Stock stock);

	List<ProductWithoutStock> getAllProductsFromOneStock(int idStock);
	
	void saveProduct(int stock_id);	// will recieve a new product with existing stock
	
	List<ProductsGroupedByStock> getProductsGroupedByStock();
}
