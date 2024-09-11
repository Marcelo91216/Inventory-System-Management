package com.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/")
public class Controller {
	@Autowired
	private ProductAndStockService service;
	
	@GetMapping("/products")
	private List<Product> getAllProducts(){
		return service.getAllProducts();
	}
	
	@PostMapping("/stocks")
	private ResponseEntity<String> insertStock(@RequestBody Stock stock) {
		try {
			if(stock.getName().isEmpty()) {
				throw new RuntimeException("The name was empty!!");
			}
			if(stock.getDesc().isEmpty())
				stock.setDesc("No description");
			service.createStock(stock);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A name was not recieved");
		}
		return ResponseEntity.status(HttpStatus.OK).body("The stock was included correctly");
	}
	
	@GetMapping("/stocks/{stock_id}")
	private List<ProductWithoutStock> allProductsFromStock(@PathVariable int stock_id){
		List<ProductWithoutStock> res=null;
		try {
			res = service.getAllProductsFromOneStock(stock_id);
			if(res==null)
				throw new RuntimeException("Throw error");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That stock doesn't exist!");
		}
		return res;
	}
	
	@PostMapping("/products/{stock_id}")
	private ResponseEntity<String> saveNewProduct(@PathVariable int stock_id){
		try {
			service.saveProduct(stock_id);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("The creation was successful!");
	}
	
	@GetMapping("/stocks/grouped")
	private List<ProductsGroupedByStock> groupProductsByStock(){
		List<ProductsGroupedByStock> res=null;
		try {
			res = service.getProductsGroupedByStock();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An unexpected error has ocurred!");
		}
		return res;
	}
	
	@PutMapping("/products/update")
	private ResponseEntity<String> modifyExistingProduct(@RequestBody ProductSqlNative product) {
		try {
			service.updateProduct(product);
		}
		catch(NotIDProductException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch(InvalidProductException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unexpected error!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("The product was updated successfully");
	}
	
	@GetMapping("/products/product/{id}")
	private Product getProduct(@PathVariable int id){
		try {
			return service.getProduct(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
