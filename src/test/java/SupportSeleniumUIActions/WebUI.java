package SupportSeleniumUIActions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import SupportUtility.printconsole;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;


public class WebUI 
{
	
	
		public String currentTestSuite;  // Name Of the Excel File which Is Running 
	
	//==>> Driver That Perform All Actions on UI - This will Be Loaded In Constructor with driver coming From Test 
		public WebDriver driver;
		public ExtentTest ExtendTestReport;
		public ExtentTest ScenarioReport;
		//public ReportTest report = null;
		public ExtentTest test;


	//==>> Reporting Variable
		public static String PassFailFailDescription;
		

		
	public WebUI(WebDriver driver, ExtentTest test,ExtentTest ScenarioReport)
	{
		//super(driver,test);
		this.driver=driver;
		this.ExtendTestReport=test;
		this.ScenarioReport=ScenarioReport;
	}
		
	public void NavigateToPage(String Url)
	{

		driver.get(Url);
		
	}
	
	public boolean ValidateElementExist(WebElement el)
	{
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
		WebElement TargetObject=el;
		boolean s=false;
		
		try
		{
			if(!waitUntilClickable(TargetObject)) return false; // wait a Little bit for Object to Load
			//TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			s = TargetObject.isDisplayed();
		}
		catch(Exception e)
		{
			s=false;
		}

		if(s==false)
		{
			WebUI.PassFailFailDescription = thisMethodName + " -> Element Not Found " + "<br>";
			Rtype= false;
		}
		else
		{
			WebUI.PassFailFailDescription = thisMethodName + " -> Element Found " + "<br>";
			highlightElement(TargetObject,"pass");
			Rtype= true;
		}
	    return Rtype;		
	}

	public boolean EnterText(WebElement el, String Input)
	{
		
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
	    		boolean Rtype = false;
	    	//****** Report: This Step That is Executing ************************************   
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
	    	//********************************************************************************
	    	
	    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
				//Input = fInterpretInput(Input);
				//Input = fInterpretExcelData(Input);
				
	    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			WebElement TargetObject = el;
	    			TargetObject.clear();
	    			hardWaitFor(1000);
	    			TargetObject.sendKeys(Input);;
	    			Rtype= true;
	    			PassFailFailDescription = thisMethodName + "->" + el + "->"+ Input + "-> Passed" + "<br>";
	    		}
	    		catch(Exception e){
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    			Rtype= false;
	
	    			PassFailFailDescription = thisMethodName + "->" + el + "->"+ Input + "-> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; // Failed Report in Pageready Object Ready Function
			//}
			return Rtype;
	   }
	
	public boolean Verify_Text(WebElement el, String expText) 
	{

    		boolean Rtype = false;
    	
	    	//****** Report: This Step That is Executing ************************************   
	    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName +"<<<<<");
	    	//********************************************************************************
	    	
	    	//if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Verifying Text..");
			
			
			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			String ActualText = "";

			WebElement TargetObject=null;
			//boolean s=false;
			
