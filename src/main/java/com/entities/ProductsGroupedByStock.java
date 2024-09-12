package com.entities;

import java.util.List;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A component that represents a Stock and his list of products without it's
 * stock information.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class ProductsGroupedByStock {
	/**
	 * The stock to analyze.
	 */
	private Stock stock;

	/**
	 * The list of products without its stock that will belong to the stock.
	 */
	private List<ProductWithoutStock> products;
}
