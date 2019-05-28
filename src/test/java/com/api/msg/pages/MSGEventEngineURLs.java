package com.api.msg.pages;

import java.util.Hashtable;

public class MSGEventEngineURLs 
{
	public static  String ENV=""; //SUIT_QA  or SUIT_UAT
	
	//*************  E V E N T  - Event Engine URLS 		
	public static final String EventEngine_DEV_URL = "http://dev-api.msg.com/login/";
	public static final String EventEngine_QA_URL = "http://qa-api.msg.com/login/";
	public static final String EventEngine_Staging_URL = "http://staging-api.msg.com/login/";

	//public static final String EventEngine_Staging_URL = "http://google.com";

	public static final String EventEngine_Prod_URL = "http://api.msg.com/login/";

	//*************  E V E N T  - Event Engine Default ID/Password 		
	public static final String EE_SUPERADMIN = "superadmin@msg.com";
	public static final String EE_SUPERADMINPASSWORD = "LeT6ctzqy";

	
	public static final String EE_ADMINUSER = "admin@msg.com";
	public static final String EE_ADMINPASSWORD = "LeT6ctzqy";

	
	
	
	//*************  E V E N T  - Event Engine URLS 		
	public static final String EventEngine_DEV_API = "http://dev-api.msg.com/v2";
	public static final String EventEngine_QA_API = "http://qa-api.msg.com/v2";
	public static final String EventEngine_Staging_API = "https://staging-api.msg.com/v2";
	public static final String EventEngine_Prod_API = "https://staging-api.msg.com/v2";
	
	//public static String RuntimeEventID = "";

	
	public static Hashtable<String,String> table;
	
	public static Hashtable<String,String> getEnvDetails()
	{
		if(table==null)
		{
			table = new Hashtable<String,String>();
			
			//****** EE URLS 
			if(ENV.equals("EventEngine_DEV"))
			{
				table.put("URL", EventEngine_DEV_URL);
				table.put("APIBASEURL", EventEngine_DEV_API);
			}
			else if(ENV.equals("EventEngine_QA"))
			{
				table.put("URL", EventEngine_QA_URL);
				table.put("APIBASEURL", EventEngine_QA_API);
			}
			else if(ENV.equals("EventEngine_Staging"))
			{
				table.put("URL", EventEngine_Staging_URL);
				table.put("APIBASEURL", EventEngine_Staging_API);
			}
			else if(ENV.equals("EventEngine_Prod"))
			{
				table.put("URL", EventEngine_Prod_URL);
				table.put("APIBASEURL", EventEngine_Prod_API);
			}
			
			
			//===>> All Env ( DEV-QA-Stage SHould have same ID Password Please 
			table.put("EE_SUPERADMIN", EE_SUPERADMIN);
			table.put("EE_SUPERADMINPASSWORD", EE_SUPERADMINPASSWORD);
			
			table.put("EE_ADMINUSER", EE_ADMINUSER);
			table.put("EE_ADMINPASSWORD", EE_ADMINPASSWORD);

		}
		return table;
	}
}