			try
			{
				//====== TO DO : Temporary Solution  if calender object is open close it first 
					//if(Page.equals("EditEvent_Popup"))
					//{
						//driver.findElement(By.xpath(GetObjectLocatorXPathCssEtcFromRepo(Page))).click();
					//}
				
				//WebDriverWait wait = new WebDriverWait(driver, 10);
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectLocatorXpathCssETC)));
    				if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load

	
				TargetObject = el;
				ActualText = TargetObject.getText();
				
				if(!expText.equals(""))
				{
					if(expText.trim().equals(ActualText.trim()))
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "<b>Expected Text :</b> " + Input + "<br>" + "<b>Actual Text :</b> " + ActualText);
						//PassFailFailDescription = thisMethodName +"<br>"+ "<b>Expected Text :</b> " + Input + "<br>" + "<b>Actual Text :</b> " + ActualText + "<br>";
						highlightElement(TargetObject,"pass");
						Rtype = true;
					}
					else //If Text Not Matching 
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "<b>Expected Text:</b> " + Input + "<br>" + "<b>Actual Text :</b> <mark>" + ActualText + "</mark>");
						PassFailFailDescription = thisMethodName +"<br>"+ "<b>Expected Text:</b> " + expText + "<br>" + "<b>Actual Text :</b> <mark>" + ActualText + "</mark>" + "<br>";
						highlightElement(TargetObject,"fail");
						Rtype = false;
					}
				}
				else //IF Input or Expected Text Is Empty 
				{
					//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "Expected Input Text From Test Case Excel File is Empty ");
					PassFailFailDescription = thisMethodName + "->" + "Expected Input Text From Test Case Excel File is Empty "+ "<br>";
					Rtype = false;
				}
			}
			catch(Exception e)
			{
				//System.out.println(e.getMessage());
				Rtype = false;
				//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, "Can Not Verify Text Reason : <br>" + e.getMessage());
				PassFailFailDescription = thisMethodName + "->" + "Can Not Verify Text Reason : <br>"+  e.getMessage() + "<br>";
			}
		//}
		//else //If Page is not Ready 
		//{
			//Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		//}

			return Rtype;		
	}
	
	
	
	public boolean ClickButton(WebElement el)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
		
		
		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName );
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try{
    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = el;
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    			PassFailFailDescription = thisMethodName + "->" + el + "-> Passed" + "<br>";
    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
    		}
    		catch(Exception e){
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "->" + el + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
		//}
		//else
		//{
			//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function.
		//}
		return Rtype;
    }
	
	public boolean ClickLink(WebElement el)
    {
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
	    	//****** Report: This Step That is Executing ************************************   
	    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName );
	    	//********************************************************************************
	    	
	    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load
	    			WebElement TargetObject = el;
	    			TargetObject.click();
	    			hardWait();
	    			Rtype= true;
	    			PassFailFailDescription = thisMethodName + "->" + el + "-> Passed" + "<br>";
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
	    		}
	    		catch(Exception e)
	    		{
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
		    		Rtype= false;
	    			PassFailFailDescription = thisMethodName + "->" + el + "-> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function.
			//}
			return Rtype;
    }
	
	public boolean ValidateTableCellText(String sAllRowsXpath, String sAllColumnXPath,String sExpectedText)
	{
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
		boolean Rtype = false;
    		try
    		{	    			
    			//String allRowXpath = "//div[@class='react-table-events']//div[@class='fixedDataTableLayout_rowsContainer']//div[@class='fixedDataTableRowLayout_rowWrapper']";
    			//String allColumnXpath = "//div[@class='fixedDataTableCellLayout_main public_fixedDataTableCell_main']";

    			String allRowXpath = sAllRowsXpath;
    			String allColumnXpath = sAllColumnXPath;

    	        List<WebElement> listAllRows = driver.findElements(By.xpath(allRowXpath));
    	        int iTotalRows = listAllRows.size();
    	        int iStartingRow = 1;

    	        TableRowLoop: for(int i=iStartingRow;i<iTotalRows;i++)
    	        {
    		  		//System.out.println(listAllRows.get(i).getText());
    		  		//highlightElement(listAllRows.get(i),"pass");
    		  		
	    	        List<WebElement> listCurrentRowColumns = driver.findElements(By.xpath(allRowXpath+"["+i+"]"+allColumnXpath));
	    	        int iTotalColumn = listCurrentRowColumns.size();

    		  		String EachCellData = "";
	    	        for(int x=0;x<=iTotalColumn;x++)
	    	        {
	    		  		//System.out.println(listCurrentRowColumns.get(x).getText());
	    		  		//highlightElement(listCurrentRowColumns.get(x),"pass");
	    		  		
	    		  		EachCellData += listCurrentRowColumns.get(x).getText();
    	                if(StringUtils.containsIgnoreCase(EachCellData,sExpectedText))
    	                {
    	                		Rtype= true;
    	                		highlightElementBackground(listCurrentRowColumns.get(x),"pass");
    	                		highlightElement(listCurrentRowColumns.get(x),"pass");
    	                		break TableRowLoop;
    	                }
	    	        }
	    	        System.out.println("");
    	        }
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    		}
    	
	    	return Rtype;
	 }
	
	public boolean ClickTableCellText(String sAllRowsXpath, String sAllColumnXPath,String sExpectedText)
	{
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		
		boolean Rtype = false;
    		try
    		{	    			
    			String allRowXpath = sAllRowsXpath;
    			String allColumnXpath = sAllColumnXPath;

    	        List<WebElement> listAllRows = driver.findElements(By.xpath(allRowXpath));
    	        int iTotalRows = listAllRows.size();
    	        int iStartingRow = 1;

    	        TableRowLoop: for(int i=iStartingRow;i<iTotalRows;i++)
    	        {
    		  		//System.out.println(listAllRows.get(i).getText());
    		  		//highlightElement(listAllRows.get(i),"pass");
    		  		
	    	        List<WebElement> listCurrentRowColumns = driver.findElements(By.xpath(allRowXpath+"["+i+"]"+allColumnXpath));
	    	        int iTotalColumn = listCurrentRowColumns.size();

    		  		String EachCellData = "";
	    	        for(int x=0;x<=iTotalColumn;x++)
	    	        {
	    		  		//System.out.println(listCurrentRowColumns.get(x).getText());
	    		  		highlightElement(listCurrentRowColumns.get(x),"pass");
	    		  		
	    		  		EachCellData += listCurrentRowColumns.get(x).getText();
    	                if(StringUtils.containsIgnoreCase(EachCellData,sExpectedText))
    	                {
    	                		Rtype= true;
    	                		highlightElementBackground(listCurrentRowColumns.get(x),"pass");
    	                		highlightElement(listCurrentRowColumns.get(x),"pass");
    	                		//listCurrentRowColumns.get(x).click();
    	                		listCurrentRowColumns.get(x).findElement(By.xpath("a")).click();
    	                		break TableRowLoop;
    	                }
	    	        }
	    	        System.out.println("");
    	        }
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    		}
    	
	    	return Rtype;
	 }
	
	public boolean SelectAnItemFrom_ListBox(WebElement listElement, String listNameOrNumbertoSelect)
	{
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
	  
		    	boolean Rtype = false;
		    boolean matChFoundinTable=false;
		    String errorFlag = "";
				
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
	    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	    		try
	    		{
	    			WebElement liste_element = listElement;
	    	        List<WebElement> list_collection = liste_element.findElements(By.xpath("li"));
	    	        printconsole.printdebuglog("Total No of Item Found in ListBox = "+list_collection.size());
	
	       listLoop:for(int i=0;i<list_collection.size();i++)
	    	        {
					printconsole.printdebuglog("ListBox Item ("+i+") :"+ list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(listNameOrNumbertoSelect.trim()) || list_collection.get(i).getText().trim().contains(listNameOrNumbertoSelect.trim()))
					{
						list_collection.get(i).click();
						hardWait();
						PassFailFailDescription = thisMethodName + "->" + "" + "-> Passed" + "<br>";
						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
						matChFoundinTable= false;
						errorFlag = listNameOrNumbertoSelect + "  Not Found In List ";
	                }
	    	        } 		
	    		}
	    		catch(Exception e)
	    		{
	    			PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    			System.out.println("Failed : " + PassFailFailDescription);
	    			Rtype= false;
	    		}
		    	
		    	if(!matChFoundinTable)
		    	{
		        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
					PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
		    	}
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			return Rtype;
	}

	public boolean Click_A_WebElement_FromListBox(WebElement listElement, String ElementType, String listNameOrNumbertoSelect)
	{
	
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		
	    	boolean Rtype = false;
	    boolean matChFoundinTable=false;
	    String errorFlag = "";
	
	    //****** Report: This Step That is Executing ************************************   
	    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
	    	//********************************************************************************
	    	
			//Input = fInterpretExcelData(Input);
	
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
	    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    	
	    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			//WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
	    			WebElement liste_element = listElement;
	    	        List<WebElement> list_collection = liste_element.findElements(By.xpath("li"));
	    	        //System.out.println("NUMBER OF Item Found in List  = "+list_collection.size());
	
	       listLoop:for(int i=0;i<list_collection.size();i++)
	    	        {
					//System.out.println(list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(listNameOrNumbertoSelect.trim()) || list_collection.get(i).getText().trim().contains(listNameOrNumbertoSelect.trim()))
					{
						//==>> There Could Be Different type of webelement inside a ListBox Like ( EditBox/ Button/ Link /span ETC)
						//==>> We Have to check which Item need to click and then Click it 
						if(list_collection.get(i).findElements(By.xpath(ElementType)).size() > 0) //Month FiletList in Suite Client Section
						{
							list_collection.get(i).findElement(By.xpath(ElementType)).click();
						}
						hardWait();
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Listbox  " + ObjectName +"->" + Input + "->Clicked Passed" );
						PassFailFailDescription = thisMethodName + "->" + "" + "-> Passed" + "<br>";
						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
						matChFoundinTable= false;
						errorFlag = listNameOrNumbertoSelect + "  Not Found In List ";
	                }
	    	        } 		
	    		}
	    		catch(Exception e)
	    		{
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "->Clicked Failed" );
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
	    			PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    			Rtype= false;
	    		}
			//}
			//else
			//{
				//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
				//Rtype= false;
			//}
	    	
	    	if(!matChFoundinTable)
	    	{
	        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
				PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    	}
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	
			return Rtype;
	    	
	    
	}

	public boolean SelectDropDown(String ObjectXpath, String Input)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			select.selectByVisibleText(Input);
    			Rtype= true;
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}   		
    		return Rtype;
    }
	
	public String GetDropDownFirstSelectedOption(String ObjectXpath)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

		
	    	
	    	String FirstSelectedOption = null;
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			FirstSelectedOption = select.getFirstSelectedOption().getText();
    			return FirstSelectedOption;
    		}
    		catch(Exception e)
    		{
    			PassFailFailDescription = e.getMessage() + "<br>";
    			System.out.println("Error in Selenium Method " + thisMethodName +" : "+ PassFailFailDescription);
    			return "";
    		}   		
    }

	public List<String> GetAllDropDownOptionAsList(String ObjectXpath)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

	    	
		List<String> OptionList = new ArrayList<String>();
	    	
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			List <WebElement> allOptions= select.getOptions();
    			//System.out.println("\nTotal NUmber Of Option Found Inside the Dropdown Box : " + allOptions.size() );
    			for(int i=0;i<allOptions.size();i++)
    			{
        			//System.out.println("Category Box Oprion " + i + " is : " + allOptions.get(i).getText() );
    				OptionList.add(allOptions.get(i).getText());
    			}
    			return OptionList;
    		}
    		catch(Exception e)
    		{
    			PassFailFailDescription = e.getMessage() + "<br>";
    			System.out.println("Error in Selenium Method " + thisMethodName +" : "+ PassFailFailDescription);
    			return null;
    		}   		
    }
	
	
	public boolean scrollToElement(WebElement el)
	{
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		boolean rtype = false;

		WebElement TargetObject=el;
		try
		{
			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			//TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TargetObject);
			hardWaitFor(1000);
			rtype=true;
		}
		catch(Exception e) 
		{
			PassFailFailDescription = thisMethodName + " Scrolling To Element " + TargetObject.toString() + "  -Failed" + "<br>";
			PassFailFailDescription = e.getMessage() + "<br>";
			System.out.println(PassFailFailDescription);
			rtype=false;
			//e.printStackTrace();
		}
		return rtype;
	}
	
	
	public String GetAttributeText(WebElement el, String AttributeName)
	{
		
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    		String Rtype = "";
	    	//****** Report: This Step That is Executing ************************************   
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
	    	//********************************************************************************
	    	
	    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
				//Input = fInterpretInput(Input);
				//Input = fInterpretExcelData(Input);
				
	    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			WebElement TargetObject = el;
	    			//TargetObject.clear();
	    			hardWaitFor(1000);
	    			//TargetObject.sendKeys(Input);
	    			Rtype= TargetObject.getAttribute(AttributeName);
	    			PassFailFailDescription = thisMethodName + " From -> " + el + " -> Passed" + "<br>";
	    		}
	    		catch(Exception e)
	    		{
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    			Rtype= "";
	    			PassFailFailDescription = thisMethodName + " From -> " + el + " -> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; // Failed Report in Pageready Object Ready Function
			//}
			return Rtype;
			
	   }
	
	
	
	
	
	
	
	
	public boolean OpenBrowserAndNavigate(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
	   	//****** Report: This Step That is Executing ************************************   
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" +ObjectName );
		//********************************************************************************

		boolean Rtype = false;

		/*if(Input.equals("") || Input.equals(null) || Input.equals(" ") )
		{
			if(!FBConstants.ENV.equals("")) //Looking For Default QA/UAT/PRD URL
			{
				Input = FBConstants.getEnvDetails().get("admin_url"); 
			}
			else
			{
				PassFailFailDescription =  thisMethodName + " Input Data Error : Chek ID/Password in Excel Data Sheet or FB-ENV Variable may Empty?? " + Input + "<br>";
				Rtype= false;
			}
		}*/

		driver.get(Input); //Loading the URL in Browser 

    	//if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
    	//{
    		PassFailFailDescription = thisMethodName +  " to" + Page  + " Successfully.." ;
    		//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "Successfully Landed On " + Page);
    		Rtype = true;
    	//}
    	//else
    	//{
    		//Rtype = false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function. 
    	//}
    	
 		return Rtype;
	}
	
    /********************************************* Click  **************************************************************************************************************/
	public boolean ClickTab(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" +ObjectName );
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
    		try
    		{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			if(!waitUntilClickable(ObjectLocatorXpathCssETC)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    			
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Passed" + "<br>";
    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
    		
		}
		else
		{
			Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function. 
		}
		return Rtype;
 	}
	public boolean ClickButton(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName );
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			if(!waitUntilClickable(ObjectLocatorXpathCssETC)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Passed" + "<br>";
    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
    		}
    		catch(Exception e){
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
		//}
		//else
		//{
			//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function.
		//}
		return Rtype;
    }
	public boolean ClickLink(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" +ObjectName );
    	//********************************************************************************
    
    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try
    		{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			if(!waitUntilClickable(ObjectLocatorXpathCssETC)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    			
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Passed" + "<br>";
    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
    		
		//}
		//else
		//{
			//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function. 
		//}
		return Rtype;
    }
	public boolean ClickLink(String Step, String Page, WebElement ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" +ObjectName );
    	//********************************************************************************
    
    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try
    		{
    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			if(!waitUntilClickable(ObjectName)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = ObjectName;
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Passed" + "<br>";
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
    		
		//}
		//else
		//{
			//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function. 
		//}
		return Rtype;
    }
	public boolean ClickandSwithTabValidatePageTitle(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" +ObjectName );
    	//********************************************************************************
    	
    		
	        //===Input Breaking 
				String testString = Input; 
				System.out.println("Excel Input Text Page title : " + testString);

			//== If No Input 
				if(testString.equals(""))
				{
	    			PassFailFailDescription = thisMethodName + "  We Need a Page Title To validate the Swiched Page " + "<br>";
	    			return false;
				}
				
		   //==>> Input should come in [ text : text \n ] format so we will check it and if its not in this format we will format it here 
				if(!testString.contains(":"))
				{
					testString = "Page Title : " + Input;
				}
				//String[] temp = testString.split("\n");
				//ArrayList<String> allInputList = new ArrayList<String>();

				//for(int i=0;i<temp.length;i++)
				//{
					//System.out.println("Input " + i + " to Verify => " +  temp[i].split(":")[1]);
					Input = testString.split(":")[1].trim();
				//}
				

    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
    		try
    		{
    		    // considering that there is only one tab opened in that point.
    				String oldTab = driver.getWindowHandle();
    			
    			//Get The Object that Need to Click and Click It 
    				
    				if(ObjectName.contains(""))
    				{
    					
    				}
    				
    				String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    				
    				
        			if(!waitUntilClickable(ObjectLocatorXpathCssETC)) return false; // wait a Little bit for Object to Load
    				WebElement TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    				highlightElementBackground(TargetObject,"pass");
    				TargetObject.click();
    				hardWait();
			
    			//After Clicking the Link We now Have Multiple Tab Lets Hold all tab window handel	
    				ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
    				newTab.remove(oldTab);
    				
    			// change focus to new tab & Do what you want here, you are in the new tab    				  
    				driver.switchTo().window(newTab.get(0));
    			  	String activePageTitle = driver.getTitle();
    				String expPageTitle = Input;
    				
    				if(activePageTitle.contains(expPageTitle))
    				{
    	    			Rtype= true;
    	    			PassFailFailDescription = thisMethodName + "<br>  Expected Page :  " + Input + "<br>  Loaded Properly in Seperate Tab " +  "<br>";
    				}
    				else
    				{
    	    			Rtype= false;
    	    			PassFailFailDescription = thisMethodName + "<br>  Something Wrong On Expected Page :  " + Input + "<br>  Not Loaded Properly in Seperate Tab <br> See the Screen Shot Above ^^" +  "<br>";
    	    			takeScreenShot();
    				}

    				driver.close(); //Closing the New Tab before Switching back 

    		    // change focus back to old tab & Do what we need to Do in OLD TAB
    		    	driver.switchTo().window(oldTab);
    		    	hardWaitFor(1000);
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "<br>  Something Wrong On Expected Page :  " + Input + "<br>  Not Loaded Properly in Seperate Tab <br> See the Screen Shot Above ^^" +  "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			takeScreenShot();
    		}
    		
		}
		else
		{
			Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function. 
		}
    	
    	
    	
		return Rtype;
    }
	public boolean ClickandSwithTabValidatePageTitle(String Step, String Page, WebElement ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" +ObjectName );
    	//********************************************************************************    	
    		
	        //===Input Breaking 
				String testString = Input; 
				System.out.println("Excel Input Text Page title : " + testString);

			//== If No Input 
				if(testString.equals(""))
				{
	    			PassFailFailDescription = thisMethodName + "  We Need a Page Title To validate the Swiched Page " + "<br>";
	    			return false;
				}
				
		   //==>> Input should come in [ text : text \n ] format so we will check it and if its not in this format we will format it here 
				if(!testString.contains(":"))
				{
					testString = "Page Title : " + Input;
				}
				//String[] temp = testString.split("\n");
				//ArrayList<String> allInputList = new ArrayList<String>();

				//for(int i=0;i<temp.length;i++)
				//{
					//System.out.println("Input " + i + " to Verify => " +  temp[i].split(":")[1]);
					Input = testString.split(":")[1].trim();
				//}
				

    	if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		{
    		try
    		{
    		    // considering that there is only one tab opened in that point.
    				String oldTab = driver.getWindowHandle();
    			
    			//Get The Object that Need to Click and Click It 
    				
    				//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    				
    				WebElement TargetObject = ObjectName;
    				highlightElementBackground(TargetObject,"pass");
    				TargetObject.click();
    				hardWait();
			
    			//After Clicking the Link We now Have Multiple Tab Lets Hold all tab window handel	
    				ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
    				newTab.remove(oldTab);
    				
    			// change focus to new tab & Do what you want here, you are in the new tab    				  
    				driver.switchTo().window(newTab.get(0));
    			  	String activePageTitle = driver.getTitle();
    				String expPageTitle = Input;
    				
    				if(activePageTitle.contains(expPageTitle))
    				{
    	    			Rtype= true;
    	    			PassFailFailDescription = thisMethodName + "<br>  Expected Page :  " + Input + "<br>  Loaded Properly in Seperate Tab " +  "<br>";
    				}
    				else
    				{
    	    			Rtype= false;
    	    			PassFailFailDescription = thisMethodName + "<br>  Something Wrong On Expected Page :  " + Input + "<br>  Not Loaded Properly in Seperate Tab <br> See the Screen Shot Above ^^" +  "<br>";
    	    			takeScreenShot();
    				}

    				driver.close(); //Closing the New Tab before Switching back 

    		    // change focus back to old tab & Do what we need to Do in OLD TAB
    		    	driver.switchTo().window(oldTab);
    		    	hardWaitFor(1000);
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "<br>  Something Wrong On Expected Page :  " + Input + "<br>  Not Loaded Properly in Seperate Tab <br> See the Screen Shot Above ^^" +  "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			takeScreenShot();
    		}
    		
		}
		else
		{
			Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function. 
		}
		return Rtype;
    }
	
    /********************************************* Set / Enter Text  **************************************************************************************************************/
	public boolean EnterText(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
    	//********************************************************************************
    	
    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
			//Input = fInterpretInput(Input);
			Input = fInterpretExcelData(Input);
			
    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try
    		{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    			TargetObject.clear();
    			hardWaitFor(1000);
    			TargetObject.sendKeys(Input);;
    			Rtype= true;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "->"+ Input + "-> Passed" + "<br>";
    		}
    		catch(Exception e){
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
    			Rtype= false;

    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
		//}
		//else
		//{
			//Rtype= false; // Failed Report in Pageready Object Ready Function
		//}
		return Rtype;
    }
	public boolean EnterText(String Step, String Page, WebElement ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
    	//********************************************************************************
    	
    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
			//Input = fInterpretInput(Input);
			Input = fInterpretExcelData(Input);
			
    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try
    		{
    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement TargetObject = ObjectName;
    			TargetObject.clear();
    			hardWaitFor(1000);
    			TargetObject.sendKeys(Input);;
    			Rtype= true;
    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "->"+ Input + "-> Passed" + "<br>";
    		}
    		catch(Exception e){
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
    			Rtype= false;

    			PassFailFailDescription = thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
		//}
		//else
		//{
			//Rtype= false; // Failed Report in Pageready Object Ready Function
		//}
		return Rtype;
    }
	
	
    /********************************************* Select List Box  **************************************************************************************************************/
    public boolean SelectDropDown(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From (" +ObjectName+")-> Value(" + Input + ")");
    	//*****************************************************************************************
 
		Input = fInterpretExcelData(Input);
    	
    	if(Input.contains("."))
		{
			Input = Input.replaceAll("\\.0*$", "");
		}
    	
    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Selecting Text");

    		try
    		{
        		String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			Select select = new Select(driver.findElement(By.xpath(ObjectLocatorXpathCssETC)));
    			select.selectByVisibleText(Input);
    			Rtype= true;
        		//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From (" +ObjectName+")-> Value(" + Input  + ")-> Passed");
    			PassFailFailDescription = thisMethodName + "-> From [" +ObjectName+"] -> Value(" + Input  + ")-> Passed" + "<br>"; 
    		}
    		catch(Exception e)
    		{
        		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From (" +ObjectName+")-> Value(" + Input  + ")-> Failed");
        		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, e.getMessage());
    			Rtype= false;
    			PassFailFailDescription = thisMethodName + "-> From [" +ObjectName+"] -> Value(" + Input  + ")-> FAILED" + "<br>"; 
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}   		
		}
		else
		{
			Rtype= false; //Faled Reported On Object ready & Page Ready Function 
		}
		return Rtype;
    }
    public boolean Select_List_from_ListBox(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;
        String errorFlag = "";

        //****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
		Input = fInterpretExcelData(Input);

    	
    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        List<WebElement> list_collection = liste_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li"));
    	        System.out.println("NUMBER OF Item Found in "+ ObjectName +" = "+list_collection.size());

       listLoop:for(int i=0;i<list_collection.size();i++)
    	        {
					System.out.println(list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(Input.trim()) || list_collection.get(i).getText().trim().contains(Input.trim()))
					{

						//**** TO DO : While Selecting a list can be Different Element we have to create a Generic code 
						if(driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/span")).size() > 0) //Month FiletList in Suite Client Section
						{
							liste_element.findElement(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/span")).click();
						}
						else if(driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/div[1]/div")).size() > 0) //Menu Item List - clicking Add(+) to card Button
						{
							liste_element.findElement(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/div[1]/div")).click();
						}
						else if(driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/div")).size() > 0) //Menu Item List - clicking Add(+) to card Button
						{
							liste_element.findElement(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/div")).click();
						}

						hardWait();
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Listbox  " + ObjectName +"->" + Input + "->Clicked Passed" );
						PassFailFailDescription = thisMethodName + "->" + ObjectName + "-> Passed" + "<br>";
						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
		            	matChFoundinTable= false;
		            	errorFlag = Input + "  Not Found In List ";
	                }
    	        } 		
    		}
    		catch(Exception e)
    		{
            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "->Clicked Failed" );
            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			PassFailFailDescription = thisMethodName + "->" + ObjectName +" Item->" + Input + "->Clicked Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			Rtype= false;
    		}
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	if(!matChFoundinTable)
    	{
        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
			PassFailFailDescription = thisMethodName + "->" + ObjectName +" Item->" + Input + "->Clicked Failed" + "<br>";
    	}
    	
    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
		
		return Rtype;
    	
    }
    public boolean Validate_List_in_ListBox(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;
        String errorFlag = "";

        //****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Looking Text in Table .");
    		
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			//WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        //List<WebElement> list_collection = liste_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li"));
    	        List<WebElement> list_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li"));

    	        System.out.println("NUMBER OF Item Found IN "+ ObjectName +" = "+list_collection.size());
    	       
    	        //===Input Breaking 
				String testString = Input; 
				System.out.println("Excel Input Text That need to Verify In List \n " + testString);

				//==>> Input should come in [ text : text \n ] format so we will check it and if its not in this format we will format it here 
				if(!testString.contains(":"))
				{
					testString = "List : " + Input;
				}
				
				String[] temp = testString.split("\n");
				ArrayList<String> allInputList = new ArrayList<String>();
				
				//StringUtils sutil = new StringUtils();

				for(int i=0;i<temp.length;i++)
				{
					System.out.println("Input " + i + " to Verify => " +  temp[i].split(":")[1]);
					allInputList.add(temp[i].split(":")[1]);
				}
    	        
				boolean MatchFound=false;
				for(int i=0;i<allInputList.size();i++)
				{
					listLoop : for(int j=0;j<list_collection.size();j++)
					{
						if(StringUtils.containsIgnoreCase(list_collection.get(j).getText().trim(), allInputList.get(i).trim()))
						{
							Rtype= true;
							matChFoundinTable=true;
							MatchFound=true;
							break listLoop;
						}
					}
					if(!MatchFound)
					{
						System.out.println(allInputList.get(i)+ " -> Expected Item not Found in List<br>");
						Rtype= false;
						errorFlag = errorFlag + "Expected Item/Text " + allInputList.get(i)+  " not Found in List<br>";
					}
				}
				
				//**Finally If Rtype Comes True From All Nested Loop above 
				if(Rtype == true)
				{
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> " + ObjectName +"-> All Expected List Found"  );
					PassFailFailDescription = thisMethodName + "-> " + ObjectName +"-> All Expected Item Found" + "<br>";
					Rtype= true;
					matChFoundinTable=true;
				}
    		}
    		catch(Exception e)
    		{
            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->  Failed" );
            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			PassFailFailDescription = thisMethodName + "-> From List  " + ObjectName +"->  Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			Rtype= false;
    		}
    		
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	if(!matChFoundinTable)
    	{
        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"-> Failed<br>" + errorFlag );
			PassFailFailDescription = thisMethodName + "-> From List  " + ObjectName +"-> Failed<br>" + errorFlag + "<br>";
    	}
    	
    	return Rtype;
    }
    public boolean Validate_Item_Not_Present_In_ListBox(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;
        String errorFlag = "";

        //****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Looking Text in Table .");
    		
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    	        List<WebElement> list_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li"));
    	        System.out.println("NUMBER OF List IN List Box = "+list_collection.size());
    	       
				String testString = Input; 
				System.out.println("All List Coming From Excel Input\n " + testString);
				
				//StringUtils sutil = new StringUtils();
				ArrayList<String> allInputList = new ArrayList<String>();
				if(!testString.contains("\n"))
				{
					testString = "Justtomakeitworks : " + testString +  "\n";
				}
				
				String[] temp = testString.split("\n");


				for(int i=0;i<temp.length;i++)
				{
					System.out.println(temp[i].split(":")[1]);
					allInputList.add(temp[i].split(":")[1]);
				}
    	        
				boolean MatchFound=false;
				for(int i=0;i<allInputList.size();i++)
				{
					listLoop : for(int j=0;j<list_collection.size();j++)
					{
						//if(list_collection.get(j).getText().trim().equals(allInputList.get(i).trim()))
						if(StringUtils.containsIgnoreCase(list_collection.get(j).getText().trim(), allInputList.get(i).trim()))
						{
							System.out.println(allInputList.get(i)+ " -> Expected Item Found in List<br>");
							Rtype= false;
							highlightElementBackground(list_collection.get(j),"fail");
							break listLoop;
						}
						else
						{
							Rtype= true;

						}
					}
				
				}
				
				//**Finally If Rtype Comes True From All Nested Loop above 
				if(Rtype == false)
				{
					//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> " + ObjectName +"-> All Expected Item Found in List");
					PassFailFailDescription = thisMethodName + "-> " + ObjectName +"-> All Expected Item Found in List" + "<br>";
					Rtype= false;
				}
				else
				{
					//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> " + ObjectName +"-> All Expected Item Not Found in Entire List"  );
					PassFailFailDescription = thisMethodName + "-> " + ObjectName +"-> All Expected Item Not Found in Entire List" + "<br>";
					Rtype= true;
				}
    		}
    		catch(Exception e)
    		{
            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->  Failed" );
            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			PassFailFailDescription = thisMethodName + "-> From List  " + ObjectName +"->  Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			Rtype= false;
    		}
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	//if(!matChFoundinTable)
    	//{
        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"-> Failed<br>" + errorFlag );
    	//}
    	return Rtype;
    }
    public boolean Select_CheckBox_from_CheckListBox(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;
        String errorFlag = "";

        //****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Looking Text in Table .");
    		
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			//WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        List<WebElement> list_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li"));
    	        System.out.println("NUMBER OF List IN List Box = "+list_collection.size());
    	        //int row_num,col_num;
    	        //row_num=1;
    listLoop: for(int i=0;i<list_collection.size();i++)
    	        {
    	            
					System.out.println(list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(Input.trim()))
					{
						//list_collection.get(i).click();
						
						if(driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/div")).size() > 0) // Check Box is a <div> Element
						{
							driver.findElement(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/div")).click();
						}
						else if(driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/input")).size() > 0) //Check Box is a <input> Element
						{
							driver.findElement(By.xpath(ObjectLocatorXpathCssETC+"/li["+(i+1)+"]/input")).click();
						}
						
						hardWait();
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Listbox  " + ObjectName +"->" + Input + "->Clicked Passed" );
						PassFailFailDescription = thisMethodName + "-> From Listbox  " + ObjectName +"->" + Input + "->Clicked Passed" + "<br>";

						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
    	            	matChFoundinTable= false;
    	            	errorFlag = Input + "  Not Found In List ";
	                }
					
    	        } 		
    		}
    		catch(Exception e)
    		{
            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "->Clicked Failed" );
            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			PassFailFailDescription = thisMethodName + "-> From List  " + ObjectName +"->" + Input + "->Clicked Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			Rtype= false;
    		}
    		
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	if(!matChFoundinTable)
    	{
        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
			PassFailFailDescription = thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" + "<br>";
    	}
    	
    	
    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    	return Rtype;
    }
 
    
    /********************************************* Web Table  **************************************************************************************************************/
    public boolean ValidateTableCellText(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;
    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************

    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
    		Input = fInterpretExcelData(Input);
    	
    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement table_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/tbody/tr"));
    	        System.out.println("NUMBER OF ROWS IN THIS "+ ObjectName +" => "+tr_collection.size());
    	        int row_num,col_num;
    	        row_num=1;
    	  TableRowLoop:for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            String EachRowData = "";
    	            col_num=1;
    	            for(WebElement tdElement : td_collection)
    	            {
    	                System.out.print(tdElement.getText()+" ");
    	                EachRowData += tdElement.getText() + " ";
    	                col_num++;
    	                if(EachRowData.contains(Input))
    	                {
        	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" );
    	                	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" + "<br>"; 
        	    			Rtype= true;
	            			highlightElementBackground(tdElement,"pass");
	            			highlightElement(tdElement,"pass");
        	    			matChFoundinTable=true;
							break TableRowLoop;
    	                }
    	                else
    	                {
        	    			matChFoundinTable=false;
    	                }
    	            }
    	            System.out.println("");
    	            row_num++;
    	        } 		
    		}
    		catch(Exception e)
    		{
            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "-> Failed" );
            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			Rtype= false;
            	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "FAILED" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
    		
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	if(!matChFoundinTable)
    	{
        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +" -> " + Input + " -> Not Found In Table" );
        	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + " -> Not Found In Table" + "FAILED" + "<br>";
        	//takeScreenShot();
    	}
    	
    	return Rtype;
    }
    public boolean ValidateTableCellText(String Step, String Page, WebElement ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    		boolean Rtype = false;
        boolean matChFoundinTable=false;
    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************

    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
    		Input = fInterpretExcelData(Input);
    	
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try{
    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement table_element = ObjectName;
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath("//tr"));
    	        System.out.println("NUMBER OF ROWS IN THIS "+ ObjectName +" => "+tr_collection.size());
    	        int row_num,col_num;
    	        row_num=1;
    	  TableRowLoop:for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            String EachRowData = "";
    	            col_num=1;
    	            for(WebElement tdElement : td_collection)
    	            {
    	                System.out.print(tdElement.getText()+" ");
    	                EachRowData += tdElement.getText() + " ";
    	                col_num++;
    	                if(StringUtils.containsIgnoreCase(EachRowData,Input))
    	                {
        	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" );
    	                	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" + "<br>"; 
        	    			Rtype= true;
	            			highlightElementBackground(tdElement,"pass");
	            			highlightElement(tdElement,"pass");
        	    			matChFoundinTable=true;
							break TableRowLoop;
    	                }
    	                else
    	                {
        	    			matChFoundinTable=false;
    	                }
    	            }
    	            System.out.println("");
    	            row_num++;
    	        } 		
    		}
    		catch(Exception e)
    		{
            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "-> Failed" );
            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			Rtype= false;
            	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "FAILED" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
    		
		//}
		//else
		//{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			//Rtype= false;
		//}
    	
    	if(!matChFoundinTable)
    	{
        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +" -> " + Input + " -> Not Found In Table" );
        	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + " -> Not Found In Table" + "FAILED" + "<br>";
        	//takeScreenShot();
    	}
    	
    	return Rtype;
    }
    
    public boolean ValidateTableRowText(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;

    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	//Interpret Input
			Input = fInterpretExcelData(Input);

    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
    		try{
	            System.out.println("\nExpect Row Data to Validate in Table: " + Input);

    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement table_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/tbody/tr"));
    	        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
    	        int row_num,col_num;
    	        row_num=1;
    	        for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            //System.out.println("NUMBER OF COLUMNS="+td_collection.size());
    	            String EachRowData = "";
    	            col_num=1;
    	            for(WebElement tdElement : td_collection)
    	            {
    	                //System.out.println("row # "+row_num+", col # "+col_num+ " text="+tdElement.getText());
    	                //System.out.print(tdElement.getText()+" ");
    	                EachRowData += tdElement.getText() + " | ";
    	                col_num++;
    	            }
    	            
    	            //System.out.println("");
    	            System.out.println("Actual Row "+  row_num + " Data:  " + EachRowData);
    	            //System.out.println("");
    	            
    	            if(EachRowData.contains(Input))
    	            {
    	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" );
    	            	highlightElementBorder(trElement,"pass");
    	            	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" + "<br>";
    	    			Rtype= true;
    	    			matChFoundinTable=true;
    	    			break;
    	            }
    	            else // If Match not Found In Row
    	            {
    	            	matChFoundinTable= false;
    	            }
    	            row_num++;
    	        } 		
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			
            	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" + "<br>";
            	PassFailFailDescription = e.getMessage() + "<br>";

    			Rtype= false;
    		}
    		
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	if(!matChFoundinTable)
    	{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
        	PassFailFailDescription = thisMethodName + " -> From Table -> " + ObjectName +"<br>" + Input + "-> Failed" + "<br>";

    	}
    	
    	return Rtype;
    }

    public boolean ValidateTableRowTexNotExist(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;

    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
    		try{
	            System.out.println("\nExpect Row Data to Validate in Table: " + Input);

    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement table_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/tbody/tr"));
    	        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
    	        int row_num,col_num;
    	        row_num=1;
    	        for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            //System.out.println("NUMBER OF COLUMNS="+td_collection.size());
    	            String EachRowData = "";
    	            col_num=1;
    	            for(WebElement tdElement : td_collection)
    	            {
    	                //System.out.println("row # "+row_num+", col # "+col_num+ " text="+tdElement.getText());
    	                //System.out.print(tdElement.getText()+" ");
    	                EachRowData += tdElement.getText() + " | ";
    	                col_num++;
    	            }
    	            
    	            //System.out.println("");
    	            System.out.println("Actual Row "+  row_num + " Data:  " + EachRowData);
    	            //System.out.println("");
    	            
    	            if(EachRowData.contains(Input))
    	            {
    	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" );
    	            	highlightElementBorder(trElement,"fail");
    	            	PassFailFailDescription = thisMethodName + " -> From Table  " + ObjectName +" -> " + Input + "->Failed  Because row should not exist" + "<br>";
    	    			Rtype= false;
    	    			matChFoundinTable=false;
    	    			break;
    	            }
    	            else // If Match not Found In Row
    	            {
    	            	matChFoundinTable= true;
    	    			Rtype= true;
    	            }
    	            row_num++;
    	        } 		
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
    			
            	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" + "<br>";
            	PassFailFailDescription = e.getMessage() + "<br>";

    			Rtype= false;
    		}
    		
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	if(matChFoundinTable)
    	{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
        	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "-> PAssed Because Row dont exist in table :-) " + "<br>";
    	}
    	
    	return Rtype;
    }
    
    public boolean ClickTableCellText(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;

    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Looking Text in Table .");
    		
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement table_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/tbody/tr"));
    	        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
    	        int row_num,col_num;
    	        row_num=1;
    TableRowLoop: for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            //System.out.println("NUMBER OF COLUMNS="+td_collection.size());
    	            String EachRowData = "";
    	            col_num=1;
    	            for(WebElement tdElement : td_collection)
    	            {
    	                //System.out.println("row # "+row_num+", col # "+col_num+ " text="+tdElement.getText());
    	                System.out.print(tdElement.getText()+" ");
    	                EachRowData += tdElement.getText() + " ";
    	                col_num++;
    	                if(EachRowData.contains(Input))
    	                {
    	                	tdElement.click();
    	                	hardWait();
    	                	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" );
    	                	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed"+ "<br>";
        	    			Rtype= true;
        	    			matChFoundinTable=true;
							break TableRowLoop;
    	                }
    	                else
    	                {
        	            	matChFoundinTable= false;
    	                }
    	                
    	            }
    	            
    	            System.out.println("");
    	            //if(EachRowData.contains(Input))
    	            //{
    	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" );
    	    			//Rtype= true;
    	    			//matChFoundinTable=true;
    	    			//break;
    	            //}
    	            //else // If Match not Found In Row
    	            //{
    	            	//matChFoundinTable= false;
    	            //}
    	            row_num++;
    	        } 		
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
            	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed"+ "<br>";
            	PassFailFailDescription = e.getMessage() + "<br>";
    			Rtype= false;
    		}
    		
		//}
		//else
		//{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			//Rtype= false;
		//}
    	
    	if(!matChFoundinTable)
    	{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
        	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed"+ "<br>";

    	}
    	
    	return Rtype;
    }
    public boolean ClickTableCellText(String Step, String Page, WebElement ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;

    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Looking Text in Table .");
    		
    		try{
    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement table_element = ObjectName;
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath("//tbody/tr"));
    	        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
    	        int row_num,col_num;
    	        row_num=1;
    TableRowLoop: for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            //System.out.println("NUMBER OF COLUMNS="+td_collection.size());
    	            String EachRowData = "";
    	            col_num=1;
    	            for(WebElement tdElement : td_collection)
    	            {
    	                System.out.print(tdElement.getText()+" ");
    	                EachRowData += tdElement.getText() + " ";
    	                col_num++;
    	                if(StringUtils.containsIgnoreCase(EachRowData,Input))	
    	                {
           	                tdElement.click();
    	                	hardWait();
    	                	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed"+ "<br>";
        	    			Rtype= true;
        	    			matChFoundinTable=true;
							break TableRowLoop;
    	                }
    	                else
    	                {
        	            	matChFoundinTable= false;
    	                }
    	            }
    	            
    	            System.out.println("");
    	            //if(EachRowData.contains(Input))
    	            //{
    	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Passed" );
    	    			//Rtype= true;
    	    			//matChFoundinTable=true;
    	    			//break;
    	            //}
    	            //else // If Match not Found In Row
    	            //{
    	            	//matChFoundinTable= false;
    	            //}
    	            row_num++;
    	        } 		
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
            	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed"+ "<br>";
            	PassFailFailDescription = e.getMessage() + "<br>";
    			Rtype= false;
    		}
    		
		//}
		//else
		//{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			//Rtype= false;
		//}
    	
    	if(!matChFoundinTable)
    	{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
        	PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed"+ "<br>";

    	}
    	
    	return Rtype;
    }

    public boolean ValidateTableCellText_Bold(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        boolean matChFoundinTable=false;
        String errorFlag = "";
    	
    	//****** Report: This Step That is Executing ************************************   
    	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Looking Text in Table .");
    		
    		try{
    			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			WebElement table_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
    	        List<WebElement> tr_collection = table_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/tbody/tr"));
    	        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
    	        int row_num,col_num;
    	        row_num=1;

    	        //==> Get the Test Data Input and see its Fine 
                String RowPrimaryKey = null; // The Distinct Row Where the Validation will Happen 
                if(Input.contains("|"))
                {
                	String[] temp = Input.split("\\|");
                	if(temp.length>1)
                	{
                    	RowPrimaryKey = Input.split("\\|")[1].trim();
                    	if(RowPrimaryKey.equals("") || RowPrimaryKey.equals(" ") || RowPrimaryKey.equals(null) )
                    	{
                    		System.out.println("Check Input Test Data it should be 2 value seperated with (|) Character  ");
                    		System.out.println("Example [ Date | EventNAme ] ");
                    		//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, thisMethodName + "  -> Check Input Test Data it should be 2 value seperated with (|) Character  " );
                    		PassFailFailDescription = thisMethodName + "  -> Check Input Test Data it should be 2 value seperated with (|) Character  " + "<br>";
                        	return false;
                    	}
                    	
                	}
                	else 
                	{
                		System.out.println("Check Input Test Data it should be 2 value seperated with (|) Character  ");
                		System.out.println("Example [ Date | EventNAme ] ");
                		//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, thisMethodName + "  -> Check Input Test Data it should be 2 value seperated with (|) Character  " );
                		PassFailFailDescription = thisMethodName + "  -> Check Input Test Data it should be 2 value seperated with (|) Character  " + "<br>";

                    	return false;
                	}
                }
                //======= End Input Validation ..
    	        
    TableRowLoop: for(WebElement trElement : tr_collection)
    	        {
    	            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
    	            String EachRowData = "";
    	            col_num=1;
    	            //for(WebElement tdElement : td_collection)
        	        for(int i=1;i<td_collection.size();i++)
    	            {
    	                //System.out.println("row # "+row_num+", col # "+col_num+ " text="+tdElement.getText());
    	                System.out.print(td_collection.get(i).getText()+" ");
    	                EachRowData = td_collection.get(i).getText() + " ";
    	                if(EachRowData.contains(RowPrimaryKey))
    	                {
    	                	/**
    	                	 * Suite Events Table Structure 
    	                	 * Col1. GearIcon | Col2. Date | Col3. Event | Col4. Start Time |  
    	                	 * Col5. DoorsOpen| Col6. Reg. Cut off | Col7. Kosher Deadline | Col8. Orders
    	                	**/                              
    	                	WebElement DateCellElement = td_collection.get(2);  // get(2) because date is in Second Column

    	                	//ElementName.click();  // if you need to Click
    	                	//hardWait();
    	                	
    	                	String weight =  DateCellElement.getCssValue("font-weight");
    	                	if(weight.toUpperCase().equals("BOLD"))
    	                	{
    	                		//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + " -> For Table ->  " + ObjectName +" -> Date Field for EventName : " + RowPrimaryKey + " -> Is Bold" );
                        		PassFailFailDescription = thisMethodName + " -> For Table ->  " + ObjectName +" -> Date Field for EventName : " + RowPrimaryKey + " -> Is Bold" + "<br>";
            	    			Rtype= true;
    	            			highlightElement(DateCellElement,"pass");
            	    			matChFoundinTable=true;
    							break TableRowLoop;
    	                	}
    	                	else
    	                	{
    	                		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + " -> For Table ->  " + ObjectName +" -> Date Field for EventName : " + RowPrimaryKey + " -> Is Not Bold" );
                        		PassFailFailDescription = thisMethodName + " -> For Table ->  " + ObjectName +" -> Date Field for EventName : " + RowPrimaryKey + " -> Is Not Bold" + "<br>";

    	            			Rtype= false;
    	            			highlightElement(DateCellElement,"fail");
            	    			matChFoundinTable=true;
    							break TableRowLoop;
    	                	}
    	                }
    	                else  // This is a Column Looping inside Each Table Row - Error Flag will Store here until it not match 
    	                {
        	            	matChFoundinTable= false;
        	            	errorFlag = thisMethodName + " - > EventName : " + RowPrimaryKey + " -> Is Not Found In  ["+ ObjectName +"]  Table";
        	            	//errorFlag = errorFlag +  "Text" + "<br>"  //If you wants to multine Error Msg
    	                }

    	            }//Columns Loop End Here
        	        
    	            System.out.println(""); // One Line Break After Each Rows 
    	            row_num++;
 
    	        }//Row Loop End Here 
                
    		}
    		catch(Exception e)
    		{
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" );
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
        		PassFailFailDescription = thisMethodName + "-> From Table  " + ObjectName +"->" + Input + "->Failed" + "<br>";
        		PassFailFailDescription = e.getMessage() + "<br>";
    			Rtype= false;
    		}
    		
		}
		else
		{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			Rtype= false;
		}
    	
    	if(!matChFoundinTable)
    	{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, errorFlag );
    		PassFailFailDescription = errorFlag + "<br>";
    	}
    	
    	return Rtype;
    }
   
   
    /********************************************* Validate Elements   **************************************************************************************************************/
	public boolean Validate_Element_NotExist(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException{
		
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName + "<<<<<");
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Validating Onject Not Exist In Page..");
			//PassFailFailDescription = thisMethodName + "->" + ObjectName + "->"+ Input + "-> Passed" + "<br>";

			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			boolean s=false;
			WebElement TargetObject = null;
			try{

				 TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
				s = TargetObject.isDisplayed();
			}
			catch(Exception e){
				//System.out.println(e.getMessage());
				s=false;
			}
			
			if(s==false)
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "Element Not found Which Was Expected");
				PassFailFailDescription = thisMethodName + "->" + ObjectName +  "-> Element Not found Which Was Expected" + "<br>";
				Rtype= true;
			}
			else
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "Element found Which is Not Expected");
				PassFailFailDescription = thisMethodName + "->" + ObjectName +  "-> Element found Which Was not Expected" + "<br>";
				highlightElement(TargetObject,"fail");
				Rtype= false;
			}
		}
		else
		{
			Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		}

    	return Rtype;		

	}
	public boolean Validate_Element_Exist(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{

		
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName +"<<<<<");
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		//{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Validating Onject Exist In Page..");
			
			
			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			WebElement TargetObject=null;
			boolean s=false;
			
			try
			{
    			if(!waitUntilClickable(ObjectLocatorXpathCssETC)) return false; // wait a Little bit for Object to Load
				TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
				s = TargetObject.isDisplayed();
			}
			catch(Exception e)
			{
				//System.out.println(e.getMessage());
				s=false;
			}

			if(s==false)
			{
				PassFailFailDescription = thisMethodName + " -> "+ ObjectName +  " -> Element Not Found " + "<br>";
    			takePassFailScreenShots(Step.replaceAll("\\s",""));
				Rtype= false;
			}
			else
			{
				PassFailFailDescription = thisMethodName + " -> Element Found " + "<br>";
				highlightElement(TargetObject,"pass");
				Rtype= true;
			}
		//}
		//else
		//{
			//Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		//}

    	return Rtype;		
	}
	public boolean Validate_Element_Exist(String Step, String Page, WebElement ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException{
		
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName +"<<<<<");
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		//{
			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			WebElement TargetObject=ObjectName;
			boolean s=false;
			
			try
			{
    			if(!waitUntilClickable(TargetObject)) return false; // wait a Little bit for Object to Load
				//TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
				s = TargetObject.isDisplayed();
			}
			catch(Exception e){
				//System.out.println(e.getMessage());
				s=false;
			}

			if(s==false)
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "Element Not Found ");
				WebUI.PassFailFailDescription = thisMethodName + " -> Element Not Found " + "<br>";
				Rtype= false;
			}
			else
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "Element found ");
				WebUI.PassFailFailDescription = thisMethodName + " -> Element Found " + "<br>";
				highlightElement(TargetObject,"pass");
				Rtype= true;
			}
		//}
		//else
		//{
			//Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		//}

    	return Rtype;		
	}
	public boolean Validate_Element_Enabled(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException{

		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName +"<<<<<");
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Validating Onject Exist In Page..");
			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			WebElement TargetObject=null;
			boolean s=false;
			try
			{

				TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
				s = TargetObject.isEnabled();
			}
			catch(Exception e){
				//System.out.println(e.getMessage());
				s=false;
			}

			if(s==false)
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "Element Not Enabled ");
				PassFailFailDescription = thisMethodName + " -> Element Not Enabled " + "<br>";
				Rtype= false;
				highlightElement(TargetObject,"fail");
			}
			else
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "Element Enabled ");
				PassFailFailDescription = thisMethodName + " -> Element Enabled " + "<br>";

				highlightElement(TargetObject,"pass");
				Rtype= true;
			}
		}
		else
		{
			Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		}

    	return Rtype;		
	}
	public boolean Validate_Element_Disabled(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException{
		
    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName + "<<<<<");
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		{
			//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Validating Onject Not Exist In Page..");
			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			WebElement TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));

			boolean s=false;
			try{
				//s = TargetObject.isEnabled();
					TargetObject.click();
					hardWaitFor(1000);
			}
			catch(Exception e){
				//System.out.println(e.getMessage());
				s=false;
			}
			
			if(s==false)
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "Element Not Enabled Which Was Expected");
				PassFailFailDescription = thisMethodName + " -> Element Not Enabled Which Was Expected" + "<br>";

				Rtype= true;
				highlightElement(TargetObject,"pass");
			}
			else
			{
				//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "Element Enabled Which is Not Expected");
				PassFailFailDescription = thisMethodName + " -> Element Enabled Which Was not Expected" + "<br>";

				Rtype= false;
				highlightElement(TargetObject,"fail");
			}
		}
		else
		{
			Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		}

    	return Rtype;		

	}
	
	
    /********************************************* Verify_Text Text   **************************************************************************************************************/
	public boolean Verify_Text(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException{

    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName +"<<<<<");
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		//{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Verifying Text..");
			
			
			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			String ActualText = "";

			WebElement TargetObject=null;
			//boolean s=false;
			
			try
			{
				//====== TO DO : Temporary Solution  if calender object is open close it first 
					if(Page.equals("EditEvent_Popup"))
					{
						driver.findElement(By.xpath(GetObjectLocatorXPathCssEtcFromRepo(Page))).click();
					}
				
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectLocatorXpathCssETC)));
	
				TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
				ActualText = TargetObject.getText();
				
				if(!Input.equals(""))
				{
					if(Input.trim().equals(ActualText.trim()))
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "<b>Expected Text :</b> " + Input + "<br>" + "<b>Actual Text :</b> " + ActualText);
						PassFailFailDescription = thisMethodName +"<br>"+ "<b>Expected Text :</b> " + Input + "<br>" + "<b>Actual Text :</b> " + ActualText + "<br>";
						highlightElement(TargetObject,"pass");
						Rtype = true;
					}
					else //If Text Not Matching 
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "<b>Expected Text:</b> " + Input + "<br>" + "<b>Actual Text :</b> <mark>" + ActualText + "</mark>");
						PassFailFailDescription = thisMethodName +"<br>"+ "<b>Expected Text:</b> " + Input + "<br>" + "<b>Actual Text :</b> <mark>" + ActualText + "</mark>" + "<br>";
						highlightElement(TargetObject,"fail");
						Rtype = false;
					}
				}
				else //IF Input or Expected Text Is Empty 
				{
					//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "Expected Input Text From Test Case Excel File is Empty ");
					PassFailFailDescription = thisMethodName + "->" + "Expected Input Text From Test Case Excel File is Empty "+ "<br>";
					Rtype = false;
				}
			}
			catch(Exception e)
			{
				//System.out.println(e.getMessage());
				Rtype = false;
				//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, "Can Not Verify Text Reason : <br>" + e.getMessage());
				PassFailFailDescription = thisMethodName + "->" + "Can Not Verify Text Reason : <br>"+  e.getMessage() + "<br>";
			}
		//}
		//else //If Page is not Ready 
		//{
			//Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		//}

    	return Rtype;		
	}
	public boolean Verify_Text_Bold(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException{

    	boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>>>"+ Step + " : " + thisMethodName + " -> " + ObjectName +"<<<<<");
    	//********************************************************************************
    	
    	if (fEnsurePageIsReady(Page)) //Check If the Page & Object is ready Else Return False - 
		{
    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Verifying Text..");
			
			
			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			String ActualText = "";

			WebElement TargetObject=null;
			//boolean s=false;
			
			try
			{
									//====== TO DO : Temporary Solution  if Calendar object is open close it first 
										if(Page.equals("EditEvent_Popup"))
										{
											driver.findElement(By.xpath(GetObjectLocatorXPathCssEtcFromRepo(Page))).click();
										}
								   //================================xxxxx========================================		
					
				TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));//
				ActualText = TargetObject.getText();
				String weight =  TargetObject.getCssValue("font-weight");
				
				if(!Input.equals(""))
				{
					if(Input.equals(ActualText) && !weight.toUpperCase().equals("NORMAL") )
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, "<b>Expected Text :<b> " + Input + "<br>  is <b>BOLD</b> See the Screen shot");
						PassFailFailDescription = thisMethodName + "<br>" + "<b>Expected Text :<b> " + Input + " is <b>BOLD</b> See the Screen shot"+ "<br>";
						highlightElement(TargetObject,"pass");
						Rtype = true;
					}
					else //If Text Not Matching 
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "<b>Expected Text :<b> " + Input + "<br>  is not <b>BOLD</b> See the Screen shot");
						PassFailFailDescription = thisMethodName + "<br>" + "<b>Expected Text :<b> " + Input + " is <b>not   BOLD</b> See the Screen shot"+ "<br>";
						highlightElement(TargetObject,"fail");
						Rtype = false;
					}
				}
				else //IF Input or Expected Text Is Empty 
				{
					//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, "Expected Input Text From Test Case Excel File is Empty ");
	    			PassFailFailDescription = thisMethodName + "Expected Input Is Empty..." + "<br>";

					Rtype = false;
				}
			}
			catch(Exception e)
			{
				//System.out.println(e.getMessage());
				Rtype = false;
				//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, "Can Not Verify Text Reason : <br>" + e.getMessage());
				PassFailFailDescription = thisMethodName + "->" + "Can Not Verify Text Reason : <br>"+  e.getMessage() + "<br>";
			}
		}
		else //If Page is not Ready 
		{
			Rtype= false;  //If  Sanity Check failed (Page is not Ready  Object we Looking is Not ready). It has its Own PAss Fail Reporting 
		}

    	return Rtype;		
	}

	
	
    /********************************************* MISC **************************************************************************************************************/

	public boolean waitForpageLoad(String Step, String Page, String ObjectName, String Input)
	{
		boolean rtype = false;
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();

			try 
			{
				if(Input.contains("."))
				{
					Input = Input.replaceAll("\\.0*$", "");
				}
				
				Thread.sleep(Long.parseLong(Input));
				PassFailFailDescription = thisMethodName + " Waited for " + Input + "  -Second" + "<br>";
				rtype = true;
			} 
			catch (InterruptedException e) 
			{
				PassFailFailDescription = thisMethodName + " Waited for " + Input + "  -Second Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    			rtype=false;
				//e.printStackTrace();
			}
		
		return rtype;
	}

	public boolean scrollToElement( String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
    	boolean rtype = false;
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		try
		{
			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			WebElement TargetObject=null;
			TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TargetObject);
			hardWaitFor(1000);
			rtype=true;
		}
		catch(Exception e) 
		{
			PassFailFailDescription = thisMethodName + " Scrolling To Element " + ObjectName + "  -Failed" + "<br>";
			PassFailFailDescription = e.getMessage() + "<br>";
			rtype=false;
			//e.printStackTrace();
		}
		return rtype;
	}

    public boolean Validate_Image_Present(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
    {
    	boolean Rtype = false;
        //****** Report: This Step That is Executing ************************************   
     	String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
     	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName );
     	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
    	//{
    		try
    		{
 				String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
				//[contains(@src,'visa.png')]
	    		WebElement ImageFile = driver.findElement(By.xpath(ObjectLocatorXpathCssETC+"[contains(@src,'"+Input+"')]"));
	            Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
	            if (!ImagePresent)
	            {
	                 System.out.println("Image not displayed.");
	             		//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + " --> Passed - >  Image Not Present");
	             		PassFailFailDescription = thisMethodName + " --> Passed - >  Image Not Present" + "<br>";

	             		Rtype = false;
	             		highlightElement(ImageFile,"fail");
	            }
	            else
	            {
	                System.out.println("Image displayed.");
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + " --> Passed - >  Image Present");
	            	PassFailFailDescription = thisMethodName + " --> Passed - >  Image Present" + "<br>";
	         		Rtype = true;
	         		highlightElement(ImageFile,"pass");
	            }
      		}
    		catch(Exception e)
    		{
           // Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + " -> From  " + ObjectName +" -> ImageName-> " + Input + " -> Failed" );
            //	Test_DriverThread.ScenarioReport.log(LogStatus.FAIL,  "May be This is not the Right Image Displaying in Each Event" );
            //	Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );

            	PassFailFailDescription = thisMethodName + " -> From  " + ObjectName +" -> ImageName-> " + Input + " -> Failed"  + "<br>";
             	PassFailFailDescription = "May be This is not the Right Image Displaying in Each Event" + "<br>";
            	PassFailFailDescription = e.getMessage() + "<br>";
            	//takeScreenShot();
    			Rtype= false;
    		}
       		
    	//}
		//else
		//{
			//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
			//Rtype= false;
		//}
    	
            
        	return Rtype;
 
    	}
	
	public void MouseOvertoelement(WebElement ele)
	{
		try
		{
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
		}
		catch(Exception e)
		{
    		WebUI.PassFailFailDescription =  " -> Failed to Mouse Over On element  " + ele.getText() + "<br>";
    		WebUI.PassFailFailDescription = e.getMessage() + "<br>";
			//Rtype= false;
		}
	}
	
	public boolean MouseOvertoelement(String ObjectName)
	{
    	boolean rtype = false;
		try
		{
			String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			WebElement ele=null;
			ele = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
			rtype = true;
			
		}
		catch(Exception e)
		{
    		WebUI.PassFailFailDescription =  " -> Failed to Mouse Over On element  " + ObjectName + "<br>";
    		WebUI.PassFailFailDescription = e.getMessage() + "<br>";
    		rtype= false;
		}
		
		return rtype;
	}
	
	public static Integer verifyIfLinkIsWorking(String urlToVerify) throws Exception
	{
			URL url = new URL(urlToVerify);
			HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
			try	 
			{	 
				myConnection.connect(); 
			    Integer myResponse = myConnection.getResponseCode();      	 
			    myConnection.disconnect();	 
			    return myResponse;	 
			}	 
			catch(Exception exp)	 
			{	 
				System.out.println("URL: "+url+" Can't be rsolved");
				return 404;
			}					 
	}	
	
	public boolean NavigateBack(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
    	boolean rtype = false;
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		try
		{
			driver.navigate().back();
			hardWait();
			rtype=true;
		}
		catch(Exception e) 
		{
			PassFailFailDescription = thisMethodName + " Navigating Back From Page  " + Page + "  -Failed" + "<br>";
			PassFailFailDescription = e.getMessage() + "<br>";
			rtype=false;
			//e.printStackTrace();
		}
		return rtype;
	}
	
	public boolean DebugCode()
	    {
	    	//Nothing 
	    	return true;
	    }
	
	public boolean ExitDebug()
    {
    	//Nothing 
    	return false;
    }
	
	
    
