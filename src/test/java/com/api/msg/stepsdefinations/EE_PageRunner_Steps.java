package com.api.msg.stepsdefinations;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.api.msg.pages.EE_A_LoginPage;
import com.api.msg.pages.EE_B_HomePage;
import com.api.msg.pages.EE_CommonSideBar;
import com.api.msg.pages.EE_EventDetailesPage;
import com.api.msg.pages.EE_EventsPage;

import SupportReporting.Reporter;
import SupportWebDriver.Driver;

public class EE_PageRunner_Steps {
	
	static WebDriver driver = Driver.getCurrentDriver();

	 @Steps
	 EE_SerinitySteps eeadmin;
	
	
	@Given("^I Started Testing Scenario \"([^\"]*)\"$")
	public void i_start_Scenario(String ScenarioName) throws InterruptedException
	{
		System.out.println("Inside Steps Defination: " + ScenarioName);
		Thread.sleep(5000);
	}
	
	
	
	@Given("^I login to \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_login_to(String pagename, String user) throws Throwable 
	{
		
		eeadmin.Login();
		
		
		/*//==>Initialize EE Login Page & Land On EE Login Page 
			EE_A_LoginPage eeLoginPage = new EE_A_LoginPage(driver);
			boolean am_i_in_LoginPage = eeLoginPage.goToLoginPage();
			Reporter.Reportfail("Am I In Login Page", true, am_i_in_LoginPage);
		
		//==>Enter ID PAssword and Check we are In HOme Page   
			Object oLoginReturnPage=eeLoginPage.doLogin(user);
			boolean am_i_in_HomePage = oLoginReturnPage instanceof EE_B_HomePage;
			Reporter.Reportfail("Am I Logged in With User ID & Password",true,am_i_in_HomePage);*/
	}
	
	
	
	@Given("^I Navigate to EventListPage$")
	public void i_Navigate_to_EventListPage() throws Throwable 
	{
		eeadmin.NavigateTo();

		/*EE_CommonSideBar eeSideBar = new EE_CommonSideBar(driver);
		EE_EventsPage eventPage = (EE_EventsPage) eeSideBar.NavigateTo("EventPage");
		boolean am_i_in_EventPage = eventPage instanceof EE_EventsPage;
		Reporter.Reportfail("Am I in Event List Page",true,am_i_in_EventPage);*/
	}
	
	
	@Given("^I Validate EventListPage Has Below Web Elements$")
	public void i_Validate_EventListPage_Has_Below_Web_Elements(DataTable table) throws Throwable 
	{
		
		List<List<String>> data = table.raw();
		
		//System.out.println(data.size());
		//System.out.println(data.get(0).get(0));
		//System.out.println(data.get(1).get(0));

		EE_EventsPage eventPage = new EE_EventsPage(driver);

		String categories = null;
		for(int i=0;i<data.size();i++)
		{
			categories = categories + "|" + data.get(i).get(0);
			System.out.println("Checking WebElement " + data.get(i).get(0) );
			boolean is_this_WebElement_Exist_In_page = eventPage.Validate_Element_Present(data.get(i).get(0));;
			Reporter.Reportfail("is_this_WebElement ["+data.get(i).get(0)+"] Exist_Inpage",true,is_this_WebElement_Exist_In_page);
		}

		/*//===> Variables That will Store Methos Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventDetailesPage eventEditPage = new EE_EventDetailesPage();
			PageFactory.initElements(driver, eventEditPage);
			
			if(eventEditPage.AddCategory(categories)==true) // Generic Add Page Function here
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = " Couldn't Add Remove Category - > " + categories ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}*/
		
		
	}
	
	@Given("^I Search an Event with Event Name \"([^\"]*)\"$")
	public void i_Search_an_Event_with_Event_Name(String eventname) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsPage eventPage = new EE_EventsPage(driver);
			PageFactory.initElements(driver, eventPage);
			
			if(eventPage.SearchEventByEventName(eventname)==true)
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = "Couldn't Find Event for" + eventname ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		assertEquals(sFailReason,bReturnPass,true);

		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		/*if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}*/		
		
	}
	
	@Given("^I Search \"([^\"]*)\" Event & Click The First Event Returns from EventTable$")
	public void i_Search_Event_Click_The_First_Event_Returns_from_EventTable(String eventname) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		

		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsPage eventPage = new EE_EventsPage(driver);
			PageFactory.initElements(driver, eventPage);
			
			if(eventPage.SearchAndClickFirstEvent(eventname)==true)
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = "Couldn't CLick Event for" + eventname ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		assertEquals(sFailReason,bReturnPass,true);

		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		/*if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}	*/	
		
	
		
		
	}

	@Given("^I Add Below Categories To This Event$")
	public void i_Add_Below_Categories_To_This_Event(DataTable table) throws Throwable 
	{
		List<List<String>> data = table.raw();
		String categories = null;
		for(int i=0;i<data.size();i++)
		{
			categories = categories + "|" + data.get(i).get(0);
		}

		EE_EventDetailesPage eventEditPage = new EE_EventDetailesPage(driver);
		boolean am_I_was_Success_On_Add_allCategory = eventEditPage.AddCategory(categories);
		Reporter.Reportfail("Am_I_was_Success_On_Add_allCategory",true,am_I_was_Success_On_Add_allCategory);
			
	}
	
	
}
