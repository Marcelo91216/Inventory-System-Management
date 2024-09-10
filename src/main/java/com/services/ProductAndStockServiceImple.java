package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Product;
import com.entities.ProductWithoutStock;
import com.entities.Stock;
import com.repos.ProductRepository;
import com.repos.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductAndStockServiceImple implements ProductAndStockService {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private StockRepository stockRepo;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	@Transactional
	public void createStock(Stock stock) {
		stockRepo.save(stock);
	}

	@Override
	public List<ProductWithoutStock> getAllProductsFromOneStock(int idStock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

}
