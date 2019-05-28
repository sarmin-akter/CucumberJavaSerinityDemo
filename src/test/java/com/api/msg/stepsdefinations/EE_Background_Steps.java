package com.api.msg.stepsdefinations;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.runtime.ScenarioImpl;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.api.msg.pages.EE_A_LoginPage;
import com.api.msg.pages.EE_B_HomePage;
import com.api.msg.pages.MSGEventEngineURLs;

import SupportReporting.Reporter;
import SupportWebDriver.Driver;

//import com.api.msg.pages.EE_A_LoginPage;
//import com.api.msg.pages.EE_B_HomePage;
//import com.api.msg.pages.MSGEventEngineURLs;


public class EE_Background_Steps 
{
	
	static WebDriver driver = Driver.getCurrentDriver();
	String website;
	

	/*@Given("^I login to \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_login_to(String pagename, String user) throws Throwable 
	{
		
		//==>Initialize EE Login Page & Land On EE Login Page 
			EE_A_LoginPage eeLoginPage = new EE_A_LoginPage(driver);
			boolean am_i_in_LoginPage = eeLoginPage.goToLoginPage();
			Reporter.Reportfail("Am I In Login Page", true, am_i_in_LoginPage);
		
		//==>Enter ID PAssword and Check we are In HOme Page   
			Object oLoginReturnPage=eeLoginPage.doLogin(user);
			boolean am_i_in_HomePage = oLoginReturnPage instanceof EE_B_HomePage;
			Reporter.Reportfail("Am I Logged in With User ID & Password",true,am_i_in_HomePage);
	}*/

}
