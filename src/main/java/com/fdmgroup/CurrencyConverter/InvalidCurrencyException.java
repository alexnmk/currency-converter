package com.fdmgroup.CurrencyConverter;


/**
 * Invalid Currency Exception class
 * <p>
 * An exception is thrown when an invalid currency is involved in transaction.
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class InvalidCurrencyException extends Exception {

	/** Used for serialized version UID */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Throws exception for passing an invalid currency into transaction.
	 * 
	 * @param problem - a string to display the exception
	 */
	public InvalidCurrencyException(String problem) {
		super(problem);
	}
}
