package com.fdmgroup.CurrencyConverter;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * User class with account details.
 * <p>
 * Allows retrieval of wallet details.
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class User {

	/** Used for the user name. */
	private String name;

	/** Used for the wallet details. */
	private Map<String, Double> wallet;

	
	/**
	 * Default constructor for {@code User} object
	 */
	public User() {
	};

	
	/**
	 * Custom constructor for {@code User} object
	 * 
	 * @param name   - a string of user name
	 * @param wallet - a wallet mapping currencies with corresponding amount
	 */
	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("wallet") Map<String, Double> wallet) {
		this.name = name;
		this.wallet = wallet;
	}

	
	/**
	 * Getter method to get the {@code name} class attribute of the {@code User}
	 * object.
	 * 
	 * @return name - a string of user name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Getter method to get the {@code wallet} class attribute of the {@code User}
	 * object.
	 * 
	 * @return wallet - user wallet with their currencies and corresponding amount
	 */
	public Map<String, Double> getWallet() {
		return wallet;
	};

}
