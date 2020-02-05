package weekendstatsautomationreports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import weekendstatsautomationreports.common.BrowserSetup;
import weekendstatsautomationreports.common.LoginApp;
import weekendstatsautomationreports.grafanareports.GrafanaReports;
import weekendstatsautomationreports.utilities.ExcelWrite;

public class GrafanaReportsToExcel {

	public static void main(String args[]) throws Exception

	{
		WebDriver driver;
		String URL = "https://backoffice-lcm.ladbrokes.com/grafana/d/AlwGmf9jk/performance-report?orgId=1";
		String username = "ladbrokes";
		String password = "lizXc0FD8E";
		String fromDate = "2019-09-07 00:00:00";
		String toDate = "2019-09-07 23:59:59";
		
		try {
		//Initializing WebBrowser and getting application URL
		BrowserSetup browser = new BrowserSetup();
		driver = browser.getBrowser(URL);

		//Login into Grafana Application
		LoginApp login = new LoginApp(driver);
		login.loginApp(username, password);
		
		//Setting up Reports Date Range
		GrafanaReports grafanaReports = new GrafanaReports(driver);
		grafanaReports.setDate(fromDate, toDate);
		
		//Getting Reports from Grafana
		LinkedHashMap<String, String> reportMap = grafanaReports.getGrafanaReports();
		
		//W%teriting GrafanaReports into Excel_Sheet
		ExcelWrite excel = new ExcelWrite();
		
		//To generate current date with the format "MM-dd_HH-mm-ss"
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd_HH-mm-ss");	//To set Date format
		Date currentDate = new Date();		//To get current system Date
		String date = simpleDateFormat.format(currentDate);
		
		excel.writeToExcel("GrafanaReports_"+date, reportMap);
		
		driver.quit();
		
		}catch(Exception e) 
		{
			e.printStackTrace();
		}

	}

}
