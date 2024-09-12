package com.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.entities.Product;
import com.entities.ProductSqlNative;
import com.entities.ProductWithoutStock;
import com.entities.ProductsGroupedByStock;
import com.entities.Stock;
import com.exceptions.InvalidProductException;
import com.exceptions.NotIDProductException;
import com.services.ProductAndStockService;

/**
 * The main controller of the Spring Boot application, which handles all the
 * request/respond functionality.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@RestController
@RequestMapping("/")
public class Controller {
	/**
	 * The service that will make the operations.
	 */
	@Autowired
	private ProductAndStockService service;

	/**
	 * Get all products in a list.
	 * 
	 * @return A list of products.
	 */
	@GetMapping("/products")
	private List<Product> getAllProducts() {
		return service.getAllProducts();
	}

	/**
	 * Insert a new stock.
	 * 
	 * @param stock A new stock object.
	 * @return Response entity, with it's HTTP code and a body.
	 */
	@PostMapping("/stocks")
	private ResponseEntity<String> insertStock(@RequestBody Stock stock) {
		try {
			if (stock.getName().isEmpty()) {
				throw new RuntimeException("The name was empty!!");
			}
			if (stock.getDesc().isEmpty())
				stock.setDesc("No description");
			service.createStock(stock);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A name was not recieved");
		}
		return ResponseEntity.status(HttpStatus.OK).body("The stock was included correctly");
	}

	/**
	 * Get all products from a selected stock.
	 * 
	 * @param stock_id The ID for the stock to be selected.
	 * @return A list of products without it's stock.
	 */
	@GetMapping("/stocks/{stock_id}")
	private List<ProductWithoutStock> allProductsFromStock(@PathVariable int stock_id) {
		List<ProductWithoutStock> res = null;
		try {
			res = service.getAllProductsFromOneStock(stock_id);
			if (res == null)
				throw new RuntimeException("Throw error");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That stock doesn't exist!");
		}
		return res;
	}

	/**
	 * Upload a new product to the system.
	 * 
	 * @param stock_id The ID of the stock where you want to place your new product.
	 * @return Response entity, with it's HTTP code and a body.
	 */
	@PostMapping("/products/{stock_id}")
	private ResponseEntity<String> saveNewProduct(@PathVariable int stock_id) {
		try {
			service.saveProduct(stock_id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("The creation was successful!");
	}

	/**
	 * A group of products classified by their stock.
	 * 
	 * @return A list of products.
	 */
	@GetMapping("/stocks/grouped")
	private List<ProductsGroupedByStock> groupProductsByStock() {
		List<ProductsGroupedByStock> res = null;
		try {
			res = service.getProductsGroupedByStock();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An unexpected error has ocurred!");
		}
		return res;
	}

	/**
	 * Modify an already existing product.
	 * 
	 * @param product An already existing product with SQL original data.
	 * @return Response entity, with it's HTTP code and a body.
	 */
	@PutMapping("/products/update")
	private ResponseEntity<String> modifyExistingProduct(@RequestBody ProductSqlNative product) {
		try {
			service.updateProduct(product);
		} catch (NotIDProductException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (InvalidProductException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unexpected error!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("The product was updated successfully");
	}

	/**
	 * Obtain an existent product.
	 * 
	 * @param id Identification number.
	 * @return A product.
	 */
	@GetMapping("/products/product/{id}")
	private Product getProduct(@PathVariable int id) {
		try {
			return service.getProduct(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * Remove an existent product from the database.
	 * 
	 * @param id Identification number of the product.
	 * @return Response entity, with it's HTTP code and a body.
	 */
	@DeleteMapping("/products/delete/{id}")
	private ResponseEntity<String> removeProduct(@PathVariable int id) {
		try {
			service.removeProduct(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Successfully removed");
	}

	/**
	 * Get a sorted list of products by start date.
	 * 
	 * @param ord The type of sorting the user wants. False = Ascendant, True =
	 *            Descendant.
	 * @return A list of products.
	 */
	@GetMapping("/products/sorted/{ord}")
	private List<Product> sortedProductsByDate(@PathVariable boolean ord) {
		try {
			return service.getSortedProductsByDate(ord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * Obtain a list of products sorted by stock.
	 * 
	 * @param ord The type of sorting the user wants. False = Ascendant, True =
	 *            Descendant.
	 * @return A list of products.
	 */
	@GetMapping("/products/sortedByStock/{ord}")
	private List<Product> sortedProductsByStock(@PathVariable boolean ord) {
		try {
			return service.getSortedProductsByStock(ord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	/**
	 * Remove a stock with all of its products.
	 * 
	 * @param id The ID of the Stock to be removed.
	 * @return Response entity, with it's HTTP code and a body.
	 */
	@DeleteMapping("/stocks/delete/{id}")
	private ResponseEntity<String> removeStock(@PathVariable int id) {
		try {
			service.removeStock(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("The stock and it's products were eliminated!");
	}
}
