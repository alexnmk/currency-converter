package com.fdmgroup.CurrencyConverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for reading files
 * <p>
 * Reads and converts file line by line
 * </p>
 * 
 * @author Alex Ng
 * @version 1.0
 * @since 04/12/22
 */
public class ReadFile {

	/**
	 * Converts file data with {@code fileName} into list of string {@code lines}.
	 * 
	 * @param fileName - the name of the file to be extracted
	 * @return lines - a list of string extracted line by line from the file
	 * @throws {@link java.io.FileNotFoundException} - throws exception when file with {@code fileName} cannot be found
	 * @throws {@link java.io.IOException} - throws exception when when file with {@code fileName} cannot be outputted or inputted
	 */
	public List<String> readLines(String fileName) {
		List<String> lines = new ArrayList<>();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line = bufferedReader.readLine();

			while (line != null) {
				lines.add(line);
				line = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}
}
