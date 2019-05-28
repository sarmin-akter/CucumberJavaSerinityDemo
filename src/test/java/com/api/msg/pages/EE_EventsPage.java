package com.api.msg.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SupportSeleniumUIActions.WebUI;

public class EE_EventsPage
{
	//static WebDriver driver = Driver.getCurrentDriver();
	WebDriver driver;
	WebUI selenium;

	
	
	
	@FindBy(xpath="//a[@href='/logout']")
		public WebElement weLogoutLinkIcon;
	
	@FindBy(xpath="//div[@class='card-header card-header-icon']/i[contains(text(), 'event')]")
		public WebElement weEventCardHeaderIcon;
	
	@FindBy(xpath="//h2[contains(text(), 'Events')]")
		public WebElement weEventHeadingLabel;
	
	@FindBy(xpath="//input[@placeholder='Search..']")
		public WebElement weSearchField;	
	
	@FindBy(xpath="//button[@type='submit']")
		public WebElement weSearchButton;
	
	@FindBy(xpath=".//*[@id='event-list-table']")
		public WebElement weEventsTable;
	
	///===== We will implement Passing List to Table Function later 
	@FindBy(xpath="//div[@class='react-table-events']//div[@class='fixedDataTableLayout_rowsContainer']//div[@class='fixedDataTableRowLayout_rowWrapper']")
		public List<WebElement> listEventsTableAllRows;
		//public String sTableRowsXpath ="//div[@class='react-table-events']//div[@class='fixedDataTableLayout_rowsContainer']//div[@class='fixedDataTableRowLayout_rowWrapper']";
		//public String sTableColumnsXpath ="//div[@class='fixedDataTableCellLayout_main public_fixedDataTableCell_main']";
	
		public String sTableRowsXpath ="//*[@id='event-list-table']//tr";
		public String sTableColumnsXpath ="//td";

	
	//*** Common Side Bar Object Variable For all Page 
	public EE_CommonSideBar sideNavBar;
	
	/** Default Constructor  */
	public EE_EventsPage(WebDriver driver) 
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
	
	
	public boolean ValidateWeInEventPage()  // THis Should go as Common Ensure Page ready Function 
	{
		if(selenium.ValidateElementExist(weLogoutLinkIcon)==true)
		{
			return true;
		}
		return false;
	}
	
	public boolean Validate_Element_Present(String sEleName)
	{

		//==>> Lets Check Which element Need to Validate for Existence 
		WebElement weElementToValidate = null;
		if(sEleName.equals("EventCardHeaderIcon"))
		{
			weElementToValidate = weEventCardHeaderIcon;
		}
		else if(sEleName.equals("EventHeadingLabel"))
		{
			weElementToValidate = weEventHeadingLabel;
		}
		else if(sEleName.equals("EventSearchField"))
		{
			weElementToValidate = weSearchField;
		}
		else if(sEleName.equals("SearchButton"))
		{
			weElementToValidate = weSearchButton;
		}
		else if(sEleName.equals("EventListTable"))
		{
			weElementToValidate = weEventsTable;
		}
		else
		{
			System.out.println("WebElement : " + sEleName +" Not Exist in Event Page Repository ...");
			return false;
		}

		
		

		if(selenium.ValidateElementExist(weElementToValidate)==true)
		{
			return true;
		}
		return false;
	}
	
	public boolean SearchEventByEventName(String sTextToValidate)
	{
		
		if(!selenium.EnterText(weSearchField,"Billy Joel - In Concert")) return false;
		if(!selenium.ClickButton(weSearchButton)) return false;
		if(!selenium.ValidateTableCellText(sTableRowsXpath,sTableColumnsXpath,sTextToValidate))return false;
		
		return true;
	}

	public boolean SearchAndClickFirstEvent(String sTextToValidate)
	{
		
		if(!selenium.EnterText(weSearchField,"Billy Joel - In Concert")) return false;
		if(!selenium.ClickButton(weSearchButton)) return false;
		if(!selenium.ClickTableCellText(sTableRowsXpath,sTableColumnsXpath,sTextToValidate))return false;
		
		return true;
	}
	
	
	
	
	
}
