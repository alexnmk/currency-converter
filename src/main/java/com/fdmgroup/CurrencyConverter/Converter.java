package com.fdmgroup.CurrencyConverter;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Class converting currencies.
 * <p>
 * Converts a transaction amount from one currency to another currency.
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class Converter {

	/**
	 * Converts {@code transactionAmount} from {@code fromCurrency} to
	 * {@code toCurrency}.
	 * 
	 * @param fromCurrency      - currency code to be converted from
	 * @param toCurrency        - currency code to be converted to
	 * @param transactionAmount - the amount of transaction in {@code fromCurrency}
	 * @return convertedAmount - the amount of transaction in {@code toCurrency}
	 */
	public double convert(String fromCurrency, String toCurrency, double transactionAmount) {

		Logger logger = LogManager.getLogger();

		// Get currencies information
		File currencyJSONFile = new File("src/main/resources/fx_rates.json");
		JSONFileProcessor jsonFileProcessor = new JSONFileProcessor();
		Map<String, Currency> currencies = jsonFileProcessor.readFromCurrencyJSONFile(currencyJSONFile);

		// Base currency is USD
		double fromCurrencyInverseRate = 1.0;
		double toCurrencyRate = 1.0;

		// Get inverse rate if the from currency is not USD
		if (currencies.containsKey(fromCurrency)) {
			fromCurrencyInverseRate = currencies.get(fromCurrency).getInverseRate();
		}
		logger.trace("\n" + fromCurrency + " inverse rate: " + fromCurrencyInverseRate);


		// Get rate if the to currency is not USD
		if (currencies.containsKey(toCurrency)) {
			toCurrencyRate = currencies.get(toCurrency).getRate();
		}
		logger.trace("\n" + toCurrency + " rate: " + toCurrencyRate);

		logger.trace("\nTransaction amount in " + fromCurrency + " : " + transactionAmount);
		double convertedAmount = transactionAmount * fromCurrencyInverseRate * toCurrencyRate;
		logger.trace(
				"\nTransaction amount converted from " + fromCurrency + " to " + toCurrency + ": " + transactionAmount
						+ " * " + fromCurrencyInverseRate + " * " + toCurrencyRate + " = " + convertedAmount);
		return convertedAmount;
	}
}
