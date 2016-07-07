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
	private WebDriverWait webwait;
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
	    	    Dimension dim=new Dimension(800,600);
	    	    driver.manage().window().setSize(dim); 
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    	    webwait=new WebDriverWait(driver,20);
	  }

	  @Test
	  public void testScriptext() throws Exception {
	    driver.get("http://www.worldbank.org");
	    driver.findElement(By.xpath("(//a[contains(text(),'Data')])[2]")).click();
	    driver.findElement(By.linkText("visit the old site here")).click();
	    driver.findElement(By.linkText("By Country")).click();
	    //isElementPresent(By.linkText("High income"));
	    //webwait.until(ExpectedConditions.elementToBeClickable(By.linkText("High income")));
	    Thread.sleep(10000);
	    String highincome = driver.findElement(By.linkText("High income")).getAttribute("href");
	    driver.get(highincome);
	    //isElementPresent(By.linkText("Andorra"));
	    /*System.out.println("beforeelement");
	    WebElement element = driver.findElement(By.linkText("Andorra"));
System.out.println("afterelement");
	    Actions actions = new Actions(driver);
	    System.out.println("afteractiona");
	    actions.moveToElement(element).click().perform();
	    System.out.println("aftermove");*/
	    String unitst = driver.findElement(By.linkText("United States")).getAttribute("href");
	    driver.get(unitst);
	    
	    //driver.findElement(By.linkText("United States")).click();

	    //driver.findElement(By.linkText("Andorra")).click();
	    // ERROR: Caught exception [unknown command [clickAndWaitre]]
	    //driver.findElement(By.linkText("High income")).click();
	    // ERROR: Caught exception [unknown command []]
	  }

	  @After
	  public void tearDown() throws Exception {
	    //driver.quit();
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
