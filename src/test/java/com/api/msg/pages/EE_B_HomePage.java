package com.api.msg.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import SupportSeleniumUIActions.WebUI;
import SupportWebDriver.Driver;

public class EE_B_HomePage
{
	
	//static WebDriver driver = Driver.getCurrentDriver();
	WebDriver driver;
	WebUI selenium;
	
	@FindBy(xpath="//a[@href='/logout']")
	public WebElement logoutLinkIcon;

	
	public EE_CommonSideBar sideNavBar;

	/** Default Constructor  */
	public EE_B_HomePage(WebDriver driver) 
	{
		
		//==>> Loading All Web-Element Object Using Page Factory
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	    
		//==>> Initialize SELENIUM wrapper that will help to perform all UI Related Action   
		selenium = new WebUI(driver,null,null);

		//=== All Logged In Page need this Side bar to Navigate 
		sideNavBar = new EE_CommonSideBar(driver);
	}
	
	public void goTo(String Url)
	{
		selenium.NavigateToPage(Url);
	}
	
	public boolean ValidateWeInEventEngineDashBoard()
	{
		if(selenium.ValidateElementExist(logoutLinkIcon)==true)
		{
			return true;
		}
		return false;
	}
	
	public EE_EventsPage gotoEventsPage()
	{
		EE_EventsPage EventsPage = (EE_EventsPage) sideNavBar.NavigateTo("EventPage") ;
		return EventsPage;
	}
	
}
