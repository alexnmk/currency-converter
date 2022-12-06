package com.fdmgroup.CurrencyConverter;


/**
 * Insufficient Money Exception class.
 * <p>
 * An exception is thrown when there is insufficient money to perform
 * transaction.
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class InsufficientMoneyException extends Exception {

	/** Used for serialized version UID */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Throws exception for insufficient money to perform transaction.
	 * 
	 * @param message - a string to display the exception
	 */
	public InsufficientMoneyException(String problem) {
		super(problem);
	}
}
