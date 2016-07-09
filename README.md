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
3. Download the below JUNIT files.
	a. junit-4.12.jar
	b. hamcrest-core-1.3.jar
4. Install FF browser version 34.

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
	c. junit-4.12.jar
	d. hamcrest-core-1.3.jar
5. Click on "Apply" and then "OK" button.

How to execute the Script on FF browser
---------------------------------------
1. Open Eclipse and then open the WorldBankData project.
2. Naviage to src->com.text.worldbankdta on the "Package" section of eclipse.
3. Double click on "WorldBankData.java" file.
4. Ensure that in the "setUp()" method the line "driver=new FirefoxDriver()" is uncommented.
5. Click on the eclipse Run menu item and then choose "Run As -> JUnit test".

How to execute the Script on IE browser
---------------------------------------
1. Download the IEDriverServer_Win32_2.53.1.zip file from http://docs.seleniumhq.org/download/ and extract it.
2. Open Eclipse and then open the WorldBankData project.
3. Naviage to src->com.text.worldbankdta on the "Package" section of eclipse.
4. Double click on "WorldBankData.java" file.
5. Make the below changes in the "setUp()" method 
	a. Comment the line "driver=new FirefoxDriver()".
	b. Uncomment the line System.setProperty("webdriver.ie.driver", "D:\\sele\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe")" and change the path of "IEDriverServer.exe" file appropriately.
	c. Uncomment the line driver = new InternetExplorerDriver();
6. Click on the eclipse Run menu item and then choose "Run As -> JUnit test".

Tested on the below platform
----------------------------
OS: Win 8.1 Pro x32
Browsers: Firefox 34 and IE11
