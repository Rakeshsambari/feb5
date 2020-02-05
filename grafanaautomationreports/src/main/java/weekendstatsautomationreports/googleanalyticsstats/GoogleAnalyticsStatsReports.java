package weekendstatsautomationreports.googleanalyticsstats;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author rsambari
 *
 */
public class GoogleAnalyticsStatsReports {

	WebDriver driver;

	public GoogleAnalyticsStatsReports(WebDriver driver) 
	{
		this.driver = driver;
	}

	//WebElements
	By Badge360 = By.xpath("//img[@alt='360 badge']");

	By customizationMenuLocator = By.xpath("//span[text()='Customisation']");
	By customReportsSubMenuLocator = By.xpath("//span[text()='Custom Reports']");

	By newCustomReportTabLocator = By.xpath("//input[contains(@value,'New Custom Report')]");
	By addMetricBtnLocator = By.xpath("//div[text()=' + add metric ']");
	By pickerLocator = By.xpath("//input[@class='ID-searchBox ']");
	By pageViewsPickerLocator = By.xpath("//div[text()='Page Views']");

	By usersSessionsPickerLocator = By.xpath("(//div[text()='Sessions'])[2]");
	By addDimensionLocator = By.xpath("//div[text()=' + add dimension ']");
	By dimensionPicker = By.xpath("//div[contains(@class,'ID-dimensionEditor _GAVR')]//input[@class='ID-searchBox ']");
	By timeHourLocator = By.xpath("//div[text()='Hour']");
	By saveBtnLocator = By.xpath("//div[text()='Save']");

	By dateLocator = By.xpath("//div[@data-guidedhelpid='date-picker-container']");
	By startDateLocator = By.xpath("//input[contains(@class, 'TARGET-primary_start')]");
	By endDateLocator = By.xpath("//input[contains(@class, 'TARGET-primary_end')]");
	By applyBtnLocator = By.xpath("//input[contains(@class, 'DATECONTROL') and @value='Apply']");

	By pageViewsFirstLocator = By.xpath("(//table[@id='ID-rowTable']/tbody/tr//td[contains(@class, 'pageviews')])[1]");
	By sessionsTableHeaderLocator = By.xpath("//th[text()='Sessions']");
	By sessionsFirstLocator = By.xpath("(//table[@id='ID-rowTable']/tbody/tr//td[contains(@class, 'visits')])[1]");

	public void selectAccount(String searchId) throws InterruptedException 
	{
		Thread.sleep(10000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement badgeEL = driver.findElement(Badge360);
		js.executeScript("arguments[0].click();", badgeEL);

		Thread.sleep(10000);	
		driver.findElement(By.xpath("(//input[@placeholder='Search'])[2]")).sendKeys(searchId);
		Thread.sleep(10000);
		WebElement allcountries = driver.findElement(By.xpath("//*[contains(text(),'Tracking ID')]"));
		js.executeScript("arguments[0].click();", allcountries);

		Thread.sleep(10000);
	}

	public void navigateCustomReports() 
	{
		driver.findElement(customizationMenuLocator).click();
		driver.findElement(customReportsSubMenuLocator).click();
	}

	public void setupCustomReports() throws InterruptedException 
	{
		Thread.sleep(5000);
		driver.findElement(newCustomReportTabLocator).click();

		Thread.sleep(5000);
		driver.findElement(addMetricBtnLocator).click();
		driver.findElement(pickerLocator).sendKeys("Page Views");
		driver.findElement(pageViewsPickerLocator).click();

		driver.findElement(addMetricBtnLocator).click();
		Thread.sleep(5000);
		driver.findElement(pickerLocator).sendKeys("Sessions");
		driver.findElement(usersSessionsPickerLocator).click();

		driver.findElement(addDimensionLocator).click();
		Thread.sleep(5000);
		driver.findElement(dimensionPicker).sendKeys("Hour");
		driver.findElement(timeHourLocator).click();
		driver.findElement(saveBtnLocator).click();
		Thread.sleep(10000);
	}

	public void setDateRange(String startDate, String endDate) throws Exception 
	{
		driver.findElement(dateLocator).click();
		
		WebElement startDateElement = driver.findElement(startDateLocator);
		startDateElement.clear();
		startDateElement.sendKeys(startDate);
		
		WebElement toDateElement = driver.findElement(endDateLocator);
		toDateElement.clear();
		toDateElement.sendKeys(endDate);
		
		driver.findElement(applyBtnLocator).click();
	}

	public String getPageviewMaxValue() 
	{
		String pageviewsMax = driver.findElement(pageViewsFirstLocator).getText();
		System.out.println("pageviewsMax "+pageviewsMax);
		return pageviewsMax;
	}

	public String getSessionMaxValue() throws Exception
	{
		Thread.sleep(10000);
		driver.findElement(sessionsTableHeaderLocator).click();
		Thread.sleep(5000);
		String sessionMax = driver.findElement(sessionsFirstLocator).getText();
		System.out.println("sessionMax "+sessionMax);
		return sessionMax;
	}

	public LinkedHashMap<String, String> getPage_SessionMaxValues(String accountId, String fromDate, String toDate) throws Exception 
	{
		selectAccount(accountId);
		navigateCustomReports();
		driver.switchTo().frame("galaxyIframe");
		setupCustomReports();
		setDateRange(fromDate, toDate);
		String pageMax = getPageviewMaxValue();
		String sessionMax = getSessionMaxValue();
		driver.switchTo().defaultContent();

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Account", accountId);
		map.put("PageViews", pageMax);
		map.put("Sessions", sessionMax);

		return map;
	}

}
