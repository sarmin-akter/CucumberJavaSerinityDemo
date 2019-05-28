package com.api.msg.testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources/EEFeatureFiles/EE_AdminUIEventPage.feature" },
		
		 //==>> Below "SupportWebDriver" glue was invoking an auto web-driver   
		//glue = { "SupportWebDriver", "com.api.msg.stepsdefinations", "com.api.msg.testrunner" },
		
		 glue = { "com.api.msg.stepsdefinations"},
		tags = { "~@ignore"},
		plugin = {"pretty"})

public class EETestRunner {}
