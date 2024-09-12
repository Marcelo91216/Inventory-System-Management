package com.entities;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Component that represents the mapping of the input and output product with
 * the client, where it is only necessary to receive/return a product's ID and
 * date of creation.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Component
public class ProductWithoutStock {
	/**
	 * Identification number of the product without stock information.
	 */
	private int id;

	/**
	 * Date of creation of the product without stock information.
	 */
	private LocalDateTime startAt;

	/**
	 * A description of the product information and it's attributes.
	 * 
	 * @return A description of the product without stock's information.
	 */
	@Override
	public String toString() {
		return "ProductWithoutStock [id=" + id + ", startAt=" + startAt + "]";
	}

	/**
	 * Constructor of the product without a stock, which works as a parsing method,
	 * from a Product object into a ProductWithoutStock object.
	 * 
	 * @param product The Product object that the will be converted.
	 * 
	 * @return A new instance/object ProductWithoutStock
	 */
	public ProductWithoutStock(Product product) {
		this.id = product.getId();
		this.startAt = product.getStartAt();
	}
}
