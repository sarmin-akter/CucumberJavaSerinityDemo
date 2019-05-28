package com.api.msg.testrunner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(		
					features = { "src/test/resources/EEFeatureFiles/EE_AdminUIEventPage.feature" },
					glue = { "com.api.msg.stepsdefinations"},
					tags = { "~@ignore"},
					plugin = {"pretty"})

public class EETestRunnerWithSerenity {}
