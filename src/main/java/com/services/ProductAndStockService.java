/**
 * @author guill
 * */

package com.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.entities.Product;
import com.entities.ProductSqlNative;
import com.entities.ProductWithoutStock;
import com.entities.ProductsGroupedByStock;
import com.entities.Stock;
import com.exceptions.InvalidProductException;
import com.exceptions.NotIDProductException;

public interface ProductAndStockService {
	
	List<Product> getAllProducts();
	
	void createStock(Stock stock);

	List<ProductWithoutStock> getAllProductsFromOneStock(int idStock);
	
	void saveProduct(int stock_id);
	
	/**
	 * @return List<ProductsGroupedByStock>
	 * */
	List<ProductsGroupedByStock> getProductsGroupedByStock();
	
	void updateProduct(ProductSqlNative product) throws InvalidProductException, NotIDProductException;
	
	Product getProduct(int id);
	
	void removeProduct(int id);
	
	List<Product> getSortedProductsByDate(boolean ord);
}
