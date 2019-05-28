package SupportUtility;

public class printconsole 
{
	
	
	public static void printfailure(String sFailedInClass,String sFailedinMethod, String sFailReason, String sExpectedValue, String sActualValue)
	{
		System.out.println("***************Failed Reporting ************-**********************\n");
		System.out.println("Failed in \t:\t ["+sFailedInClass+ " -> " + sFailedinMethod +"]");
		System.out.println("Failed reason \t:\t "+sFailReason);

		//==Printing Expected Values 
		String[] sTempExpectedValueArray = sExpectedValue.split("\\|");
		for(int i=0;i<sTempExpectedValueArray.length;i++)
		{
			if(i>0) //Subsequent Print
			{
				System.out.print(" " + sTempExpectedValueArray[i]);
			}
			else //First Time Print 
			{
				System.out.print("Expected \t:\t" + sTempExpectedValueArray[i]);
			}
		}
		System.out.println("");
		
		
		//==Printing Actual Values 
		String[] sTempActualValueArray = sActualValue.split("\\|");
		for(int i=0;i<sTempActualValueArray.length;i++)
		{
			if(i>0) //Subsequent Print
			{
				System.out.print(" " + sTempActualValueArray[i]);
			}
			else //First Time Print 
			{
				System.out.print("Actual \t\t:\t" + sTempActualValueArray[i]);
			}
		}
		System.out.println("\n\n***************XX**********************************\n");
	}	

	
	
	
	public static void print(String stepDescription)
	{
		//System.out.println(stepDescription);
		if(stepDescription.toLowerCase().contains("step"))
		{
			System.out.println("\n" + stepDescription);  // For Step Print One Empty Line 
		}
		else 
		{
			System.out.println(stepDescription);
		}
	}
	

	
	/*
	 	*This Function will Print all Logs For Utility Like 
	 	*SELENIUM - RestAPI or anything else 
	 */
	public static void printutilitylog(String log)
	{
		if(log.toLowerCase().contains("selenium"))
		{
			//System.out.println(log);  //  To Do   Format SELENIUM in Action Text 
		}
		else if(log.toLowerCase().contains("api"))  
		{
			System.out.println(log);  //  To Do   Format API Call Action Text 
		}
	}


	/*
 		*This Function will Print all Debug Log
 		*SELENIUM - RestAPI or anything else we can switch off to not to show debug log 
 	*/
	public static void printdebuglog(String log)
	{
			//System.out.println("debug log : " + log);  // For Step Print One Empty Line 
	}



}
