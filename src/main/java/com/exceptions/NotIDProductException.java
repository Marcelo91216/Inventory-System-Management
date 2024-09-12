package com.exceptions;

/**
 * An exception that occurs when the application detects the user's input when
 * requesting an update of it's product, doesn't receive an ID.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
public class NotIDProductException extends Exception {

	/**
	 * The constructor that will create the exception.
	 * 
	 * @param msg Message to show when the exception occurs.
	 * @return A new instance of the NotIDProductException class.
	 */
	public NotIDProductException(String msg) {
		super(msg);
	}
}
