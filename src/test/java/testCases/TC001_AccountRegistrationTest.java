package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.TestDataGenerator;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registartion() {
		
		logger.info("*******Staring TC1***********");//To generate a logs info means type of log
		//To get the logs whenever occur that's why we added in try catch block
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.ClickRegistration();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

		regpage.setFirstName(randomName);
		regpage.setLastName(String.valueOf(randomNumber));
		regpage.setEmail(randomName + "@gmail.com");
		regpage.setTelephone("4562130");
		regpage.setPassword("ABC123");
		regpage.setConfirmPassword("ABC123");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		String confmsg = regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Failed"); //.error will generate eeor logs if need any other we can specify accoringl
			Assert.fail();
		}

	}

	String randomName = TestDataGenerator.getRandomString(6);
	int randomNumber = TestDataGenerator.getRandomNumber(1000, 9000);

}
