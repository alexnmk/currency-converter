package com.fdmgroup.CurrencyConverter;


/**
 * Missing Currency Exception class
 * <p>
 * An exception is thrown when the user performing transaction does not exist in
 * {@code users.json} file.
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class InvalidUserException extends Exception {

	/** Used for serialized version UID */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Throws exception for invalid user performing transaction.
	 * 
	 * @param problem - a string to display the exception
	 */
	public InvalidUserException(String problem) {
		super(problem);
	}
}
