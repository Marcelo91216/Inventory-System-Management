package com.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.entities.Product;
import com.entities.ProductSqlNative;
import com.entities.ProductWithoutStock;
import com.entities.ProductsGroupedByStock;
import com.entities.Stock;
import com.exceptions.InvalidProductException;
import com.exceptions.NotIDProductException;
import com.repos.ProductRepository;
import com.repos.StockRepository;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * The product and stock service implementation.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@Service
public class ProductAndStockServiceImple implements ProductAndStockService {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private StockRepository stockRepo;

//	Consult for all the products
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
	public List<ProductWithoutStock> getAllProductsFromOneStock(int idStock) throws RuntimeException {
		Optional<Stock> stock = stockRepo.findById(idStock);
		if (stock.isEmpty())
			throw new RuntimeException();
		List<Product> temp = productRepo.findByStock(stock.get());
		List<ProductWithoutStock> res = temp.stream().map(p -> {
			return new ProductWithoutStock(p.getId(), p.getStartAt());
		}).toList();
		return res;
	}

	@Override
	@Transactional
	public void saveProduct(int stock_id) throws RuntimeException {
		Optional<Stock> stock = stockRepo.findById(stock_id);
		if (stock.isEmpty())
			throw new RuntimeException();
		productRepo.save(new Product(0, stock.get(), LocalDateTime.now()));
	}

	@Override
	public List<ProductsGroupedByStock> getProductsGroupedByStock() throws RuntimeException {
		Stream<Stock> stocks = stockRepo.findAll().stream();
		return stocks.map(s -> {
			List<ProductWithoutStock> products = productRepo.findByStock(s).stream().map(ProductWithoutStock::new)
					.toList();
			return new ProductsGroupedByStock(s, products);
		}).toList();
	}

	@Override
	@Transactional
	public void updateProduct(ProductSqlNative product) throws InvalidProductException, NotIDProductException {
		try {
			if (product.getStartAt() == null)
				throw new InvalidProductException("The information was not correct");
			Optional<Product> findIt = productRepo.findById(product.getId());
			if (findIt.isEmpty())
				throw new NotIDProductException("The product doesn’t exist");
			Optional<Stock> stock = stockRepo.findById(product.getStockid());
			if (stock.isEmpty())
				throw new InvalidProductException("The information was not correct");
			productRepo.save(new Product(product.getId(), stock.get(), product.getStartAt()));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Product getProduct(int id) {
		try {
			Optional<Product> product = productRepo.findById(id);
			if (product.isEmpty())
				throw new RuntimeException();
			return product.get();
		} catch (Exception e) {
			throw new RuntimeException("");
		}
	}

	@Override
	@Transactional
	public void removeProduct(int id) {
		try {
			if (!productRepo.existsById(id))
				throw new RuntimeException("The product doesn't exist");
			productRepo.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Product> getSortedProductsByDate(boolean ord) {
		List<Product> res = null;
		if (ord)
			res = productRepo.findByOrderByStartAtDesc();
		else
			res = productRepo.findByOrderByStartAtAsc();
		return res;
	}

	@Override
	public List<Product> getSortedProductsByStock(boolean ord) {
		List<Product> res = productRepo.findAll().stream().sorted((p1, p2) -> {
			if (ord)
				return (int) (productRepo.countByStock(p2.getStock()) - productRepo.countByStock(p1.getStock()));
			else
				return (int) (productRepo.countByStock(p1.getStock()) - productRepo.countByStock(p2.getStock()));
		}).toList();
		return res;
	}

	@Override
	@Transactional
	public void removeStock(int id) {
		try {
			Optional<Stock> stock = stockRepo.findById(id);
			if (stock.isEmpty())
				throw new RuntimeException("The stock doesn't exist!");
			productRepo.deleteByStock(stock.get());
			stockRepo.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}
}
