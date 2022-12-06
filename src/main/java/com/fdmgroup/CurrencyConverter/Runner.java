package com.fdmgroup.CurrencyConverter;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Main class to perform transaction.
 * <p>
 * Performs currency convertion for users.
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
public class Runner {

	/** Used for the transaction details */
	private static final String TRANSACTION_TEXT_FILE_URL = "src/main/resources/transactions.txt";

	
	/**
	 * {@code main} method performing transaction and outputting JSON file.
	 * 
	 * @param args - a list of string passed to the console
	 * @throws InsufficientMoneyException - throws exception when user does not have
	 *                                    sufficient money for transaction
	 * @throws MissingCurrencyException   - throws exception when the currency to
	 *                                    transact from is missing in the wallet
	 * @throws InvalidUserException       - throws exception when user transacting
	 *                                    currency does not have an user account
	 * @throws InvalidCurrencyException   - throws exception when the currency
	 *                                    involving in transaction is invalid
	 */
	public static void main(String[] args) {

		Logger logger = LogManager.getLogger();

		logger.info("\n\n -- Start of the Program --");

		// Read transaction text file
		ReadFile readFile = new ReadFile();
		TransactionProcessor transactionProcessor = new TransactionProcessor();
		List<String> transactions = readFile.readLines(TRANSACTION_TEXT_FILE_URL);

		// Perform transactions
		for (int i = 0; i < transactions.size(); i++) {
			try {
				logger.info("\n\nTransaction No." + (i + 1));
				transactionProcessor.executeTransaction(transactions.get(i));
			} catch (InsufficientMoneyException | MissingCurrencyException | InvalidUserException
					| InvalidCurrencyException e) {
				e.printStackTrace();
			}

			// Overwrite users.json
			transactionProcessor.updateUsersFile();
		}

		logger.info("\n\n -- End of the Program -- \n\n");
	}

}
