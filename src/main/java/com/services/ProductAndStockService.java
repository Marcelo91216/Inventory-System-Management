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

/**
 * The product and stock service.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
public interface ProductAndStockService {

	/**
	 * Obtain all products.
	 * 
	 * @return A list of products.
	 */
	List<Product> getAllProducts();

	/**
	 * Create a new stock.
	 * 
	 * @param stock The stock to receive.
	 * @return Nothing.
	 */
	void createStock(Stock stock);

	/**
	 * Consult for all the products from only a single stock.
	 * 
	 * @param idStock Receive the ID of the stock.
	 * @return A list of products.
	 * @throws RuntimeException
	 */
	List<ProductWithoutStock> getAllProductsFromOneStock(int idStock);

	/**
	 * Can persist data of a new product onto the database.
	 * 
	 * @param stock_id The stock ID data.
	 * @return Nothing.
	 * @throws RuntimeException
	 */
	void saveProduct(int stock_id);

	/**
	 * Will consult a list of all the products, but grouped by their stock, in a way
	 * that a Front-End could see a JSON of a stock and it's products.
	 * 
	 * @return A list of products.
	 * @throws RuntimeException
	 */
	List<ProductsGroupedByStock> getProductsGroupedByStock();

	/**
	 * Will verify if the Product Native SQL class have a valid ID and a valid
	 * Stock, then convert it into a Product (JPA Version) to upload it into the
	 * database with correct data.
	 * 
	 * @param product The product that the user inputs
	 * @return Nothing
	 * @throws InvalidProductException
	 * @throws NotIDProductException
	 */
	void updateProduct(ProductSqlNative product) throws InvalidProductException, NotIDProductException;

	/**
	 * Obtain the product information.
	 * 
	 * @param id The ID of the product.
	 * @return A product.
	 */
	Product getProduct(int id);

	/**
	 * Remove a product from the database.
	 * 
	 * @param id The ID of the product to remove.
	 * @return Nothing.
	 */
	void removeProduct(int id);

	/**
	 * Get the list of all the products order by date, in ascending or descending
	 * order.
	 * 
	 * @param ord A boolean data that tells the order. False=Ascendant,
	 *            True=Descendant.
	 * @return A list of products.
	 */
	List<Product> getSortedProductsByDate(boolean ord);

	/**
	 * Return the list of products sorted by their stock availability, the user can
	 * determine the order with a boolean.
	 * 
	 * @param ord A boolean data that tells the order. False=Ascendant,
	 *            True=Descendant.
	 * @return A list of products.
	 */
	List<Product> getSortedProductsByStock(boolean ord);

	/**
	 * Removes the stock and all of its products related.
	 * 
	 * @param id The ID of the stock to remove.
	 * @return Nothing.
	 */
	void removeStock(int id);
}
