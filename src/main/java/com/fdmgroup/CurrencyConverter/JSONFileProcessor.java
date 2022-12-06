package com.fdmgroup.CurrencyConverter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Class processing JSON file.
 * <p>
 * Converts data between JSON file and data structures (i.e. array and list).
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class JSONFileProcessor {

	/** Used for mapping data between JSON file and data structures. */
	private ObjectMapper mapper = new ObjectMapper();

	
	/**
	 * Convert currency information in JSON {@code file} to hash map about
	 * {@code currencies}.
	 * 
	 * @param file - a file with currency information in JSON format
	 * @return currencies - a hash map of currency information
	 * @throws {@link java.io.IOException} - throws exception when {@code file} is
	 *                {@code null}
	 */
	public Map<String, Currency> readFromCurrencyJSONFile(File file) {

		Map<String, Currency> currencies = new HashMap<String, Currency>();

		try {
			currencies = mapper.readValue(file, new TypeReference<Map<String, Currency>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return currencies;
	}

	
	/**
	 * Convert user information in JSON {@code file} to array about {@code users}.
	 * 
	 * @param file - a file with user information in JSON format
	 * @return users - a hash map of user information
	 * @throws {@link java.io.IOException} - throws exception when {@code file} is
	 *                {@code null}
	 */
	public User[] readFromUserJSONFile(File file) {

		User[] users = null;

		try {
			users = mapper.readValue(file, User[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return users;
	}

	
	/**
	 * Write user information to JSON {@code file}.
	 * 
	 * @param file  - destination file where {@code users} will be transferred to.
	 * @param users - an array containing user information
	 * @throws {@link java.io.IOException} - throws exception when {@code file} is
	 *                {@code null}
	 */
	public void writeToJSONFile(File file, User[] users) {

		try {
			mapper.writeValue(file, users);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
