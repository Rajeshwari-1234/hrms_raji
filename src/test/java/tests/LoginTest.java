package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import reusableComponents.CommonConstants;
import reusableComponents.ExcelOperations;

import org.testng.Assert;
import testComponents.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


public class LoginTest extends BaseTest {
	
	String fileName = "\\src\\main\\java\\resources\\Hrms_Logins.xlsx";
	ExcelOperations  excel=new ExcelOperations(fileName,"Invalid");
	ExcelOperations  excel1=new ExcelOperations(fileName,"Valid");
	
	@Test(dataProvider = "InvalidHRMSLogin")
	public void incorrectLogin(Object obj1) throws IOException {
		
	    HashMap<String, String> testData = (HashMap<String, String>) obj1;
	    landingPage.login(testData.get(CommonConstants.USERNAME).toString(), testData.get(CommonConstants.PASSWORD).toString());
		Assert.assertEquals("Invalid credentials", landingPage.getErrorMessage());
	
	}
	
	@Test(dataProvider = "ValidHRMSLogin")
	public void correctLogin(Object obj1) throws IOException {
		
	    HashMap<String, String> testData = (HashMap<String, String>) obj1;
	    landingPage.login(testData.get(CommonConstants.USERNAME).toString(), testData.get(CommonConstants.PASSWORD).toString());
		Assert.assertEquals("Time at Work", landingPage.getValidMessage());
		
	}
	
	@DataProvider(name = "InvalidHRMSLogin")
	public Object[][] invalidTestDataSupplier() throws Exception {
		Object[][] obj = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i - 1][0] = testData;
		}
		return obj;

	}
	
	
	@DataProvider(name = "ValidHRMSLogin")
	public Object[][] validTestDataSupplier() throws Exception {
		Object[][] obj = new Object[excel1.getRowCount()][1];
		for (int i = 1; i <= excel1.getRowCount(); i++) {
			HashMap<String, String> testData = excel1.getTestDataInMap(i);
			obj[i - 1][0] = testData;
		}
		return obj;

	}
	
}
