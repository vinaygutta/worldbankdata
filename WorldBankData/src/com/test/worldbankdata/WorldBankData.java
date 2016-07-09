package com.test.worldbankdata;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WorldBankData {

	private HashMap<String, Long> mapgdp = new HashMap<String, Long>();
	private HashMap<String, Long> mappopu = new HashMap<String, Long>();
	private HashMap<String, Long> mapco2 = new HashMap<String, Long>();

	private FileWriter writergdp = null;
	private FileWriter writerpopu = null;
	private FileWriter writerco2 = null;

	private long startTime;
	private long endTime;

	private WebDriver driver;
	private String baseUrl;

	private WorldBankDataUtilities utiliy;

	@Before
	public void setUp() throws Exception {
		// System.setProperty("webdriver.ie.driver",
		// "D:\\sele\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		driver = new FirefoxDriver();
		baseUrl = "http://www.worldbank.org";
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		utiliy = new WorldBankDataUtilities();
		startTime = System.currentTimeMillis();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testScriptext() throws Exception {

		String cntry = "";
		String gdp = "";
		String popu = "";
		String co2 = "";
		driver.get(baseUrl);
		driver.findElement(By.xpath("//*[@id='hf_header_wrapper']/div/ul[2]/li[3]/a")).click();
		driver.findElement(By.linkText("visit the old site here")).click();
		driver.findElement(By.linkText("By Country")).click();

		String highincome = driver.findElement(By.linkText("High income")).getAttribute("href");
		driver.get(highincome);

		String countrylist[] = utiliy.getCountList();
		utiliy.generateCsvFileHeader();

		for (String country : countrylist) {
			cntry = "";
			gdp = "";
			popu = "";
			co2 = "";
			if (isElementPresent(By.linkText(country))) {
				String unitst = driver.findElement(By.linkText(country)).getAttribute("href");
				driver.get(unitst);
			}

			if (isElementPresent(By.xpath("//*[@id='nav-header-top-left-area']/h1"))) {
				cntry = driver.findElement(By.xpath("//*[@id='nav-header-top-left-area']/h1")).getText();

			}

			if (isElementPresent(By
					.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[1]/div/div[2]/span/span/a/span"))) {
				gdp = driver
						.findElement(By
								.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[1]/div/div[2]/span/span/a/span"))
						.getText();

				long gdplong = utiliy.convertStringToNumber(gdp);

				mapgdp.put(cntry, gdplong);
			}

			if (isElementPresent(By.xpath(
					"//*[@id='nav-header-top-left-area']/div[2]/div/div/div[2]/div/div/div/div[2]/span/span/a/span"))) {
				popu = driver
						.findElement(By
								.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[2]/div/div/div/div[2]/span/span/a/span"))
						.getText();
				long populong = utiliy.convertStringToNumber(popu);

				mappopu.put(cntry, populong);
			} else if (isElementPresent(By.xpath(
					"//*[@id='nav-header-top-left-area']/div[2]/div/div/div/div/div/div/div[2]/span/span/a/span"))) {

				popu = driver
						.findElement(By
								.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div/div/div/div/div[2]/span/span/a/span"))
						.getText();
				long populong = utiliy.convertStringToNumber(popu);

				mappopu.put(cntry, populong);
			}

			if (isElementPresent(By.xpath("//*[@id='boxes-box-country_wdi_block2']/div/div[2]/div/div/div/span[1]"))) {
				co2 = driver
						.findElement(By.xpath("//*[@id='boxes-box-country_wdi_block2']/div/div[2]/div/div/div/span[1]"))
						.getText();

				long co2long = utiliy.convertStringToNumber(co2);

				mapco2.put(cntry, co2long);
			}

			utiliy.generateCsvFile(cntry.replaceAll("[,]+", ""), gdp.replaceAll("[,]+", ""),
					popu.replaceAll("[,]+", ""), co2.replaceAll("[,]+", ""));

			driver.get(highincome);
		}
		Object[] agdp = mapgdp.entrySet().toArray();
		Arrays.sort(agdp, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Long>) o2).getValue().compareTo(((Map.Entry<String, Long>) o1).getValue());
			}
		});
		int count = 1;
		try {
			writergdp = new FileWriter("top3countriesbygdp.log");
			writergdp.append("Top 3 countries by GDP at market prices (current US$)");
			writergdp.append(System.lineSeparator());
			writergdp.append("=====================================================");
			writergdp.append(System.lineSeparator());
			writergdp.flush();
			writergdp.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (Object e : agdp) {

			try {
				if (count <= 3) {
					writergdp = new FileWriter("top3countriesbygdp.log", true);
					writergdp.append(
							((Map.Entry<String, Long>) e).getKey() + " : " + ((Map.Entry<String, Long>) e).getValue());
					writergdp.append(System.lineSeparator());
					writergdp.flush();
					writergdp.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			count++;
		}

		Object[] apopu = mappopu.entrySet().toArray();
		Arrays.sort(apopu, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Long>) o2).getValue().compareTo(((Map.Entry<String, Long>) o1).getValue());
			}
		});
		int countpopu = 1;
		try {
			writerpopu = new FileWriter("top3countriesbypopu.log");
			writerpopu.append("Top 3 countries by Population");
			writerpopu.append(System.lineSeparator());
			writerpopu.append("=============================");
			writerpopu.append(System.lineSeparator());
			writerpopu.flush();
			writerpopu.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (Object e : apopu) {

			try {
				if (countpopu <= 3) {
					writerpopu = new FileWriter("top3countriesbypopu.log", true);
					writerpopu.append(
							((Map.Entry<String, Long>) e).getKey() + " : " + ((Map.Entry<String, Long>) e).getValue());
					writerpopu.append(System.lineSeparator());
					writerpopu.flush();
					writerpopu.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			countpopu++;
		}

		Object[] aco2 = mapco2.entrySet().toArray();
		Arrays.sort(aco2, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Long>) o2).getValue().compareTo(((Map.Entry<String, Long>) o1).getValue());
			}
		});
		int countco2 = 1;
		try {
			writerco2 = new FileWriter("top3countriesbyco2.log");
			writerco2.append("Top 3 countries by CO2 emissions");
			writerco2.append(System.lineSeparator());
			writerco2.append("================================");
			writerco2.append(System.lineSeparator());
			writerco2.flush();
			writerco2.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (Object e : aco2) {

			try {
				if (countco2 <= 3) {
					writerco2 = new FileWriter("top3countriesbyco2.log", true);
					writerco2.append(
							((Map.Entry<String, Long>) e).getKey() + " : " + ((Map.Entry<String, Long>) e).getValue());
					writerco2.append(System.lineSeparator());
					writerco2.flush();
					writerco2.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			countco2++;
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime);

		long diffSeconds = elapsedTime / 1000 % 60;
		long diffMinutes = elapsedTime / (60 * 1000) % 60;
		long diffHours = elapsedTime / (60 * 60 * 1000);

		System.out.println("Run Time: " + diffHours + " Hours " + diffMinutes + " Minutes " + diffSeconds + " Seconds");
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
