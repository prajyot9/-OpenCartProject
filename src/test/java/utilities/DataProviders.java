package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProviders 1

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\Opencart_loginData.xlsx"; // Taking xl file from testData

		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1); // 1 means staring from row 1

		String logindata[][] = new String[totalrows][totalcols];// created for two dimensional array which can store the
																// value the same no of rows and col has been created which present in excel

		for (int i = 1; i < totalrows; i++) {//1  //read the data from xl storing in two dimensional array
			for (int j = 0; j < totalcols; j++) {//0    i is rows j is col
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);// 1,0 //As array starting from zero that's why we used here i-1
			}
		}

		return logindata;//returning two dimensional array

	}

}
