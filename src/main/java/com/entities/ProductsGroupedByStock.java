package com.entities;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Class made for the User Story #5, in order to return a JSON of a stock and a list of its products id and start date
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class ProductsGroupedByStock {
	private Stock stock;
	private List<ProductWithoutStock> products;
}
