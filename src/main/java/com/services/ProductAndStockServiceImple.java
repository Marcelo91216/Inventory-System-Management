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

	/**
	 * Create a new stock.
	 * @param Stock
	 * @return Void
	 * */
	@Override
	@Transactional
	public void createStock(Stock stock) {
		stockRepo.save(stock);
	}
	
	/**
	 * Consult for all the products from only a single stock.
	 * @param Integer
	 * @return List<ProductWithoutStock>
	 * @exception RuntimeException
	 * */
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

	/**
	 * Can persist data of a new product onto the database.
	 * @param Integer
	 * @return Void
	 * @exception RuntimeException
	 * */
	@Override
	@Transactional
	public void saveProduct(int stock_id) throws RuntimeException{
		Optional<Stock> stock = stockRepo.findById(stock_id);
		if(stock.isEmpty())
			throw new RuntimeException();
		productRepo.save(new Product(0, stock.get(), LocalDateTime.now()));
	}

	/**
	 * Will consult a list of all the products, but grouped by their stock, in a way that a Front-End could see a JSON of a stock and it's products.
	 * @return List<ProductsGroupedByStock>
	 * @exception RuntimeException
	 **/
	@Override
	public List<ProductsGroupedByStock> getProductsGroupedByStock()  throws RuntimeException{
		Stream<Stock> stocks = stockRepo.findAll().stream();
		return stocks
				.map(s -> {
						List<ProductWithoutStock> products = productRepo
								.findByStock(s)
								.stream()
								.map(ProductWithoutStock::new)
								.toList();
						return new ProductsGroupedByStock(s, products);
					})
				.toList();
	}

	/**
	 * Will verify if the Product Native SQL class have a valid ID and a valid Stock, then convert it into a Product (JPA Version) to upload it into the database with correct data.
	 * @param ProductSqlNative
	 * @return Void
	 * @exception InvalidProductException, NotIDProductException
	 * */
	@Override
	@Transactional
	public void updateProduct(ProductSqlNative product) throws InvalidProductException, NotIDProductException {
		if(product.getStartAt()==null)
			throw new InvalidProductException("The information was not correct");
		Optional<Product> findIt = productRepo.findById(product.getId());
		if(findIt.isEmpty())
			throw new NotIDProductException("The product doesn’t exist");
		try {
			Optional<Stock> stock = stockRepo.findById(product.getStockid());
			if(stock.isEmpty())
				throw new InvalidProductException("The information was not correct");
			productRepo.save(new Product(product.getId(), stock.get(), product.getStartAt()));
		}
		catch(Exception e) {
			throw e;
		}
	}
}
