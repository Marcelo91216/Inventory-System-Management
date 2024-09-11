package com.entities;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Component
public class ProductWithoutStock {
	private int id;
	private LocalDateTime startAt;

	@Override
	public String toString() {
		return "ProductWithoutStock [id=" + id + ", startAt=" + startAt + "]";
	}

//	Recieve a Product and convert it into a simple Product without the stock information
	public ProductWithoutStock(Product product) {
		this.id = product.getId();
		this.startAt = product.getStartAt();
	}
}
