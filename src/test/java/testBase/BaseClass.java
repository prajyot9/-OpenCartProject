package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;//For Log4j2
import org.apache.logging.log4j.Logger;//For Log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver; //Why we make static added the reason in detail in notebook
	public Logger logger; // Log4j
	public Properties p; // loading config.properties file

	@BeforeClass(groups = { "Regression", "Master", "Sanity" }) // As there is different name given to each group so
																// every time it should run as setup and tear down
																// require so we have mention here.)
	@Parameters({ "os", "browser" }) // Use to take a paramter from mastertestng.xml

	// String os and String browser both varaible which we take from testng.xml as
	// parameter
	public void setUp(String os, String br) throws IOException {

		logger = LogManager.getLogger(this.getClass());
		// Logger step basically used to generate the logs for each class.
		// (this) keyword is used to pointing out to a particular class so by using
		// this.getClass it will decide the class name

		// loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties"); // Location of file in current
																						// project:./src//test//resources//config.properties
		p = new Properties(); // Object
		p.load(file); // This will load the data from properties file

		// Switch is after taking input from .xml file then here we used it. based on
		// parameter provided during runtime
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser");
			return;
		}
		// Here the purpose of return keyword is as browser name fail the complete
		// excution will fail not just loop so return will take the flow complete out of
		// loop.

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL")); // p var and getProperty method to fetch the data from config.properties
												// file
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Regression", "Master", "Sanity" })
	public void tearDown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
        
		TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
	    File sourceFile =takesScreenshot.getScreenshotAs(OutputType.FILE);
	    
	    String targetFilePath =System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);
	    
	    sourceFile.renameTo(targetFile);
	    
	    return targetFilePath;
	}
	//When to execute this captureScreen this method?
	//If the test case got fail then we need to execute this method.
	//How you know the test got fail? As we are using ExtendReport in which once OnTestFailure Fail then we got to know.
	
}
