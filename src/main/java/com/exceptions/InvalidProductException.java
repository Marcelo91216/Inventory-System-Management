package com.exceptions;

/**
 * Exception that occurs when the application detects that when the user wants
 * to update a product data, it's stock ID or date of creation are incorrect or
 * inexistent.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
public class InvalidProductException extends Exception {

	/**
	 * Constructor for the creation of the exception with it's message.
	 * 
	 * @param msg Message to show when exception occurs.
	 * @return A new instance of the InvalidProductException class.
	 */
	public InvalidProductException(String msg) {
		super(msg);
	}
}
