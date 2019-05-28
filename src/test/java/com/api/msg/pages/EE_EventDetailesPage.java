package com.api.msg.pages;

import java.net.URI;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import SupportSeleniumUIActions.WebUI;
import SupportUtility.printconsole;
import SupportWebDriver.Driver;

public class EE_EventDetailesPage
{
	//static WebDriver driver = Driver.getCurrentDriver();
	WebDriver driver;
	WebUI selenium;

	
	
	//###### Locators :-   *****
		@FindBy(xpath="//a[@href='/logout']")
			public WebElement weLogoutLinkIcon;
		@FindBy(xpath="//div[@class='card-header card-header-icon']/i[contains(text(), 'event')]")
			public WebElement weEventCardHeaderIcon;
		@FindBy(xpath="//h2[contains(text(), 'Events')]")
			public WebElement weEventHeadingLabel;
		@FindBy(xpath="(//label[@class='form-group'][2])")
			public WebElement wePublishUnpublishSwitch;	
		@FindBy(xpath="//button[text() = 'Update']")
			public WebElement weupdate_event_button;
		@FindBy(xpath="//input[contains(@value,'ticketmaster.com')]")
			public WebElement weevent_ticket_master_urlTextField;
		@FindBy(xpath="//*[@id='eventArrivalDescription']")
			public WebElement weArrivingAtTheEventDescription;
		
	//#### Locators :- Artist Selection ( //h4[text() = 'Artists:']/.. )
		@FindBy(xpath="(//h4[text() = 'Artists:']/..//input)[1]")
			public WebElement weEventsAvailableArtistsSearchField;
		@FindBy(xpath="//h4[text() = 'Artists:']/..//div[@class='availableItemsContainer']//ul")
			public WebElement weEventsAvailableArtistsListBox;
		@FindBy(xpath="//h4[text() = 'Artists:']/..//div[@class='selectedItemsContainer']//ul")
			public WebElement weEventsSelectedArtistsListBox;
		

