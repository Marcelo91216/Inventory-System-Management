package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Product;
import com.entities.Stock;
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
}
