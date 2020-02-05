package weekendstatsautomationreports.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserSetup {

	public WebDriver driver = null;
	String chromeDriverPath = "C:\\ChromeDriver\\chromedriver.exe";

	public WebDriver getBrowser(String URL) 
	{
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		
		/*ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(URL);

		return driver;
	}
}
