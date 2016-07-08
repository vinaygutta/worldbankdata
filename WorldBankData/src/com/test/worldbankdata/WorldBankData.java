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

public class WorldBankData {

	static String driverPath = "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe";

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
		// System.setProperty("webdriver.ie.driver", driverPath);
		// driver = new InternetExplorerDriver();
		driver = new FirefoxDriver();
		baseUrl = "http://www.worldbank.org";
		driver.manage().window().maximize();
		// Dimension dim=new Dimension(800,600);
		// driver.manage().window().setSize(dim);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		driver.findElement(By.xpath("(//a[contains(text(),'Data')])[2]")).click();
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
				// System.out.println("=============");
				// System.out.println(cntry);
			}

			if (isElementPresent(By
					.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[1]/div/div[2]/span/span/a/span"))) {
				gdp = driver
						.findElement(By
								.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[1]/div/div[2]/span/span/a/span"))
						.getText();

				// System.out.println("GDP: " + gdp);
				long gdplong = utiliy.convertStringToNumber(gdp);

				// System.out.println("GDP Long: " + gdplong);

				mapgdp.put(cntry, gdplong);
				// System.out.println("GDP Int = " +gdpint);
			}

			if (isElementPresent(By.xpath(
					"//*[@id='nav-header-top-left-area']/div[2]/div/div/div[2]/div/div/div/div[2]/span/span/a/span"))) {
				popu = driver
						.findElement(By
								.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[2]/div/div/div/div[2]/span/span/a/span"))
						.getText();
				// System.out.println("Population: " + popu);
				long populong = utiliy.convertStringToNumber(popu);

				// System.out.println("Popu Long: " + populong);
				mappopu.put(cntry, populong);
			}

			if (isElementPresent(By.xpath("//*[@id='boxes-box-country_wdi_block2']/div/div[2]/div/div/div/span[1]"))) {
				co2 = driver
						.findElement(By.xpath("//*[@id='boxes-box-country_wdi_block2']/div/div[2]/div/div/div/span[1]"))
						.getText();
				// System.out.println("CO2: " + co2);
				long co2long = utiliy.convertStringToNumber(co2);

				// System.out.println("CO2 Long: " + co2long);
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
			writergdp.flush();
			writergdp.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (Object e : agdp) {
			// System.out.println(((Map.Entry<String, Long>) e).getKey() + " : "
			// + ((Map.Entry<String, Long>) e).getValue());
			try {
				if (count <= 3) {
					writergdp = new FileWriter("top3countriesbygdp.log", true);
					writergdp.append(
							((Map.Entry<String, Long>) e).getKey() + " : " + ((Map.Entry<String, Long>) e).getValue());
					writergdp.append("\n");
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
			writerpopu.flush();
			writerpopu.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (Object e : apopu) {
			// System.out.println(((Map.Entry<String, Long>) e).getKey() + " : "
			// + ((Map.Entry<String, Long>) e).getValue());
			try {
				if (countpopu <= 3) {
					writerpopu = new FileWriter("top3countriesbypopu.log", true);
					writerpopu.append(
							((Map.Entry<String, Long>) e).getKey() + " : " + ((Map.Entry<String, Long>) e).getValue());
					writerpopu.append("\n");
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
			writerco2.flush();
			writerco2.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (Object e : aco2) {
			// System.out.println(((Map.Entry<String, Long>) e).getKey() + " : "
			// + ((Map.Entry<String, Long>) e).getValue());
			try {
				if (countco2 <= 3) {
					writerco2 = new FileWriter("top3countriesbyco2.log", true);
					writerco2.append(
							((Map.Entry<String, Long>) e).getKey() + " : " + ((Map.Entry<String, Long>) e).getValue());
					writerco2.append("\n");
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
