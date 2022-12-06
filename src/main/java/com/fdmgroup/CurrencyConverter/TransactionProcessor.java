package com.fdmgroup.CurrencyConverter;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Class processing transaction
 * <p>
 * Performs transaction by converting currencies and updating user wallet.
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 * @see com.fdmgroup.CurrencyConverter.InsufficientMoneyException
 * @see com.fdmgroup.CurrencyConverter.MissingCurrencyException
 * @see com.fdmgroup.CurrencyConverter.MissingCurrencyException
 * @see com.fdmgroup.CurrencyConverter.InvalidCurrencyException
 */
public class TransactionProcessor {

	/** Used for the user name and wallet */
	private File usersJSONFile = new File("src/main/resources/users.json");

	/** Used for the currency details */
	private final File CURRENCY_JSON_FILE = new File("src/main/resources/fx_rates.json");

	/** Used for processing JSON file */
	private JSONFileProcessor jsonFileProcessor = new JSONFileProcessor();

	/** Used for converting currencies */
	private Converter converter = new Converter();

	/** Used for logging data to console and to a file */
	private Logger logger = LogManager.getLogger();

	/** Used for storing data in {@code usersJSONFile} */
	private User[] users = null;

	
	/**
	 * Deduct {@code transactionAmount} in currency {@code fromCurrencyCode} from
	 * {@code user} wallet.
	 * 
	 * @param user              - current user performing transaction
	 * @param fromCurrencyCode  - a string of currency code to be transacted from
	 * @param transactionAmount - transaction amount in currency
	 *                          {@code fromCurrencyCode}
	 */
	private void deductFromCurrency(User user, String fromCurrencyCode, double transactionAmount) {

		double fromWalletAmount = user.getWallet().get(fromCurrencyCode) - transactionAmount;
		user.getWallet().put(fromCurrencyCode, fromWalletAmount);
		logger.trace("\n" + fromCurrencyCode + " amount after deduction: " + user.getWallet().get(fromCurrencyCode));
	}

	
	/**
	 * Update the {@code user} wallet with {@code transactionAmount} converted from
	 * currency {@code fromCurrencyCode} to currency {@code toCurrencyCode}.
	 * 
	 * @param user              - current user performing transaction
	 * @param toCurrencyCode    - a string of currency code to be transacted to
	 * @param fromCurrencyCode  - a string of currency code to be transacted from
	 * @param transactionAmount - transaction amount in currency
	 *                          {@code fromCurrencyCode}
	 */
	private void convertedAmountransferToWallet(User user, String toCurrencyCode, String fromCurrencyCode,
			double transactionAmount) {

		double toWalletAmount = 0.0;

		// Check if the to currency exists in the wallet
		if (user.getWallet().containsKey(toCurrencyCode)) {
			logger.trace("\n" + toCurrencyCode + " original amount: " + user.getWallet().get(toCurrencyCode));
			toWalletAmount = user.getWallet().get(toCurrencyCode)
					+ converter.convert(fromCurrencyCode, toCurrencyCode, transactionAmount);
			user.getWallet().put(toCurrencyCode, toWalletAmount);
			logger.trace("\n" + toCurrencyCode + " amount added: " + user.getWallet().get(toCurrencyCode));
		} else {
			// Add new currency to wallet
			logger.trace("\n" + toCurrencyCode + " original amount: " + user.getWallet().get(toCurrencyCode));
			toWalletAmount = converter.convert(fromCurrencyCode, toCurrencyCode, transactionAmount);
			user.getWallet().put(toCurrencyCode, toWalletAmount);
		}
	}

	
	/**
	 * Convert currencies and update wallet in {@code users} list.
	 * 
	 * @param line - single transaction of one user
	 * @throws InsufficientMoneyException - throws exception when user does not have
	 *                                    sufficient money for transaction
	 * @throws MissingCurrencyException   - throws exception when currency
	 *                                    {@code fromCurrencyCode} to transact from
	 *                                    is missing in the wallet
	 * @throws InvalidUserException       - throws exception when user transacting
	 *                                    currency does not have an user account
	 * @throws InvalidCurrencyException   - throws exception if any of the following
	 *                                    is true:
	 *                                    <ul>
	 *                                    <li>{@code currencies} does not have a key
	 *                                    of {@code fromCurrencyCode} AND
	 *                                    {@code fromCurrencyCode} is not usd
	 *                                    <li>{@code currencies} does not have a key
	 *                                    of {@code toCurrencyCode} AND
	 *                                    {@code toCurrencyCode} is not usd</li>
	 *                                    </ul>
	 */
	public void executeTransaction(String line) throws InsufficientMoneyException, MissingCurrencyException,
			InvalidUserException, InvalidCurrencyException {

		String[] transaction = line.split(" ");
		String name = transaction[0];
		String fromCurrencyCode = transaction[1].toLowerCase();
		String toCurrencyCode = transaction[2].toLowerCase();
		double transactionAmount = Double.parseDouble(transaction[3]);

		boolean isUserValid = false;

		Map<String, Currency> currencies = jsonFileProcessor.readFromCurrencyJSONFile(CURRENCY_JSON_FILE);

		users = jsonFileProcessor.readFromUserJSONFile(usersJSONFile);

		// Loop through every user
		for (int i = 0; i < users.length; i++) {

			// Check if user has an account for transaction
			if (users[i].getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
				logger.trace("\nname: " + users[i].getName());

				isUserValid = true;
				logger.trace("\nisUserValid value is " + isUserValid);

				if ((currencies.containsKey(fromCurrencyCode) || fromCurrencyCode.equals("usd"))
						&& (currencies.containsKey(toCurrencyCode) || toCurrencyCode.equals("usd"))) {

					// Check the from currency exists in the wallet
					if (users[i].getWallet().containsKey(fromCurrencyCode)) {
						logger.trace("\n" + fromCurrencyCode + " original amount: "
								+ users[i].getWallet().get(fromCurrencyCode));

						// Check if there is enough money in wallet for transaction
						if (users[i].getWallet().get(fromCurrencyCode) >= transactionAmount) {
							// Deduct fromCurrency amount
							deductFromCurrency(users[i], fromCurrencyCode, transactionAmount);
							// Add converted currency to wallet
							convertedAmountransferToWallet(users[i], toCurrencyCode, fromCurrencyCode,
									transactionAmount);
						} else {
							// Throw exception and log for not having enough money in the wallet for transaction
							logger.error("\nInsufficient " + fromCurrencyCode + " to perform transaction.");
							throw new InsufficientMoneyException(
									"There is not enough " + fromCurrencyCode + " in the wallet for transaction.");
						}
					} else {
						// Throw exception and log for from currency missing in the wallet for transaction
						logger.error("\nMissing " + fromCurrencyCode + " in the wallet for transaction.");
						throw new MissingCurrencyException(
								"There is no " + fromCurrencyCode + " in the wallet for transaction.");
					}
				} else {
					// Throw exception and log for invalid currency
					logger.error("\nThe currency in transaction is invalid.");
					throw new InvalidCurrencyException("The currenct in transaction is invalid.");
				}
			}
		}

		if (!isUserValid) {
			// Throws exception and log for invalid user
			logger.error("\nUser " + name + " is an invalid user.");
			throw new InvalidUserException("User " + name + " does not have an account for transaction.");
		}
	}

	
	/**
	 * Overwrite {@code usersJSONFile} with {@code users} in JSON format.
	 */
	public void updateUsersFile() {
		
		jsonFileProcessor.writeToJSONFile(usersJSONFile, users);
	}
}
