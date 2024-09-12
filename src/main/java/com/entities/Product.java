package com.entities;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Product is the entity that maps it into the 'product' table on the database.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
	/**
	 * Identification of the product.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * The stock which the product belongs.
	 */
	@ManyToOne
	@JoinColumn(name = "stock_id", referencedColumnName = "id")
	private Stock stock;

	/**
	 * Date and time when the product was introduced into the company.
	 */
	@Column(name = "start_at")
	private LocalDateTime startAt;

	/**
	 * Print on the console the identification number and the start date of the
	 * product.
	 * 
	 * @return A description of the product.
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", startAt=" + startAt + "]";
	}

}