		//#### Locators :- Sponsor Selection ( //h4[text() = 'Artists:']/.. )
			@FindBy(xpath="(//h4[text() = 'Sponsors:']/..//input)[1]")
				public WebElement weEventsAvailableSponsorSearchField;
			@FindBy(xpath="//h4[text() = 'Sponsors:']/..//div[@class='availableItemsContainer']//ul")
				public WebElement weEventsAvailableSponsorListBox;
			@FindBy(xpath="//h4[text() = 'Sponsors:']/..//div[@class='selectedItemsContainer']//ul")
				public WebElement weEventsSelectedSponsorListBox;
		
		
	//#### Locators :- Category Selection ( //h4[text() = 'Category:']/.. )
		@FindBy(xpath="//h4[text() = 'Categories:']/..")
			public WebElement weCategoryContainer;
			public String XpathCategoryDropDownBox_1 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][0]']";
			public String XpathCategoryDropDownBox_2 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][1]']";
			public String XpathCategoryDropDownBox_3 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][2]']";
		@FindBy(xpath="//h4[text() = 'Categories:']/..//select[@name='event[categories][2]']/..//a")
			public WebElement XpathRemoveCategory_3;
			public String XpathCategoryDropDownBox_4 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][3]']";
		@FindBy(xpath="//h4[text() = 'Categories:']/..//button[text() = 'Add more']")
			public WebElement weAddMoreCataButton;
		

	//##### Common Variables   *****
			public String lastSavedEventId;  	// This variable will store the Last Saved Event ID for API CALL
			public EE_CommonSideBar sideNavBar;	// This Object will Hold the Common Side Navigation Bar that will help to navigate	
		

	
	/** Default Constructor  */
	public EE_EventDetailesPage(WebDriver driver) 
	{
		//==>> Loading All Web-Element Object Using Page Factory
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	    
		//==>> Initialize SELENIUM wrapper that will help to perform all UI Related Action   
		selenium = new WebUI(driver,null,null);

		//=== All Logged In Page need this Side bar to Navigate 
		sideNavBar = new EE_CommonSideBar(driver);
	}
	
	
	public boolean AddCategory(String sCategoryName)
	{
		//************ All Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
		printconsole.print(".............................................................");
		//************ ************************** *******************************************
	
		//###########  Step >>> 1st : Scroll To The Category Section ####################
		printconsole.print("Step >>> 1st : Scroll To The Category Section  <<<<");
		if(!selenium.scrollToElement(weCategoryContainer))
		{
			printconsole.printfailure(thisClassName, thisMethodName, 
					 				 "Faild Scroll to Category Element weCategoryContainer", 
				 					 "Exp  N/A", 
					 				 "Actual   N/A");
			return false;
		}	
		
		
		String cate1 = sCategoryName.split("\\|")[1].trim();
		selenium.ClickButton(weAddMoreCataButton);
		selenium.hardWaitFor(2);
		WebElement ele = driver.findElement(By.xpath(XpathCategoryDropDownBox_2));
		selenium.highlightElement(ele, "PASS");
		selenium.SelectDropDown(XpathCategoryDropDownBox_2,cate1);
		
		String cate2 = sCategoryName.split("\\|")[2].trim();
		selenium.ClickButton(weAddMoreCataButton);
		selenium.hardWaitFor(2);
		WebElement elex = driver.findElement(By.xpath(XpathCategoryDropDownBox_3));
		selenium.highlightElement(elex, "PASS");

		selenium.SelectDropDown(XpathCategoryDropDownBox_3,cate2);

		return true;
		
		/*//String category[] = sCategoryName.split("\\|");
		if(category.length>0)
		{
			for(int i=0;i<category.length;i++)
			{
				selenium.ClickButton(weAddMoreCataButton);
				selenium.SelectDropDown(XpathCategoryDropDownBox_3,sCategoryName);
			}
		}
			
		
		
		//###########  Step >>> 2nd : Clicking Add More Button ####################
		printconsole.print("Step >>> 2nd : Clicking Add More Button <<<<");
		if(!selenium.ClickButton(weAddMoreCataButton))
		{
			printconsole.printfailure(thisClassName, thisMethodName, 
										"Faild to Click Add More Button", 
										"Exp  N/A", 
										"Actual N/A");
			return false;
		}
		
		//###########  Step >>> 3rd : Selecting A New category  ####################
		printconsole.print("Step >>> 3rd : Add a New category <<<<");
		if(!selenium.SelectDropDown(XpathCategoryDropDownBox_3,sCategoryName))
		{
			printconsole.printfailure(thisClassName, thisMethodName, 
										"Faild to Add A  New Category"+ sCategoryName , 
										"Exp  N/A", 
										"Actual N/A");				
			return false;
		}
		
		return false;*/
	}
	
	
	public boolean AddRemoveCategory(String sCategoryName)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			printconsole.print(".............................................................");
		//************ ************************** *******************************************

		
		//************ Default Data For this Functions  ************************************
			EE_Z_API eeapi = new EE_Z_API();
			String CategoryArray = "category";
			String ExpectedResponseValue="";
			String ActualResponseValue=""; 
			
			sCategoryName = "Family";
			String sFirstCtegoryToChange = "Family";
			String sSecondCtegoryChaseCardholderAccess = "Chase Cardholder Access";
			String sThirdCtegoryAddNew = "Music";
			String sCategorySwapHelper = "Sports";
		//************ ************************** *******************************************


		//###########  Step >>> 1st : We Will Change Default Category to Family - This will Prove There is At least one Category Present ####################
			printconsole.print("Step >>> 1st : We Will Change Default Category to Family - This will Prove There is At least one Category Present <<<<");
			if(!selenium.scrollToElement(weCategoryContainer))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
						 				 "Faild Scroll to Category Element weCategoryContainer", 
					 					 "Exp  N/A", 
						 				 "Actual   N/A");
				return false;
			}
			if(!selenium.SelectDropDown(XpathCategoryDropDownBox_1,sFirstCtegoryToChange))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
		 				 				"Can Not Select [" + sCategoryName + "] From CategoryDropDownBox_1", 
		 				 				"Exp  N/A", 
		 				 				"Actual N/A");
				return false;
			}
	
		//###########  Step >>> 2nd : Look For Default Chase Cardholder Access 	Category is There ####################
			printconsole.print("Step >>> 2nd : Look For Default Chase Cardholder Access 	Category is There <<<<");
			if(!selenium.GetDropDownFirstSelectedOption(XpathCategoryDropDownBox_2).equals(sSecondCtegoryChaseCardholderAccess))
			{
				
				printconsole.printfailure(thisClassName, thisMethodName, 
			 								"Faild to Get [" + sSecondCtegoryChaseCardholderAccess + "] From CategoryDropDownBox_1", 
			 								"Exp  N/A", 
			 								"Actual N/A");
				return false;
			}
	
		//###########  Step >>> 3rd : Clicking Add More Button ####################
			printconsole.print("Step >>> 3rd : Clicking Add More Button <<<<");
			if(!selenium.ClickButton(weAddMoreCataButton))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Click Add More Button", 
											"Exp  N/A", 
											"Actual N/A");
				return false;
			}
			
		//###########  Step >>> 4th : Make Sure Family Option is no longer exist in New Category DropDown. As we already added it in  1st step ####################
			printconsole.print("Step >>> 4th : Make Sure Family Option is no longer exist in New Category DropDown. As we already added it in  1st step <<<<");
			//sCategoryName = "Sports"; Debug with negative Value
			if(selenium.GetAllDropDownOptionAsList(XpathCategoryDropDownBox_3).contains(sFirstCtegoryToChange))
			{
				
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Category ["+ sCategoryName + "] Already Selected Once it Can't Be Repeat" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
	
		//###########  Step >>> 5th : Add a New category  ####################
			printconsole.print("Step >>> 5th : Add a New category <<<<");
			if(!selenium.SelectDropDown(XpathCategoryDropDownBox_3,sThirdCtegoryAddNew))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Add A  New Category"+ sCategoryName , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}

		//###########  Step >>> 6th : Swap MUSIC with SPORTS category so MUSIC become available for First Category Drop Box  ####################
			printconsole.print("Step >>> 6th : Swap MUSIC with SPORTS category so MUSIC become available for First Category Drop Box <<<<");
			//==>>Swapping from CategoryDropDownBox_3 -> MUSIC Category to SPORTS Category [ MUSIC to SPORTS ]
			if(!selenium.SelectDropDown(XpathCategoryDropDownBox_3,sCategorySwapHelper))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Change Category from Music to  [" + "Sports" + "] From CategoryDropDownBox_3" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
			
			//==>>Swapping from CategoryDropDownBox_1 -> Family Category to MUSIC Category [ Family to Music ]
			if(!selenium.SelectDropDown(XpathCategoryDropDownBox_1,sThirdCtegoryAddNew)) 
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Change Category from Family to  [" + "Music" + "] From CategoryDropDownBox_1" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
			
			//==>>Swapping from CategoryDropDownBox_3 -> Sports Category to Family Category [ Sports to family ]
			if(!selenium.SelectDropDown(XpathCategoryDropDownBox_3,sFirstCtegoryToChange))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Change Category from Sports to  [" + "Family" + "] From CategoryDropDownBox_3" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
			
			//==>>  Save the Event & Get the Event ID 
			if(!Save_And_StoreEventID())
			{
				if(!Save_And_StoreEventID())
				{
					printconsole.printfailure(thisClassName, thisMethodName, 
											 "Couldn't Save the Event After Swapping Category from Box3.Music to Sports | Box1.Family to Music | Box3.Sports To Family", 
											 "Exp  N/A", 
											 "Actual   N/A");
					return false;
				}
				return false;
			}
			
			//==>> Call API And validate API Response for "category":[{"name": "Music"},{"name": "Chase Cardholder Access"}]
			ExpectedResponseValue = "[{name="+sThirdCtegoryAddNew+"}, {name="+sSecondCtegoryChaseCardholderAccess+"}, {name="+sFirstCtegoryToChange+"}]";
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,CategoryArray,ExpectedResponseValue);
			if(!ActualResponseValue.equals(ExpectedResponseValue))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Event/Event ID API Response doesn't Match with", 
											CategoryArray + ":" + ExpectedResponseValue, 
											ActualResponseValue);
				return false;
			}


		//###########  Step >>> 7th : Remove the Last Category From the The List & Validate API Response  ####################
			printconsole.print("Step >>> 7th : Remove the Last Category From the The List & Validate API Response <<<<<<<<<<<<<<<<<<<<<");			
			if(!selenium.scrollToElement(weCategoryContainer))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
						 				 "Faild Scroll to Category Element", 
					 					 "Exp  N/A", 
						 				 "Actual   N/A");
				return false;
			}
			if(!selenium.ClickLink(XpathRemoveCategory_3))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
		 				 				"Faild Scroll to Remove Category" + sFirstCtegoryToChange + " From Category List", 
	 				 					"Exp  N/A", 
		 				 				"Actual   N/A");
				return false;
			}

			//==>>  Save the event & Get the Event ID 
			if(!Save_And_StoreEventID())
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Save the Event After Removing Category" + sFirstCtegoryToChange, 
										 "Exp  N/A", 
										 "Actual   N/A");
				return false;
			}
			//==>> Call API And validate API REsponse now Now don't have that Removed "category":[{"name": "Music"},{"name": "Chase Cardholder Access"}]
			ExpectedResponseValue = "[{name="+sThirdCtegoryAddNew+"}, {name="+sSecondCtegoryChaseCardholderAccess+"}]";
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,CategoryArray,ExpectedResponseValue);
			if(!ActualResponseValue.equals(ExpectedResponseValue))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										"Event/Event ID API Response doesn't Match with", 
										CategoryArray + ":" + ExpectedResponseValue, 
										ActualResponseValue);
				return false;
			}
			
		//#### If there is no false this function returns true	
		return true;	
	}
	
	
	public boolean Save_And_StoreEventID()
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		    String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		    //printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *******************************************

		String sEventIDFromTMURL;
		//==Lets Save the Tiket Master URL From Current Edit Event Screen 
		String tmUrl = selenium.GetAttributeText(weevent_ticket_master_urlTextField,"value");
		if(!tmUrl.equals("")) 
		{
			// == > Parse the URL text and Store only the Event ID x`
			URI uri;
			try 
			{
				uri = new URI(tmUrl);
				String path = uri.getPath();
				sEventIDFromTMURL = path.substring(path.lastIndexOf('/') + 1);
				lastSavedEventId = sEventIDFromTMURL;
			} 
			catch (Exception e) 
			{
				System.out.println("ERROR :>> Failed To Get Event ID From Ticket Master URL ");
				return false;
				//e.printStackTrace();
			}
		}
		
		//==>> Scroll TO  AvailableArtistsSearchField
		selenium.scrollToElement(weupdate_event_button);
		
		//== >> Click The Save Update Button 
		if(selenium.ClickButton(weupdate_event_button)) 
		{
			//sFailReason = "Failed To CLick Login Button .. ";
			return true;
		}
		
		
		return false;
		
	}
}
