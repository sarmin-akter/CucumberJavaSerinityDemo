package supportMethods;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import SupportReadConfig.testconfig;


public class BrowserStack 
{
	public static Boolean useBrowserStack() 
	{
		
		return Boolean.valueOf(testconfig.config.get("useBrowserstack"));
	}
	
	public static void setSeleniumHub() 
	{
		
		String browserstackUsername = testconfig.config.get("browserstackUsername");
		
		if (browserstackUsername == null) {
			throw new WebDriverException("Browserstack username not set.");
		}
		
		String browserstackPassword = testconfig.config.get("browserstackPassword");
		
		if (browserstackPassword == null) {
			throw new WebDriverException("Browserstack password not set.");
		}
		
		testconfig.config.put("seleniumHub", "http://" + browserstackUsername + ":" + browserstackPassword + "@hub.browserstack.com/wd/hub");
	}
	
	public static DesiredCapabilities setBrowserCapabilities() 
	{
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability("resolution", testconfig.config.get("browserstackResolution"));
		
		return caps;
	}
	
	public static DesiredCapabilities setProjectDetails() 
	{
		
		String project = testconfig.config.get("browserstackProject");
		String build = testconfig.config.get("browserstackBuild");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("project", project);
		caps.setCapability("build", build);
		
		return caps;
	}
}
