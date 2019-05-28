package com.api.msg.stepsdefinations;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.api.msg.pages.MSGEventEngineURLs;
import SupportReadConfig.testconfig;
import SupportWebDriver.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import supportCucumber.runningscenario;
import supportMethods.FileRead;
import supportMethods.SauceLab;

public class Hooks 
{
	private static Boolean runOnce = false;

	@Before
	public void beforeAll(Scenario scenario) throws FileNotFoundException, IOException, InterruptedException 
	{
		System.out.println("\n******* Started Before Hook*********");
		
		if (!runOnce) 
		{
			testconfig.config = FileRead.readProperties();
			MSGEventEngineURLs.ENV = testconfig.config.get("RUN_ENV");
			runOnce=true;
		}
		
		runningscenario.scenario = scenario;
	}
	

	@After
	public void after(Scenario scenario) 
	{
		System.out.println("***** Started After Hook*********");
		
		if (scenario.isFailed()) 
		{
			Driver.embedScreenshot();
		}
		
		//==>> Sending Test Scenario Status Pass/Fail to Sauce Lab If  Test is Running in Sauce-Lab
		if(testconfig.config.get("seleniumEnvironment").equals("sauceLab") && scenario.isFailed())
		{
			SauceLab.sendPAssFailToSauce(false);
		}
		else if(testconfig.config.get("seleniumEnvironment").equals("sauceLab"))
		{
			SauceLab.sendPAssFailToSauce(true);
		}
	}

}
