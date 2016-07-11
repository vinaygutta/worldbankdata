# worldbankdata
			World Bank TC Automation using Selenium

What is Automated?
------------------
The below TC is automated.

1. Open the world bank site in a ﬁrefox browser or IE browser (www.worldbank.org).
2. Click on the Data tab.
3. Click on the link "visit the old site here" to navigate to older site. Click on the "By Country" option below the label "Data".
4. In the Countries and Economies" section, in the "Income Levels" block,  click on "High income" item.
5. Click on Country Andorra.
6. Note the value for following 3 factors: 
"GDP at market prices (current US$)"
"Population, total"
"CO2 emissions (metric tons per capita)"
7. Navigate back to World bank site's income-level/HIC page.
8. Read and note the data as in step 6 for each country.
9. Click on Home tab of the country page.
10. Close the browser. 
11. Process the data programmatically and log the names of  top 3 countries along with their "GDP at market prices (current US$)" value.
12. Process the data programmatically and log the names of  top 3 countries along with their Population, total" value.
13. Process the data programmatically and log the names of  top 3 countries along with their "CO2 emissions (metric tons per capita)" value.
14. Export the all country data for all the 3 factors in the csv format at appropriate location in the Project directory.

Setup
-----
1. Install Eclipse IDE for Java Developers version: Neon Release (4.6.0).
2. Download the below Selenium files from http://docs.seleniumhq.org/download/.
	a. selenium-server-standalone-2.53.1.jar
	b. selenium-java-2.53.1.zip and extract it.
	c. IEDriverServer_Win32_2.53.1.zip and extract it.
	d. chromedriver_win32.zip and extract it.
4. Install FF browser version 34.
5. Install Chrome browser.

How to Import Project into Eclipse
----------------------------------
1. Copy the folder WorldBankData to the computer.
2. Launch Eclipse and choose default workspace.
3. Click on "File -> Open Projects from File System".
4. Click on "Directory" button.
5. Choose WorldBankData directory and click OK.
6. Click on "Finish" button.

Adding JARS to build path
-------------------------
1. Right Click on "WorldBankData" project and click on "Build Path -> Configure Build Path".
2. Click on Libraries tab.
3. Click on "Add External JARs" button.
4. Choose the below JARs and add them.
	a. selenium-server-standalone-2.53.1.jar
	b. selenium-java-2.53.1.jar
5. Click on "Apply" and then "OK" button.
6. Open Eclipse market place and install TestNG.

How to execute the Script in parallel on FF, IE and Chrome browsers
-------------------------------------------------------------------
Open the testngBrowserMultipleExecution.xml xml file from the project directory.

The below will be the content
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="TestSuite" thread-count="3" parallel="tests">
  <test name="ChromeTest">
  <parameter name="browser" value="Chrome" />
    <classes>
       <class name="com.test.worldbankdata.WorldBankData">
       </class>
    </classes>
  </test>
  <test name="FirefoxTest">
  <parameter name="browser" value="Firefox" />
    <classes>
       <class name="com.test.worldbankdata.WorldBankData">
       </class>
    </classes>
  </test>
  <test name="IETest">
  <parameter name="browser" value="IE" />
    <classes>
       <class name="com.test.worldbankdata.WorldBankData">
       </class>
    </classes>
  </test>
 </suite>
 
 Ensure that the parallel="tests" is present in the suite xml element.
 
 Right click on the xml file and then choose "Run As -> TestNG Suite".

Tested on the below platform
----------------------------
OS: Win 8.1 Pro x32
Browsers: Firefox 34 and IE11 and Chrome 51.
