package com.api.msg.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import SupportSeleniumUIActions.WebUI;
import SupportWebDriver.Driver;

public class EE_CommonSideBar
{
	//static WebDriver driver = Driver.getCurrentDriver();
	WebDriver driver;
	WebUI selenium;

	
	@FindBy(xpath="//*[@class='sidebar-wrapper']")
	public WebElement sideNavigationBar;
	
	/** Default Constructor  */
	public EE_CommonSideBar(WebDriver driver) 
	{
		//==>> Loading All Web-Element Object Using Page Factory
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	    
		//==>> Initialize SELENIUM wrapper that will help to perform all UI Related Action   
		selenium = new WebUI(driver,null,null);
	}

	
	public Object NavigateTo(String PageName)
	{
		if(PageName.equalsIgnoreCase("EventPage"))
		{
			selenium.ClickLink(driver.findElement(By.xpath("//a[@href='/events']")));		
			EE_EventsPage eventpage =new EE_EventsPage(driver);
			return eventpage;
		}
		
		return null;
	}
	
	
}
