package weekendstatsautomationreports.dareboostreports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DareboostLoginPage {

	WebDriver driver;
	WebDriverWait wait;

	public DareboostLoginPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public boolean loginApp(String userName, String password) 
	{
		By SectionA = By.xpath("//a[text()='Section A. Betting']");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"username\"]")));

		driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(userName);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class=\"btn btn-large p-x-2 btn-primary\"]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(SectionA));
		Boolean isLogin  = driver.findElement(SectionA).isDisplayed();

		return isLogin;



	}
}
