package com.api.msg.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SupportSeleniumUIActions.WebUI;
import SupportWebDriver.Driver;

public class EE_A_LoginPage 
{

	//static WebDriver driver = Driver.getCurrentDriver();
	WebDriver driver;
	WebUI selenium;
	
	//###### Locators :-   *****
	@FindBy(xpath="//div/input[@name='email']")
	public WebElement email;
	@FindBy(xpath="//div/input[@name='password']")
	public WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	public WebElement btnLogin;
	
	
	/** Default Constructor  */
	public EE_A_LoginPage(WebDriver driver) 
	{
		//==>> Loading All Web-Element Object Using Page Factory
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	    
		//==>> Initialize SELENIUM wrapper that will help to perform all UI Related Action   
		selenium = new WebUI(driver,null,null);
	}
	
	public boolean goToLoginPage()
	{
		String sLoginPageURL = MSGEventEngineURLs.getEnvDetails().get("URL"); 
		selenium.NavigateToPage(sLoginPageURL);
		
		//==>> Check Point :  Validate Email Id is Visible on the page
		if(selenium.ValidateElementExist(email)==true)
		{
			return true;
		}
		return false;
	}
	
	public Object doLogin(String input)
	{
		//=========>> Input Parsing  
		if(input.toLowerCase().equals("superadmin")) // If the Input user is Super-Admin 
		{
			input = MSGEventEngineURLs.getEnvDetails().get("EE_SUPERADMIN") + "|" + MSGEventEngineURLs.getEnvDetails().get("EE_SUPERADMINPASSWORD");
		}
		else if(input.toLowerCase().equals("admin"))
		{
			input = MSGEventEngineURLs.getEnvDetails().get("EE_ADMINUSER") + "|" + MSGEventEngineURLs.getEnvDetails().get("EE_ADMINPASSWORD");
		}

		String sUsername = input.split("\\|")[0].trim();
		String sPassword = input.split("\\|")[1].trim();

		//=========>> Variable that will Store the Fail to Login Reason  
		String sFailReason = "";

		//=========>> Check ID/Password Field is Editable and Button Is Click-able
		if(!selenium.EnterText( email, sUsername))
		{
			sFailReason =  "Failed To Enter User ID .. ";
			return sFailReason;
		}
		
		if(!selenium.EnterText( password, sPassword))
		{
			sFailReason =  "Failed To Enter Password .. ";
			return sFailReason;

		}
		
		if(!selenium.ClickButton(btnLogin)) 
		{
			sFailReason = "Failed To CLick Login Button .. ";
			return sFailReason;

		}

		//=========>> If All Above PAssed Check we are in Event Engine HOme Page 
		EE_B_HomePage homePage = new EE_B_HomePage(driver);
		boolean isloginSuccess=homePage.ValidateWeInEventEngineDashBoard();
		if(isloginSuccess==true)
		{
			return homePage;
		}
		else
		{
			sFailReason = "Failed To Login with User ID  :-  " + sUsername + " And Password :- " + sPassword ;
			return sFailReason;
		}
	}
}
