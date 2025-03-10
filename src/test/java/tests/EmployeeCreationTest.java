package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testComponents.Retry;

import junit.framework.Assert;
import pageObjects.AddEmployee;
import pageObjects.Dashboard;
import pageObjects.ViewEmployee;
import pageObjects.ViewPersonalDetails;
import testComponents.BaseTest;

public class EmployeeCreationTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void CreateEmployee(HashMap<String, String> input) {
		Dashboard dashboard = landingPage.login("Admin", "admin123");
		ViewEmployee employeePage = dashboard.viewEmployeePage();
		AddEmployee addPage = employeePage.addEmployee();
		ViewPersonalDetails employeeAdded = addPage.addEmployee(input.get("fName"), input.get("lName"));
		//return employeeAdded;
	}

	@Test(dependsOnMethods = { "CreateEmployee" }, dataProvider="getData", retryAnalyzer=Retry.class)
	public void verifyEmployeeCreation(HashMap<String, String> input) {
		Dashboard dashboard = landingPage.login("Admin", "admin123");
		ViewEmployee employeePage = dashboard.viewEmployeePage();
		Assert.assertTrue(employeePage.verifyEmployee(input.get("fName"), input.get("lName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//EmployeeData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
