package weekendstatsautomationreports.grafanareports;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Rakesh
 *
 */

public class GrafanaReports {

	WebDriver driver;
	WebDriverWait wait;
	String BP_TotalMax,  BS_TotalMax, CustomerLoginsCount, CashoutsTotal, CashoutOffersMax, EditMyAccaOffersMax, AmelcoMax_Peak_requests_Max,TOps_PeakInserts_Max,TOps_PeakInserts_Avg,TOps_PeakUpdates_Max,TOps_PeakUpdates_Avg;

	By maxValue = By.xpath("//a[@title='Logins count']/following-sibling::div[contains(@class,'max')]");    
	By Test = By.xpath("//a[@class=\"graph-legend-alias pointer\" and @title='Total']/..//div[contains(@class,'max')]");
	By VisibileElement = By.xpath("//div[@id='panel-1']/descendant::a[@class='graph-legend-alias pointer' and @title='Total'][1]/following-sibling::div[1]");
	By SectionC = By.xpath("//a[text()='Section C. Cashouts']");
	By SectionD = By.xpath("//a[text()='Section D. Trading activity']");
	By CashoutsTotalPath = By.xpath("//span[contains(text(), 'Cashouts')]/ancestor::div[1]/following-sibling::div//*[@title='Total']/..//div[contains(@class,'max')]");
	By CashoutSQs = By.xpath("//a[@title='Cashout offers']/following-sibling::div[contains(@class,'max')]");
	By EMAquotes = By.xpath("//a[@title='Edit My Acca offers']/following-sibling::div[contains(@class,'max')]");
	By SectionE = By.xpath("//a[text()='Section E. Important OXi requests performance']");
	By SectionF = By.xpath("//a[text()='Section F. Connections']");
	By AmelcoMaxPath = By.xpath("//a[@title='Amelco']/following-sibling::div[contains(@class,'max')]");
	By SectionA = By.xpath("//a[text()='Section A. Betting']");
	By BS_TotalMax_Xpath = By.xpath("//div[@id='panel-4']/descendant::a[@class='graph-legend-alias pointer' and @title='Total'][1]/following-sibling::div[1]");
	By SectionB = By.xpath("//a[text()='Section B. Customer logins + registrations']");
	By Peak_InserstsMax_path=By.xpath("//a[@title='Inserts']/following-sibling::div[contains(@class,'max')]");
	By Peak_InserstsAvg_path=By.xpath("//a[@title='Inserts']/following-sibling::div[contains(@class,'avg')]");
	By Peak_UpdatesMax_path=By.xpath("//a[@title='Updates']/following-sibling::div[contains(@class,'max')]");
	By Peak_UpdatesAvg_path=By.xpath("//a[@title='Updates']/following-sibling::div[contains(@class,'avg')]");



	public GrafanaReports(WebDriver driver) {
		this.driver = driver;
	}

	public void setDate(String fromDate, String toDate) 
	{	
		driver.findElement(By.xpath("//button[@bs-tooltip='ctrl.tooltip']")).click();
		driver.findElement(By.xpath("//form[@name='timeForm']/descendant::input[1]")).clear();
		driver.findElement(By.xpath("//form[@name='timeForm']/descendant::input[1]")).sendKeys(fromDate);
		driver.findElement(By.xpath("//form[@name='timeForm']/descendant::input[2]")).clear();
		driver.findElement(By.xpath("//form[@name='timeForm']/descendant::input[2]")).sendKeys(toDate);
		driver.findElement(By.xpath("//button[text()='Apply']")).click();
	}

