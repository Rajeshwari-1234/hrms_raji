package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ResetPassword extends AbstractComponent {
	
	private WebDriver driver;
	
	public ResetPassword(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	private WebElement username;
	
	@FindBy(css="[type='submit']")
	private WebElement submitBtn;
	
	@FindBy(css=".oxd-text.oxd-text--h6.orangehrm-forgot-password-title")
	private WebElement message;
	
	
	public String reset() {
		username.sendKeys("Admin");
		submitBtn.click();
		return message.getText();
	}
}
