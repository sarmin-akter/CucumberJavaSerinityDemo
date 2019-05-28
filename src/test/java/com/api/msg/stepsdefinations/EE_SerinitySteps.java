package com.api.msg.stepsdefinations;

import org.openqa.selenium.WebDriver;

import com.api.msg.pages.EE_A_LoginPage;
import com.api.msg.pages.EE_B_HomePage;
import com.api.msg.pages.EE_CommonSideBar;
import com.api.msg.pages.EE_EventsPage;

import SupportReporting.Reporter;
import SupportWebDriver.Driver;
import net.thucydides.core.annotations.Step;

public class EE_SerinitySteps 
{
	static WebDriver driver = Driver.getCurrentDriver();


	EE_A_LoginPage eeLoginPage;
	EE_CommonSideBar eeSideBar;
	EE_EventsPage eventPage;

	
	@Step
	public void Login() 
	{
		eeLoginPage = new EE_A_LoginPage(driver);
		eeLoginPage.goToLoginPage();
		eeLoginPage.doLogin("SuperAdmin");
	}


	@Step
	public void NavigateTo() 
	{
		eeSideBar = new EE_CommonSideBar(driver);
		eventPage = (EE_EventsPage) eeSideBar.NavigateTo("EventPage");
		boolean am_i_in_EventPage = eventPage instanceof EE_EventsPage;
		Reporter.Reportfail("Am I in Event List Page",true,am_i_in_EventPage);
	}
}
