package com.fdmgroup.CurrencyConverter;


/**
 * Currency class with code, name, date and rate.
 * <p>
 * Allows retrieval of currency information.
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class Currency {
	
	/** Used for the currency code. */
	private String code;

	/** Used for the currency alphabetical code. */
	private String alphaCode;

	/** Used for the currency numeric code. */
	private String numericCode;

	/** Used for the currency name. */
	private String name;

	/** Used for the currency rate. */
	private double rate;

	/** Used for the date of currency retrieval. */
	private String date;

	/** Used for the currency inverse rate. */
	private double inverseRate;

	
	/**
	 * Default constructor for {@code Currency} class.
	 */
	public Currency() {
	};

	
	/**
	 * Custom constructor for {@code Currency} object.
	 * 
	 * @param code        - a string of currency code
	 * @param alphaCode   - a string of currency alphabetical code
	 * @param numericCode - a string of currency numeric code
	 * @param name        - a string of currency name
	 * @param rate        - a double value of currency rate
	 * @param date        - a string of currency retrieval date
	 * @param inverseRate - a double value of currency inverse rate
	 */
	public Currency(String code, String alphaCode, String numericCode, String name, double rate, String date,
			double inverseRate) {
		super();
		this.code = code;
		this.alphaCode = alphaCode;
		this.numericCode = numericCode;
		this.name = name;
		this.rate = rate;
		this.date = date;
		this.inverseRate = inverseRate;
	}

	
	/**
	 * Getter method to get the {@code code} class attribute of the {@code Currency}
	 * object.
	 * 
	 * @return code - code of the currency
	 */
	public String getCode() {
		return code;
	}

	
	/**
	 * Getter method to get the {@code alphaCode} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return alphaCode - alphabetical code of the currency
	 */
	public String getAlphaCode() {
		return alphaCode;
	}

	
	/**
	 * Getter method to get the {@code numericCode} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return numericCode - numeric code of the currency
	 */
	public String getNumericCode() {
		return numericCode;
	}

	
	/**
	 * Getter method to get the {@code name} class attribute of the {@code Currency}
	 * object.
	 * 
	 * @return name - name of the currency
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Getter method to get the {@code rate} class attribute of the {@code Currency}
	 * object.
	 * 
	 * @return rate - rate of the currency
	 */
	public double getRate() {
		return rate;
	}

	
	/**
	 * Getter method to get the {@code date} class attribute of the {@code Currency}
	 * object.
	 * 
	 * @return date - date of retrieving currency data
	 */
	public String getDate() {
		return date;
	}

	
	/**
	 * Getter method to get the {@code inverseRate} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return inverseRate - inverse rate of the currency
	 */
	public double getInverseRate() {
		return inverseRate;
	}
}
