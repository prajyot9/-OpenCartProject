package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	// As DataProvider class are in different package so that's why we are using
	// this to use it in this class.
	// dataProviderClass=DataProviders.class,groups="DataDriven"
	// If data provider is present in same class then that is not required.
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void verfiy_loginDDT(String email, String pwd, String exp) {

		logger.info("**************Start Test Case*****************");

		// HomePage
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.ClickLogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			// MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean target = macc.isMyAccountPageExists();

			/*
			 * Data is Valid -- Login Success - TestCase Pass --Logout Login Failed -
			 * TestCase Fail Data is InValid -- Login Success - TestCase Fail --Logout Login
			 * Failed - TestCase Pass
			 */
			if (exp.equalsIgnoreCase("Valid")) {
				if (target == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (target == true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("************End Login DDT Test****************");
	}
}
