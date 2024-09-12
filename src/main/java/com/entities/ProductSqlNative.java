package com.entities;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A component that represents a product object based on the MySQL original
 * representation, with it's "stock_id" column representing the foreign key for
 * a Stock object.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductSqlNative {
	/**
	 * Identification number.
	 */
	private int id;

	/**
	 * Date and time when the product was created.
	 */
	private LocalDateTime startAt;

	/**
	 * Stock identification number, which the product belongs.
	 */
	private int stockid;
}
