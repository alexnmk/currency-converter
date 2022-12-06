package com.fdmgroup.CurrencyConverter;


/**
 * Missing Currency Exception class
 * <p>An exception is thrown when the currency to transact from is missing in the wallet.</p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class MissingCurrencyException extends Exception {

	/** Used for serialized version UID */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Throws exception for missing currency to transact from the wallet.
	 * 
	 * @param problem - a string to display the exception
	 */
	public MissingCurrencyException(String problem) {
		super(problem);
	}
}
