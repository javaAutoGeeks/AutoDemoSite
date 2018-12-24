package TestSuite;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import Utility.ExcelRead; 
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import Actions.CommonHelperActions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DemoPages.Registration;

public class DemoSiteTestCases {

	WebDriver dr;
	ExtentReports extent;
	ExtentTest logger;

	Registration reg = new Registration();

	@Parameters({"browser"})
	@BeforeMethod
	public void openBrowser(String browser) {

		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		
		extent = new ExtentReports (System.getProperty("user.dir") +"\\src\\ExtentReports\\STMExtentReport.html", true);
		//extent.addSystemInfo("Environment","Environment Name")
		extent
		.addSystemInfo("Host Name", "SuccessFactor")
		.addSystemInfo("Environment", "Automation Testing")
		.addSystemInfo("User Name", "Rohit Ranjan	");
		//loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		//You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));


		System.out.println("Browser -- "+browser);
		if(browser.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", "C:\\Users\\rr72165\\eclipse-workspace\\AutoDemoSite\\chromedriver.exe");
			System.out.println("launching Chrome browser");
			dr = new ChromeDriver();
			CommonHelperActions.waitForPageToLoad(dr);
			dr.get("http://demo.automationtesting.in/Index.html");
			dr.manage().window().maximize();
			reg.clickSkipLogin(dr);
		}

		else if(browser.equalsIgnoreCase("Firefox")){

			System.setProperty("webdriver.gecko.driver","C:\\\\Users\\rr72165\\eclipse-workspace\\AutoDemoSite\\geckodriver.exe");
			System.out.println(" Launching Firefox driver ");
			dr =new FirefoxDriver();
			dr.get("http://demo.automationtesting.in/Index.html");
			dr.manage().window().maximize();		

		}
		
		
		logger = extent.startTest("Validate that the registration details gets filled successfully","This is a test case to ensure that users can be approved for scheduled training by their manager.");

	}
	

	@Test(dataProvider="RegistrationSheet")
	public void registerDetails(String testCase,String fname,String lname,String address) throws AWTException, InterruptedException {

		 //extent.startTest("TestCaseName", "Description");
		//TestCaseName – Name of the test
		//Description – Description of the test
		//Starting test
		

		//Assert.assertTrue(true);
		
		System.out.println(" In Regester Deatils");
		
		reg.enterDetails(dr,testCase,fname,lname,address);
		reg.browseFile(dr);	
	//	Thread.sleep(10000);
	//	dr.close();
		
		//To generate the log when the test case is passed
		logger.log(LogStatus.PASS, "Test Case - Register details is passTest");

	}

	@Test
	public void alerts() {
//
//		logger = extent.startTest("alerts","Check that the alerts for the users arw working properly ");
//		AssertJUnit.assertTrue(true);
		System.out.println(" In the alerts page ");
		logger.log(LogStatus.PASS, "Test Case alerts Status is passed");

	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.FAILURE){
			
			long millis=System.currentTimeMillis();  
			Date date = new Date(millis);
			File file= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir")+"\\src\\ExtentReports\\STMExtentReport.html" +result.getMethod().getMethodName()+date+ ".png";
			FileHandler.copy(file, new File(path));
			Assert.assertFalse(true);
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		
		
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}

	@DataProvider(name="RegistrationSheet")
	public Object[][]RegistrationDetails(){
		
		Object[][] testObjArray= new Object[3][2];
		try {
			
			testObjArray = ExcelRead.getExcelData("C://Users//rr72165//eclipse-workspace//AutoDemoSite//src//TestData//RegisterDetails.xlsx","Sheet1");
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		 
        return testObjArray;	
		
	}
	

	 @DataProvider
	    public Object[][] getData(ITestContext context) {
	        String parameter = context.getCurrentXmlTest().getLocalParameters().get("names");
	        String[] names = parameter.split(",");
	        Object[][] returnValues = new Object[names.length][1];
	        int index = 0;
	        for (Object[] each : returnValues) {
	            each[0] = names[index++].trim();
	        }
	        return returnValues;
	    }
	
	
	
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {

		System.out.println("Closing the browser ");
		// writing everything to document
		//flush() - to write or update test information to your report.

		extent.flush();
		
		//Call close() at the very end of your session to clear all resources. 
		//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		//Once this method is called, calling any Extent method will throw an error.
		//close() - To close all the operation
		
		//extent.close();

		//dr.quit();


	}	
}