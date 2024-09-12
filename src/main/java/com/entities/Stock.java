package com.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the stock of products on the storage.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock {
	/**
	 * Identification number of the stock.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * Name of the stock.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Description of the stock.
	 */
	@Column(name = "description")
	private String desc;

	/**
	 * A description of the stock with all its data.
	 * 
	 * @return A description of a stock.
	 */
	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}

}