//###################  Helper Functions  ##################################################################################################
     
	public String getFieldValues(final Object obj, String FieldName)
		    throws IllegalArgumentException,IllegalAccessException
		  {
		
	      Object value = "";

		    Class<? extends Object> c1 = obj.getClass();
		    Field[] fields = c1.getDeclaredFields();
		    
		    for (int i = 0; i < fields.length; i++) {
		      String name = fields[i].getName();
		      if(name.equals(FieldName))
		      {
		    	   value = fields[i].get(obj);
		    	   break;
		      }
		      
		    }
		    return (String) value;
		  }	
	public boolean fEnsurePageIsReady(String oPage) throws IllegalArgumentException, IllegalAccessException
	{
		int giTimeOut = 2;
		boolean bFound = false;
		int iMax = 0;
		String ErrorMsg="";
		String ObjectLocator;

	
		//==> Get The "Page" Locator From Excel File If Available 
		 ObjectLocator = GetObjectLocatorXPathCssEtcFromRepo(oPage);;

		//==> If Locator Not Available in Excel File Get it From REPO Java File 
  		 if(ObjectLocator.equals(""))
  		 {
	   		//Suite_Test_Repository dummy = new Suite_Test_Repository();
	   		//ObjectLocator = getFieldValues(dummy,oPage.trim());
  		 }
	   	
  		 if(ObjectLocator.equals(null)) ObjectLocator="";

	   //==> Check Page's Unique Object is Not Null Or Empty - If Empty Return False  or Log Page is Ready...		
			if(!ObjectLocator.equals(""))
			{
				while(iMax < giTimeOut  && !bFound)
				{
					try
					{
						iMax++; 
						int s = driver.findElements(By.xpath(ObjectLocator)).size();

						if(s==0)
						{
							bFound=false;
						}
						else
						{
							//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Page : ["+oPage + "] : Is Ready ");
							bFound =  true;
						}
					}
					catch(Exception e)
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, );
						bFound=false;
						System.out.println(e.getMessage());
						ErrorMsg = e.getMessage();
					}

				}
			 }

		//==> If Page's Unique Object Failed - Report Error And Take a Screen Shot 		
			if(bFound==false)
			{
				PassFailFailDescription = "Landing Page [" + oPage +"] is Not ready...<br>" + ErrorMsg;
				//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, "Landing Page [" + oPage +"] is Not ready..." + ErrorMsg);
				//takeScreenShot();
			}
		return bFound;
	}
	public boolean fEnsureObjectIsReady(String oObjectName) throws IllegalArgumentException, IllegalAccessException
	{
		int giTimeOut = 2;
		boolean bFound = false;
		int iMax = 0;
		String ErrorMsg="";
		String ObjectLocator;
	
		//==> Get The Object Locator From Excel File If Available 
		 ObjectLocator = GetObjectLocatorXPathCssEtcFromRepo(oObjectName);;

 		//==> If Locator Not Available in Excel File Get it From REPO Java File 
   		 if(ObjectLocator.equals(""))
   		 {
 	   		//Suite_Test_Repository dummy = new Suite_Test_Repository();
 	   		//ObjectLocator = getFieldValues(dummy,oObjectName.trim());
   		 }
	   	
   		 if(ObjectLocator.equals(null)) ObjectLocator="";

	    //==> See If this Object is Ready in Page Before Perform any Action ( Click Validate ) on it 		
			if(!ObjectLocator.equals(""))
			{
				while(iMax < giTimeOut  && !bFound)
				{
					try
					{
						iMax++; 
						driver.findElement(By.xpath(ObjectLocator)).isDisplayed();
						int s = driver.findElements(By.xpath(ObjectLocator)).size();
						if(s==0)
						{
							bFound=false;
						}
						else
						{
							//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Object : ["+oObjectName + "] : Is Ready ");
							bFound =  true;
						}
					}
					catch(Exception e)
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, );
						bFound=false;
						System.out.println(e.getMessage());
						ErrorMsg = e.getMessage();
					}

				}
			 }

		//==> If Page's Unique Object Failed - Report Error And Take a Screen Shot 		
			if(bFound==false)
			{
				PassFailFailDescription = "Target WebElement [" + oObjectName +"] is Not Found...<br>" + ErrorMsg + "<br>";
				//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, "Target Object [" + oObjectName +"] is Not ready..." + ErrorMsg);
				//takeScreenShot();
			}
		return bFound;
	}
	public String GetObjectLocatorXPathCssEtcFromRepo(String ObjectName) throws IllegalArgumentException, IllegalAccessException
	{
		String Locator="";

		// Get the Locator From Excel If Available 
		Map<String, String[]> AllLocatorFromTestCaseExcelFileObjectCommonSheet = getlistOfAllSelectors();
		String[] findLocator = new String[2];
		findLocator = AllLocatorFromTestCaseExcelFileObjectCommonSheet.get(ObjectName);
 		//if(findLocator.equals(null)) findLocator="";
		//System.out.println(findLocator.length);
		
		if(findLocator!=null)
		{
			Locator = findLocator[0];
			return Locator;
		}

		/*
		int count =1;
		for (String key : AllLocatorFromTestCaseExcelFileObjectCommonSheet.keySet()) 
		{
			String[] myValues = new String[2];
			myValues = AllLocatorFromTestCaseExcelFileObjectCommonSheet.get(key);
			System.out.println("Entry"+count+" "+key+" "+myValues[0]+" "+myValues[1]);
			// Out Put :        Entry1 SuiteHolderUser_Firstname_Field .//*[@id='editshuser']/div[2]/div[1]/div[1]/div[2]/input Xpath
			// Out Put :        Entry3 Admin_EventdetailsPage   CSS
			if(key.trim().equals(ObjectName.trim()))
			{
				Locator = myValues[0];
				return Locator;
			}
			count++;
		}
		*/
		

		//===>> If Locator (Xpath,CSS)  Not Found in Excel File look it in Java repo Class File  
			//Suite_Test_Repository dummy = new Suite_Test_Repository();
			//String ObjectLocator = getFieldValues(dummy,ObjectName);
			/*if(ObjectLocator.equals(null)) ObjectLocator="";

			if(!ObjectLocator.equals(""))
			{
				Locator = ObjectLocator.trim();
			}
			else 
			{
				//ZTest_DriverThreadThread.ScenarioReport.log(LogStatus.ERROR, "This Object " + ObjectName +" is Not Found In Suite_Test_Repository Java File..");
				System.out.println("\n ***** This Object " + ObjectName +" is Not Found In Suite_Test_Repository Java File..\n");
			}*/
		
		
		return Locator;
	}
	public Map<String, String[]> getlistOfAllSelectors()  throws IllegalAccessException, IllegalArgumentException
	{
		//Map<String, String[]> myDesiredSelectors = new HashMap<String, String[]>();
		
		Map<String, String[]> myAllSelectors = new HashMap<String, String[]>();
	
		
		 /*Xls_Reader currentTestCaseXLSFile;
		 //currentTestCaseXLSFile = new Xls_Reader(System.getProperty("user.dir")+"//data//"+ZTest_DriverThreadThread.currentTestSuite+".xlsx");
		 currentTestCaseXLSFile = new Xls_Reader(System.getProperty("user.dir")+"//data//"+currentTestSuite+".xlsx");
*/


		 //=======>> Loop Through the Entire test selectors sheet for all rows
		//for(int row=2;row<=currentTestCaseXLSFile.getRowCount(Constants.TEST_OBJECT_SELECTOR_SHEET);row++)
		{
			//String myCss = currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "CSS", row);	//
			//String myXpath = currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Xpath", row);	
			
			/*if (myCss.length() > 0)
			{
				String[] myValues = new String[2];
				myValues[0] = myCss; myValues[1] = "CSS";
				myAllSelectors.put(currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Object", row), myValues);
			}
			
			if (myXpath.length() > 0)
			{
				String[] myValues = new String[2];
				myValues[0] = myXpath; myValues[1] = "Xpath";
				myAllSelectors.put(currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Object", row), myValues);
			}*/
		}
		
			//if (myDesiredSelectors.containsKey("")) myDesiredSelectors.remove("");
			return myAllSelectors;
	}
	
	public Map<String, String> parseExcelInputCellData(String excelInputString)
	{
		
		Map<String, String> map = new HashMap<String, String>();
		//String test = "pet:cat::car:honda::location:Japan::food:sushi";
		String test = excelInputString;

		String[] test1 = test.split("\n");

		for (String s : test1) {
		    String[] t = s.split(":");
		    map.put(t[0], t[1]);
		}

		for (String s : map.keySet()) {
		    System.out.println(s + " is " + map.get(s));
		}
		

		return map;
	}
	
	//==== Random Input / [Random Input] Functions
	public String fInterpretExcelData(String Input)
	{
		String sOut = fInterpretData(Input);
		return sOut;
	}
	public String fInterpretData(String Input)
	{
		String sOut=Input;
		
		//String tmp = "SHAdmin [User 1] and then [Email 6] and nore";
		String tmp = Input;
		ArrayList<String> aRepl  = new ArrayList<String>();

		if (tmp.toString().indexOf("[") + 1 != 0)
		{
			String[] aRef = tmp.split("\\[");
			
			for (int i = 0; i <= aRef.length-1; i++)
			{
				if (aRef[i].indexOf("]") + 1 != 0)
				{
					aRepl.add(aRef[i].split("]", -1)[0]);
					//System.out.println(aRepl.get(0));
				}

			}

			for (int i = 0; i <= aRepl.size()-1; i++)
			{
				System.out.println(aRepl.get(i));
				String sInterpreted = fGetRandomTestData(aRepl.get(i));
				tmp = tmp.toString().replace("[" + aRepl.get(i) + "]", sInterpreted);
				sOut = tmp;
			}
		}
		else
		{
			sOut = fGetRandomTestData(Input);
		}
		
		return sOut;
	}
	public String fGetRandomTestData(String Input)
	{
		String returnString=Input;
		
		//====== Return A Random Email ================
			if(Input.matches(".*Email \\d"))
			{
				
				//returnString=generateEmail(Input); //  Not Using this Any more Pain on ass
				
				// == We can Not Use Random Number for a email to create it can Make a Duplicate in DB so we need a DB Query when create email
				/*if(ZTest_DriverThreadThread.AllEmail.get(Input).equals(""))
				{
					//ZTest_DriverThreadThread.AllEmail.put(Input, Constants.emailAcctAlias + RandomNumber() + "@mailinator.com");
					Input=
					returnString = ZTest_DriverThreadThread.AllEmail.get(Input);          // Return the Data From HasMap
				}
				else
				{
					returnString = ZTest_DriverThreadThread.AllEmail.get(Input);          // Because data is already in Hasmap Return ii
				}*/
			}
		
		/*//====== Return A Random User ID ================
			if(Input.matches(".*User \\d.*"))
			{
				if(Test_DriverThread.AllEmail.get(Input).equals(""))
				{
					Test_DriverThread.AllEmail.put(Input, "User" + RandomNumber()); // First Store The random Data in HasMap 
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
				}
				else
				{
					returnString =Test_DriverThread.AllEmail.get(Input);  // Because data is already in Hasmap Return it 
				}
			}*/

			
			
			//====== Get The Order Total From CheckOut Page and Store it in Has  ================		
		/*	if(Input.matches(".*Order Total \\d.*"))
			{
				if(Test_DriverThread.AllEmail.get(Input).equals("")) // If the Data HasMap Dictionary is Empty then go and get the Event Name 
				{
					String ObjectLocatorXpathCssETC = null;
					try
					{
						ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo("CheckOutOrderTotalLabel");
						WebElement ChkOutOrderTotalLAbel = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
						String OrderTotalAmount = ChkOutOrderTotalLAbel.getText().trim();

						Test_DriverThread.AllEmail.put(Input, OrderTotalAmount);
						returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
					}
					catch(Exception e)
					{
						
					}
				}
				else
				{
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
				}*/
				
			//}
			
			
			
			//====== Select A Event From Suites "Event Scroll Box" That Appears in Different Part of the Suites APp  ================		

			if(Input.trim().matches(".*Event List \\d.*"))
			{/*
				if(Test_DriverThread.AllEmail.get(Input).equals("")) // If the Data HasMap Dictionary is Empty then go and get the Event Name 
				{
					String ObjectLocatorXpathCssETC = null;
					try 
					{
					  //First we have to Load All Available Event List Elements 
						ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo("Scrollbox_List"); //Common Object Scroll Box List
						List<WebElement> Event_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC));
						System.out.println("NUMBER OF Event Found = "+Event_collection.size());
						//for(int i=0;i<Event_collection.size();i++)
						//{
							String listNumberToPick = stripNonDigits(Input);
							String EventName = driver.findElement(By.xpath(ObjectLocatorXpathCssETC+"["+listNumberToPick+"]/div[2]/span[1]")).getText();
							System.out.println("Events Details : " + EventName);
							//String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
							Test_DriverThread.AllEmail.put(Input, EventName);
							returnString = Test_DriverThread.AllEmail.get(Input);   // Return the Data From HasMap
						//}// End Looping For Events 
					} 
					catch (Exception e) 
					{
						//=== Error Problem Finding Event From Event List 
						e.printStackTrace();
					} 
				}
				else
				{
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
				}
				
			*/}

			
			//====== Select A Event From Suites "Client Event Tile" if not Found in Current Month Move to Next Month  ================		
			if(Input.trim().matches(".*Event \\d.*"))
			{/*
				if(Test_DriverThread.AllEmail.get(Input).equals("")) // If the Data HasMap Dictionary is Empty then go and get the Event Name 
				{
					String ObjectLocatorMonthFilterList = null;
					String ObjectLocatorXpathCssETC = null;
					try 
					{
						
					//First we have to Load All Available Month From Month Filter List 
						ObjectLocatorMonthFilterList = GetObjectLocatorXPathCssEtcFromRepo("EventsMonthsFilter_List");  //Get the Xpath Of Month Filter List here 
						ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo("ClientEventsList"); //Get the Xpath Of Event Filter List Here 

						List<WebElement> Month_collection = driver.findElements(By.xpath(ObjectLocatorMonthFilterList+"/li"));
						System.out.println("NUMBER OF Month Found = "+Month_collection.size());
		Monthloop:	for(int j=0;j<=Month_collection.size()-1;j++)
						{
							
							System.out.println("Looking In  Month : " + Month_collection.get(j).getText());
							this.highlightElementBorder(driver.findElement(By.xpath(ObjectLocatorMonthFilterList+"/li["+(j+1)+"]/span")), "pass");
							driver.findElement(By.xpath(ObjectLocatorMonthFilterList+"/li["+(j+1)+"]/span")).click(); //Clicking On Current Month
							hardWait();
							
						//======= Event To event  Looping
							List<WebElement> Event_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/client-event/div"));
							System.out.println("NUMBER OF Event Found = "+Event_collection.size());
							//--> Select The First AvailAble Event and Store It in a HasMap for This Entire Test case
							for(int i=0;i<=Event_collection.size()-1;i++)
							{
								System.out.println("Events Details : " + Event_collection.get(i).getText());
								//System.out.println("Events Details : " + Event_collection.get(i).findElement(By.xpath("//section[1]/h1")).getText());
								//System.out.println("Events Details : " + driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+i+"]/div/section[1]/h1")).getText());
								String allTextinEventTile = driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]")).getText();
							
								//&&&&&&&&&&&&&  Get a PRE-GAme Day Play Off Event &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
								if(Input.trim().matches(".*PreGamePlayOff.*"))
								{
									if(!allTextinEventTile.contains("GAME DAY PRICING") && allTextinEventTile.contains("KOSHER NOT AVAILABLE"))
									{
										String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
										Test_DriverThread.AllEmail.put(Input, StoreEventName);
										returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
										break Monthloop;
									}
								}
								
								
								//&&&&&&&&&&&&&  Get a PRE-GAme Day Regular &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
								if(Input.trim().matches(".*PreGame.*"))
								{
									if(!allTextinEventTile.contains("GAME DAY PRICING"))
									{
										String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
										Test_DriverThread.AllEmail.put(Input, StoreEventName);
										returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
										break Monthloop;
									}
								}


								//&&&&&&&&&&&&&  Get a PRE-GAme Day Regular &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
								if(Input.trim().matches(".*GameDay.*"))
								{
									if(allTextinEventTile.contains("GAME DAY PRICING"))
									{
										String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
										Test_DriverThread.AllEmail.put(Input, StoreEventName);
										returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
										break Monthloop;
									}
								}
								
								
								
								
							}// End Looping For Events 
						}//End Looping In Month List 
					} 
					catch (Exception e) 
					{
						//=== Error Problem Finding Event From Event List 
						e.printStackTrace();
					} 
				}
				
				else
				{
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
					//break;
				}

			*/}
			
			
			
		return returnString;
	}
	/*
	 	* DataBase Depended Functions 
	 	* Get the maximum Test email ID from Suites DB and make a new one 	 
	 */
 	//public String generateEmail(String eMailNo)
	/*{
		
		String returnString="";
		
		//***** Connect To DataBase Query All User 
		Connection conn3 = null;
		String dbURL3 = "";
       // Properties parameters = new Properties();

        if(FBConstants.ENV.equals("SUIT_UAT")) //Looking For Default QA/UAT/PRD URL
		{
	         dbURL3 = "jdbc:postgresql://suitessitestage.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_uat";
		     parameters.put("user", "pgsqlprd");
		     parameters.put("password", "d8LaHYTk86SD");
		}
		else if(FBConstants.ENV.equals("SUIT_QA"))
		{
	        dbURL3 = "jdbc:postgresql://suitessite.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_qa";
	        parameters.put("user", "pgsqldev");
	        parameters.put("password", "pgsqldev#10");
		}
        
        try 
        {
			//==Connecting to Database 
	        	conn3 = DriverManager.getConnection(dbURL3, parameters);
			    if (conn3 != null) 
			    {
			        System.out.println("\n Connected to database #" + dbURL3 + parameters);
			    }
			    
			//==Count Query IF It return Zero we will Return from This Function 
			    Statement Countstmt = conn3.createStatement();
				ResultSet Countrs=Countstmt.executeQuery("select Count(email) from users where email LIKE '" + Constants.emailAcctAlias +"%@mailinator.com'");
				int TotalRecord = 0 ;	
				while (Countrs.next())
				{
					 TotalRecord =  Integer.parseInt(Countrs.getString(1));
				}
				
				if(TotalRecord==0)
				{
					Test_DriverThread.AllEmail.put(eMailNo, Constants.emailAcctAlias+"1@mailinator.com");
					returnString = Constants.emailAcctAlias+"1@mailinator.com";
					return returnString; // ****** This Entire Function will return here ************ 
				}
				
			//==Select Query The Database User Table 
			    String Input = Constants.emailAcctAlias +"%@mailinator.com";
			    Statement stmt = conn3.createStatement();
				ResultSet rs=stmt.executeQuery("select email from users where email LIKE'"+Input.trim()+"'");
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				while (rs.next())
				{
					String email = rs.getString(1);	
	                System. out.println("Email Found in dataBase : " + email);
	                System. out.println("Only NUmber Part From Email : " + stripNonDigits(email));
	                arrayList.add(Integer.parseInt(stripNonDigits(email)) );
				}
				
				System.out.println("Max Number From All Email " + Collections.max(arrayList));
				int MaxNumber = Collections.max(arrayList)+1;
				if(Test_DriverThread.AllEmail.get(eMailNo).equals(""))
				{
					Test_DriverThread.AllEmail.put(eMailNo, Constants.emailAcctAlias + MaxNumber + "@mailinator.com");
					System.out.println("New Email Generated For USe : " + Test_DriverThread.AllEmail.get(eMailNo));
					returnString =Constants.emailAcctAlias + MaxNumber + "@mailinator.com";
				}
				else//==> If the HasMap(Key) Already Has a Email Return That one  
				{
					returnString =Test_DriverThread.AllEmail.get(eMailNo);
				}
			//== closing DB Connection		
				conn3.close();		
		} 
        catch (SQLException ex) 
        {
        	//***** Back-Up Code IF DB Connection Fail A Random NUmber Email Will Generated - No Gurantee Work
        	if(Test_DriverThread.AllEmail.get(eMailNo).equals(""))
			{
				Test_DriverThread.AllEmail.put(eMailNo, Constants.emailAcctAlias + RandomNumber() + "@mailinator.com");
				returnString = Test_DriverThread.AllEmail.get(eMailNo);          // Return the Data From HasMap
			}
			else
			{
				returnString = Test_DriverThread.AllEmail.get(eMailNo);          // Because data is already in Hasmap Return ii
			}
        }
        finally 
        {
            try 
            {
                if (conn3 != null && !conn3.isClosed()) 
                {
                    conn3.close();
                }
            } 
            catch (SQLException ex) 
            {
                //ex.printStackTrace();
            }
        } 
        
        return returnString;		
	}*/
 	
	
	//==== Highlight element 
	public void highlightElement(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(3000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }
	public void highlightElementBackground(WebElement element, String flag)
	{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        if(flag.equalsIgnoreCase("pass"))
        {
            //js.executeScript("arguments[0].style.border='1px groove green'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"yellow"+"'",  element);
        }
        else 
        {
            //js.executeScript("arguments[0].style.border='4px solid red'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"red"+"'",  element);
        }

        try 
        {
			Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
        }
                
        //===> Take a Screen Shot and Reset the Element 
        	//takeScreenShot();
        //js.executeScript("arguments[0].style.border=''", element);
        //js.executeScript("arguments[0].style.backgroundColor = '"+""+"'",  element);
	}
	public void highlightElementBorder(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(1000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }

	//=== Driver Wait & Special Screen Shot Function
	public void waitForPageToLoad() throws InterruptedException 
	{
		//wait(2);
		this.hardWaitFor(500);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String state = (String)js.executeScript("return document.readyState");
		while(!state.equals("complete"))
		{
			//wait(2);
			this.hardWaitFor(500);
			state = (String)js.executeScript("return document.readyState");
		}
	}	
	public void hardWait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void hardWaitFor(int Second)
	{
		try {
			Thread.sleep(Second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean waitUntilClickable(String ObjectName)
	{
		//************
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//*********
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectName)));
			return true;
		}
		catch(Exception e)
		{
			
    		PassFailFailDescription = thisMethodName + "-> For  :  " + ObjectName + " ->Failed" + "<br>";
    		PassFailFailDescription = e.getMessage() + "<br>";
		}

		return false;
	}
	
	public boolean waitUntilClickable(WebElement ObjectName)
	{
		//************
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//*********
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,1);
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			 wait.until(ExpectedConditions.elementToBeClickable(ObjectName));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectName)));
			return true;
		}
		catch(Exception e)
		{
    			PassFailFailDescription = thisMethodName + "-> For  :  " + ObjectName + " ->Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
		}

		return false;
	}
	public void SwitchFrameWait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void takeScreenShot()
	{/*
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		//String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			//FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ScenarioReport.log(LogStatus.INFO,ExtendTestReport.addScreenCapture(filePath));
	*/}
	public  void takeScreenShotForScenario()
	{/*
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		//String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture(filePath));
		ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture("screenshots/"+screenshotFile));
	*/}

	//Global List Variable That will store all the ScreenShot file name for Current Scenario is Running 
	public static ArrayList<String> PassFailScreenFileName = new ArrayList<String>();
	public  void takePassFailScreenShots(String currentScenario)
	{/*
		Date d=new Date();
		String screenshotFile=currentScenario+"-"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(scrFile, new File(filePath));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		PassFailScreenFileName.add(filePath);
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture(filePath));
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture("screenshots/"+screenshotFile));
	*/}
	

	
 	//******In Progress -- > Below Function Used For Debug Purpose Works Browser Mob Proxy Help:  http://www.swtestacademy.com/webdriver-browsermob-proxy/ *** 
	public boolean CheckdbConnection()
	{
	Connection conn3 = null;
	String dbURL3 = "";
    Properties parameters = new Properties();

   /* if(FBConstants.ENV.equals("SUIT_UAT")) //Looking For Default QA/UAT/PRD URL
	{
         dbURL3 = "jdbc:postgresql://suitessitestage.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_uat";
	     parameters.put("user", "pgsqlprd");
	     parameters.put("password", "d8LaHYTk86SD");
	}
	else if(FBConstants.ENV.equals("SUIT_QA"))
	{
        dbURL3 = "jdbc:postgresql://suitessite.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_qa";
        parameters.put("user", "pgsqldev");
        parameters.put("password", "pgsqldev#10");
	}
    */
    
    try 
    {
		//==Connecting to Database 
        	conn3 = DriverManager.getConnection(dbURL3, parameters);
		    if (conn3 != null) 
		    {
		        System.out.println("Connected to database #" + dbURL3 + parameters);
		    }
		
		//==Query The Database User Table And get the REset Token 
		    /*Statement stmt = conn3.createStatement();
			ResultSet rs=stmt.executeQuery("select reset_password_token from users where email ='"+Input.trim()+"'");
			while (rs.next())
			{
				resetToken = rs.getString(1);	
                System. out.println("Reset Token for this  User : " + resetToken);		
			}*/
			//String input = "some input string";
			//int hashCode = resetToken.hashCode();
			//System.out.println("input hash code = " + hashCode);
		
		//==Update Query     
			Statement stmt = conn3.createStatement();
			String mapToken = "7a0cafe277fc84b79cf4aeacb687d53fa57e72c0b86797b0d0619030b37fd20e";
			//int rs=stmt.executeUpdate("update users SET reset_password_token = null where email Like '"+Constants.emailAcctAlias+"%@mailinator.com'");
			
		    	//int rs2=stmt.executeUpdate("update users SET reset_password_token='"+mapToken+"' where email ='"+Input.trim()+"'");
		    }
		    catch(Exception e)
		    {
		    	System.out.println(e.getMessage());
		    	
		    }

    return true;
	}
	

	/*public void reportPassFail(ReportTest reporttest, String failedInMethod, String step, String Description)
	{
		if(reporttest.PASS.toString().equals(report.PASS))
		{
			
		}
		else
		{
	    	PassFailFailDescription = failedInMethod +  "  FAILED"  + "<br>";
			takePassFailScreenShots(step.replaceAll("\\s",""));
		}
	}*/
	
	
	//*********************************************************

		
}
