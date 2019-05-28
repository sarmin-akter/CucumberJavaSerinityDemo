package supportMethods;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;


import SupportReadConfig.testconfig;
import SupportWebDriver.Driver;
import supportCucumber.runningscenario;


public class SauceLab 
{
	
	public static String sauceLabUsername;
	public static String sauceLabAccessKey;

	public static Boolean useSauceLab() 
	{
		
		return Boolean.valueOf(testconfig.config.get("useSauceLab"));
	}
	
	public static void setSeleniumHub() 
	{
		
			sauceLabUsername = testconfig.config.get("sauceLabUsername");
		
		if (sauceLabUsername == null) {
			throw new WebDriverException("sauceLabUsername username not set.");
		}
		
		 	sauceLabAccessKey = testconfig.config.get("sauceLabAccessKey");
		
		if (sauceLabAccessKey == null) {
			throw new WebDriverException("sauceLab AccessKey not set.");
		}
		
		testconfig.config.put("seleniumHub", "https://" + sauceLabUsername + ":" + sauceLabAccessKey + "@ondemand.saucelabs.com:443/wd/hub");

	}
	
	public static DesiredCapabilities setBrowserCapabilities() 
	{
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		//caps.setCapability("browserstack.debug", "true");
		//caps.setCapability("resolution", testconfig.config.get("browserstackResolution"));
		
		return caps;
	}
	
	public static DesiredCapabilities setProjectDetails() 
	{
		
		String project = testconfig.config.get("browserstackProject");
		String build = testconfig.config.get("browserstackBuild");
		
		String RunningFeature = runningscenario.scenario.getId().split(";")[0].replace("-"," ");
		String RunningScenarioName = runningscenario.scenario.getId().split(";")[1].replace("-"," ");

		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("project", project);
		caps.setCapability("build", build);
		caps.setCapability("name", RunningFeature + " - " +RunningScenarioName );

		
		return caps;
	}
	
	public static void sendPAssFailToSauce(boolean passOrFail)
	{
		WebDriver driver = Driver.getCurrentDriver();
		SessionId mySessionID = ((RemoteWebDriver)driver).getSessionId();	
		
		SauceJobAccessController mySauceJobStatusUpdate = new SauceJobAccessController(sauceLabUsername, sauceLabAccessKey);
		
		Map<String, Object> myUpdates = new HashMap<String, Object>();	//		
		if (passOrFail == false )//
			myUpdates.put("passed", false);					
		else
			myUpdates.put("passed", true);						
		try 
		{
			mySauceJobStatusUpdate.updateJobInfo(mySessionID.toString(), myUpdates);
			System.out.println("Saucelabs Job Status is updated via Saucelabs REST API.");
		} 
		catch (Exception e) 
		{
			System.out.println("Saucelabs Job Status can't be updated via Saucelabs REST API.");
			System.err.println(e.getMessage());
		}

		
	}
	
	
}
