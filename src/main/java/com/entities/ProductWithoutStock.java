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

}
