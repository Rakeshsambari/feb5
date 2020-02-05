package weekendstatsautomationreports;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import weekendstatsautomationreports.common.BrowserSetup;
import weekendstatsautomationreports.googleanalyticsstats.GoogleAnalyticsLoginPage;
import weekendstatsautomationreports.googleanalyticsstats.GoogleAnalyticsStatsReports;
import weekendstatsautomationreports.utilities.ExcelWrite;

public class GoogleAnalyticsReportsToExcel {

	public static void main(String[] args) throws Exception {
		WebDriver driver;
		String URL = "https://analytics.google.com/analytics/web/#/";
		String gmailId = "rsambari@ivycomptech.com";
		String username = "rsambari@icepor.com";
		String password = "asus@2019";
		String fromDate = "18 Apr 2019";
		String toDate = "18 Apr 2019";
		String AccountOne_AllWebSiteData_Id = "157852632";
		String LadbrokesMobile_AllCountriesAllProducts_Id = "75271695";
		String LadbrokerCom_AllCountriesAccount_Id = "85706451";

		//Initializing WebBrowser and getting application URL
		BrowserSetup browser = new BrowserSetup();
		driver = browser.getBrowser(URL);

		//Login into GA Application
		GoogleAnalyticsLoginPage login = new GoogleAnalyticsLoginPage(driver);
		login.loginApp(gmailId, username, password);

		GoogleAnalyticsStatsReports gaStats = new GoogleAnalyticsStatsReports(driver);
		ExcelWrite excel = new ExcelWrite();

		LinkedHashMap<String, String> allCountriesAccount = gaStats.getPage_SessionMaxValues(LadbrokerCom_AllCountriesAccount_Id, fromDate, toDate);		
		excel.writeToExcel("GA_AllCountriesAccount", allCountriesAccount);

		LinkedHashMap<String, String> allWebSiteData = gaStats.getPage_SessionMaxValues(LadbrokesMobile_AllCountriesAllProducts_Id, fromDate, toDate);
		excel.writeToExcel("GA_AllCountriesAllProducts", allWebSiteData);

		LinkedHashMap<String, String> allCountriesAllProducts = gaStats.getPage_SessionMaxValues(AccountOne_AllWebSiteData_Id, fromDate, toDate);
		excel.writeToExcel("GA_AllWebSiteData", allCountriesAllProducts);

		driver.quit();
	}

}
