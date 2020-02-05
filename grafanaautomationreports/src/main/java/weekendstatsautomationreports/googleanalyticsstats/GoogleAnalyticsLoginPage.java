package weekendstatsautomationreports.googleanalyticsstats;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleAnalyticsLoginPage {

	WebDriver driver;
	WebDriverWait wait;

	public GoogleAnalyticsLoginPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public boolean loginApp(String GmailId, String userName, String password) 
	{
		By GmailLocator = By.id("identifierId");
		By GmailNextBtnLocator = By.xpath("//span[contains(text(), 'Next')]");

		By usernameLocator = By.id("okta-signin-username");
		By passwordLocator = By.id("okta-signin-password");
		By signInLocator = By.id("okta-signin-submit");
		
		By continueLocator = By.xpath("//span[contains(text(), 'Continue')]");

		By googleHomeValidator = By.xpath("//h1[contains(text(), 'Google Analytics Home')]");

		driver.findElement(GmailLocator).sendKeys(GmailId);
		driver.findElement(GmailNextBtnLocator).click();

		driver.findElement(usernameLocator).sendKeys(userName);
		driver.findElement(passwordLocator).sendKeys(password);
		driver.findElement(signInLocator).click();
		
		driver.findElement(continueLocator).click();

		boolean isLogin  = driver.findElement(googleHomeValidator).isDisplayed();

		return isLogin;
	}

}
