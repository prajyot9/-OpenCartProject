package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = { "Sanity", "Master" })
	public void verify_login() {

		// After creating a method always insure to add logger if you need logs
		logger.info("**********Login Test Case Start***************");

		try {
			// home Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.ClickLogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("pwd"));
			lp.clickLogin();

			// MyAccountPage

			MyAccountPage macc = new MyAccountPage(driver);
			boolean target = macc.isMyAccountPageExists();
			Assert.assertTrue(target);// Assert.assertEquals(target, true,"Login Failed");
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("**********FinishLogin Test Case Start***************");

	}
}
