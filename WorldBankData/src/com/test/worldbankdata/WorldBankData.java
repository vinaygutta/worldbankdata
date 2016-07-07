package com.test.worldbankdata;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorldBankData {
	
	static String driverPath = "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe";

	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		  System.setProperty("webdriver.ie.driver", driverPath);
	    //driver = new InternetExplorerDriver();
	    driver = new FirefoxDriver();
	    	    baseUrl = "http://www.worldbank.org";
	    	    driver.manage().window().maximize();
//	    	    Dimension dim=new Dimension(800,600);
//	    	    driver.manage().window().setSize(dim); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testScriptext() throws Exception {
	    driver.get("http://www.worldbank.org");
	    driver.findElement(By.xpath("(//a[contains(text(),'Data')])[2]")).click();
	    driver.findElement(By.linkText("visit the old site here")).click();
	    driver.findElement(By.linkText("By Country")).click();
	    
	    String highincome = driver.findElement(By.linkText("High income")).getAttribute("href");
	    driver.get(highincome);

	    String unitst = driver.findElement(By.linkText("Sweden")).getAttribute("href");
	    driver.get(unitst);
	    
	    String gdp = driver.findElement(By.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[1]/div/div[2]/span/span/a/span")).getText();
	    System.out.println("GDP: " + gdp);
	    
	    String str = gdp.replaceAll("[^-?.0-9]+", "");
	    
	    double gdpint = Double.parseDouble(str);
	    System.out.println("GDP Int = " +gdpint);
	    
	    
	    String popu = driver.findElement(By.xpath("//*[@id='nav-header-top-left-area']/div[2]/div/div/div[2]/div/div/div/div[2]/span/span/a/span")).getText();
	    System.out.println("Population: " + popu);	    
	    
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	    	System.out.println(by);
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
