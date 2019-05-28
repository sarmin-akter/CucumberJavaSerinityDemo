package com.api.msg.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"/Users/rasulm/eclipse-workspace/MSGEventEngine_Cucumber/eventengine-cucumber-java-framework/src/test/resources/EEFeatureFiles/EE_AdminUIEventPage.feature:20"},
        plugin = {"json:/Users/rasulm/eclipse-workspace/MSGEventEngine_Cucumber/eventengine-cucumber-java-framework/target/cucumber-parallel/json/3.json"},
        monochrome = true,
        glue = {"com.api.msg.stepsdefinations"})
public class SampleRunner03IT {
}
