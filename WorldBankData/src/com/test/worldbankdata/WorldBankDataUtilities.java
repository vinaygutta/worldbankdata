package com.test.worldbankdata;

import java.io.FileWriter;
import java.io.IOException;

public class WorldBankDataUtilities {
	private String[] countList = { "Andorra", "Antigua and Barbuda", "Aruba", "Australia", "Austria", "Bahamas, The",
			"Bahrain", "Barbados", "Belgium", "Bermuda", "British Virgin Islands", "Brunei Darussalam", "Canada",
			"Cayman Islands", "Channel Islands", "Chile", "Croatia", "Curacao", "Cyprus", "Czech Republic", "Denmark",
			"Estonia", "Faroe Islands", "Finland", "France", "French Polynesia", "Germany", "Gibraltar", "Greece",
			"Greenland", "Guam", "Hong Kong SAR, China", "Hungary", "Iceland", "Ireland", "Isle of Man", "Israel",
			"Italy", "Japan", "Korea, Rep.", "Kuwait", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg",
			"Macao SAR, China", "Malta", "Monaco", "Nauru", "Netherlands", "New Caledonia", "New Zealand",
			"Northern Mariana Islands", "Norway", "Oman", "Poland", "Portugal", "Puerto Rico", "Qatar", "San Marino",
			"Saudi Arabia", "Seychelles", "Singapore", "Sint Maarten (Dutch part)", "Slovak Republic", "Slovenia",
			"Spain", "St. Kitts and Nevis", "St. Martin (French part)", "Sweden", "Switzerland", "Taiwan, China",
			"Trinidad and Tobago", "Turks and Caicos Islands", "United Arab Emirates", "United Kingdom",
			"United States", "Uruguay", "Virgin Islands (U.S.)" };
	// private String[] countList = {"Andorra","Antigua and Barbuda","Bahamas,
	// The","Australia"};

	private final String COMMA_DELIMITER = ",";
	private final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private final String FILE_HEADER = "Country,GDP,Population,CO2";
	private FileWriter writer = null;

	public WorldBankDataUtilities() {
	}

	public String[] getCountList() {
		return countList;
	}

	public void generateCsvFileHeader() {
		try {
			writer = new FileWriter("countrydata.csv");

			writer.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			writer.append(NEW_LINE_SEPARATOR);

			// generate whatever data you want

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}

	}

	public void generateCsvFile(String country, String gdp, String population, String co2) {
		try {
			writer = new FileWriter("countrydata.csv", true);

			writer.append(country);
			writer.append(COMMA_DELIMITER);
			writer.append(gdp);
			writer.append(COMMA_DELIMITER);
			writer.append(population);
			writer.append(COMMA_DELIMITER);
			writer.append(co2);
			writer.append(NEW_LINE_SEPARATOR);

			// generate whatever data you want

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}

	}

	public void generateCsvFile(String country, int gdp, int population, int co2) {
		try {
			writer = new FileWriter("countrydata.csv", true);

			writer.append(country);
			writer.append(COMMA_DELIMITER);
			writer.append(Integer.toString(gdp));
			writer.append(COMMA_DELIMITER);
			writer.append(Integer.toString(population));
			writer.append(COMMA_DELIMITER);
			writer.append(Integer.toString(co2));
			writer.append(NEW_LINE_SEPARATOR);

			// generate whatever data you want

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}

	}

	public long convertStringToNumber(String str) {
		long gdpint = 0;
		String strtemp = str.replaceAll("[^-?.0-9]+", "");

		double gdpdouble = Double.parseDouble(strtemp);

		if (str.contains("million")) {
			gdpint = (long) (gdpdouble * 1_000_000);
		} else if (str.contains("billion")) {
			gdpint = (long) (gdpdouble * 1_000_000 * 1000);
		} else if (str.contains("trillion")) {
			gdpint = (long) (gdpdouble * 1_000_000 * 1_000_000);
		} else {
			gdpint = (long) gdpdouble;
		}

		return gdpint;
	}

}
