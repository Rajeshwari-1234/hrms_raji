package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	private WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	private WebElement username;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(css="[type='submit']")
	private WebElement submitBtn;
	
	@FindBy(css=".oxd-text.oxd-text--p.orangehrm-login-forgot-header")
	private WebElement forgotPass;
	
	@FindBy(css=".oxd-alert.oxd-alert--error")
	private WebElement invalidCreds;
	
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/p")
	private WebElement validCreds;
	
	public void goTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	public Dashboard login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		submitBtn.click();
		return new Dashboard(driver);
	}
	
	public ResetPassword forgotPassword() {
		forgotPass.click();
		return new ResetPassword(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(invalidCreds);
		return invalidCreds.getText();
	}
	
	public String getValidMessage() {
		waitForWebElementToAppear(validCreds);
		return validCreds.getText();
	}

}