	//Section-A - BetPlacement
	public String getBetPlacementTotalMax() throws Exception 
	{
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SectionA));
		if(isSectionCollapsed("A"))
			driver.findElement(SectionA).click();
		scrollDown("B");
		wait.until(ExpectedConditions.visibilityOfElementLocated(VisibileElement));
		BP_TotalMax = driver.findElement(VisibileElement).getText();

		return BP_TotalMax;
	}

	//Section-A - BetSettlement
	public String getBetSettlementTotalMax() throws Exception 
	{	
		BS_TotalMax = driver.findElement(BS_TotalMax_Xpath).getText();

		return BS_TotalMax;
	}

	//Section B. Customer logins + registrations
	public String getCustomerLoginsCount() throws Exception 
	{	
		wait.until(ExpectedConditions.visibilityOfElementLocated(SectionB));
		if(isSectionCollapsed("B"))
			driver.findElement(SectionB).click();
		scrollDown("C");	
		wait.until(ExpectedConditions.visibilityOfElementLocated(maxValue));
		CustomerLoginsCount = driver.findElement(maxValue).getText();

		return CustomerLoginsCount;
	}

	//Section Cashouts
	public String getCashoutsTotal() throws Exception 
	{	
		wait.until(ExpectedConditions.visibilityOfElementLocated(SectionC));
		if(isSectionCollapsed("C"))
			driver.findElement(SectionC).click();
		scrollDown("D");	
		wait.until(ExpectedConditions.visibilityOfElementLocated(CashoutsTotalPath));
		CashoutsTotal = driver.findElement(CashoutsTotalPath).getText();
		System.out.println(CashoutsTotal);

		return CashoutsTotal;
	}

	//CashoutServiceQuotes
	public String getCashoutsServiceQuotes() throws Exception 
	{		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CashoutSQs));
		CashoutOffersMax = driver.findElement(CashoutSQs).getText();

		return CashoutOffersMax;
	}

	//EMAquotes
	public String getEMAQuotes() throws Exception 
	{		
		wait.until(ExpectedConditions.visibilityOfElementLocated(EMAquotes));
		EditMyAccaOffersMax = driver.findElement(EMAquotes).getText();

		return EditMyAccaOffersMax;
	}

	// SectionD peak re/min/Trading volume by user/feed Amelco max
	public String getTradingVolumeByUser_Feed() throws Exception 
	{		
		wait.until(ExpectedConditions.visibilityOfElementLocated(SectionD));

		if(isSectionCollapsed("D"))
			driver.findElement(SectionD).click();

		scrollDown("E");
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(AmelcoMaxPath));
		AmelcoMax_Peak_requests_Max = driver.findElement(AmelcoMaxPath).getText();

		System.out.println("Amelco max"+AmelcoMax_Peak_requests_Max);

		return AmelcoMax_Peak_requests_Max;
	}

	public String getTradingOperations_Inserts_Max() throws Exception
	{

		scrollDown_TradeOps();
		Thread.sleep(3000);		
		wait.until(ExpectedConditions.visibilityOfElementLocated(Peak_InserstsMax_path));
		TOps_PeakInserts_Max = driver.findElement(Peak_InserstsMax_path).getText();

		return TOps_PeakInserts_Max;

	}

	public String getTradingOperations_Inserts_Avg() throws Exception
	{

		wait.until(ExpectedConditions.visibilityOfElementLocated(Peak_InserstsAvg_path));
		TOps_PeakInserts_Avg = driver.findElement(Peak_InserstsAvg_path).getText();

		return TOps_PeakInserts_Avg;

	}

	public String getTradingOperations_Updates_Max() throws Exception
	{

		wait.until(ExpectedConditions.visibilityOfElementLocated(Peak_UpdatesMax_path));
		TOps_PeakUpdates_Max = driver.findElement(Peak_UpdatesMax_path).getText();

		return TOps_PeakUpdates_Max;

	}

	public String getTradingOperations_Updates_Avg() throws Exception
	{

		wait.until(ExpectedConditions.visibilityOfElementLocated(Peak_UpdatesAvg_path));
		TOps_PeakUpdates_Avg = driver.findElement(Peak_UpdatesAvg_path).getText();

		return TOps_PeakUpdates_Avg;

	}

	public boolean isSectionCollapsed(String section) 
	{
		By sectionCollapsed = By.xpath("//a[contains(text(), 'Section "+section+"')]/ancestor::div[contains(@class, 'row--collapsed')]");
		try {
			return driver.findElement(sectionCollapsed).isDisplayed();	
		}catch(NoSuchElementException e) 
		{
			return false;
		}
	}


	public LinkedHashMap<String, String> getGrafanaReports() throws Exception

	{
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();  //creating object for linked hashmap
		map.put("Peakbets", getBetPlacementTotalMax());        //assigning key-value pair
		map.put("PeakSettlements", getBetSettlementTotalMax());
		map.put("PeakLogins", getCustomerLoginsCount());
		map.put("PeakCashouts", getCashoutsTotal());
		map.put("PeakCashoutdetails", getCashoutsServiceQuotes());
		map.put("PeakEditMyAcca", getEMAQuotes());
		map.put("AmelcoMax/Peak requests/Min", getTradingVolumeByUser_Feed());
		map.put("TradingOperations_PeakInserts_Max", getTradingOperations_Inserts_Max());
		map.put("TradingOperations_Inserts_Avg", getTradingOperations_Inserts_Avg());
		map.put("TradingOperations_Updates_max", getTradingOperations_Updates_Max());
		map.put("TradingOperations_Updates_Avg", getTradingOperations_Updates_Avg());

		return map;
	}


	public void scrollDown(String section) throws Exception {

		By sectionPath = By.xpath("//a[contains(text(), 'Section "+section+"')]");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(sectionPath);
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}


	public void scrollDown_TradeOps() throws Exception {

		String fromDate = "2019-06-30 15:00:00";
		String toDate = "2019-06-30 17:00:00";
		setDate(fromDate, toDate);

		By sectionPath1 = By.xpath("//span[contains(text(),'Incoming Trading Requests')]");
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(sectionPath1);
		js1.executeScript("arguments[0].scrollIntoView();", Element1);

	}


}

