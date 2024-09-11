package com.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.entities.Product;
import com.entities.ProductWithoutStock;
import com.entities.Stock;
import com.repos.ProductRepository;
import com.repos.StockRepository;

import jakarta.persistence.TypedQuery;
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
	@Transactional
	public List<ProductWithoutStock> getAllProductsFromOneStock(int idStock) throws RuntimeException{
		Optional<Stock> stock = stockRepo.findById(idStock);
		if(stock.isEmpty())
			throw new RuntimeException();
		List<Product> temp = productRepo.findByStock(stock.get());
		List<ProductWithoutStock> res= temp.stream()
				.map(p -> {
					return new ProductWithoutStock(p.getId(), p.getStartAt());
				})
				.toList();
		return res;
	}

	@Override
	public void saveProduct(int stock_id) throws RuntimeException{
		Optional<Stock> stock = stockRepo.findById(stock_id);
		if(stock.isEmpty())
			throw new RuntimeException();
		productRepo.save(new Product(0, stock.get(), LocalDateTime.now()));
	}

}
