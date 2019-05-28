package supportFactory;

import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import SupportReadConfig.testconfig;
import SupportWebDriver.Driver;
import supportMethods.BrowserStack;
import supportMethods.SauceLab;

public class WebdriverFactory {

	private static class BrowserCleanup implements Runnable 
	{
		public void run() 
		{
			System.out.println("Cleaning up the browser");
			try 
			{ 
				//Driver.webdriver.quit();    // Commented to Pause The Drive Quite at the end of test !!
			} 
			catch (NullPointerException e) 
			{
				System.out.println("Browser already shut down.");
			}
		}
	}
	

	public static WebDriver createWebdriver() 
	{
		caps = new DesiredCapabilities();
		String seleniumEnvironment = testconfig.config.get("seleniumEnvironment");
		
		if (seleniumEnvironment.equals("local")) 
		{
			Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			return BrowserFactory.selectLocalBrowser();
		} 
		else 
		{
			if (seleniumEnvironment.equals("browserstack")) 
			{
				caps.merge(BrowserStack.setBrowserCapabilities());
				caps.merge(BrowserStack.setProjectDetails());
				BrowserStack.setSeleniumHub();
			}
			
			if (seleniumEnvironment.equalsIgnoreCase("sauceLab")) 
			{
				caps.merge(SauceLab.setBrowserCapabilities());
				caps.merge(SauceLab.setProjectDetails());
				SauceLab.setSeleniumHub();
			}
			
			PlatformFactory.selectPlatform(caps);
			BrowserFactory.selectBrowser(caps);
			WebdriverFactory.caps.merge(additionalCapabilities);
			
			String seleniumHub = testconfig.config.get("seleniumHub");
						
			try 
			{
				return new RemoteWebDriver(new URL(seleniumHub), caps);
			} 
			catch (WebDriverException e) 
			{
				Driver.writeToReport("WebDriverException: " + e.getMessage());
				Assert.fail(e.getMessage());
			}
			catch (Exception e) 
			{
				Driver.writeToReport(e.getMessage());
			} 
			finally 
			{
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}
		}
		return null;
	}

	public static DesiredCapabilities caps;
	public static DesiredCapabilities additionalCapabilities = new DesiredCapabilities();

}
