package com.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entities.Product;
import com.entities.Stock;

/**
 * Represents the repository subclass that will be injected by Spring JPA,
 * including vital functions of obtaining and updating data related to a
 * Product.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * Will find the list of products by it's stock.
	 * 
	 * @param stock The stock of the products.
	 * @return A list of products.
	 */
	List<Product> findByStock(Stock stock);

	/**
	 * Will find the list of products by it¿s start date in ascendant order.
	 * 
	 * @return A list of products.
	 */
	List<Product> findByOrderByStartAtAsc();

	/**
	 * Will find the list of products by it¿s start date in descendant order.
	 * 
	 * @return A list of products.
	 */
	List<Product> findByOrderByStartAtDesc();

	/**
	 * Make a count of all the stock actual capacity.
	 * 
	 * @param stock The stock that user wants to count.
	 * @return A long integer representing its capacity.
	 */
	long countByStock(Stock stock);

	/**
	 * Delete a product based on it's stock.
	 * 
	 * @param stock The stock that user wants to count.
	 * @return Nothing.
	 */
	void deleteByStock(Stock stock);
}
