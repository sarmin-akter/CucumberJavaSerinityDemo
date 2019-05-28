package com.api.msg.pages;

import SupportUtility.printconsole;
import SupportWebserviceAPI.Restassured;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EE_Z_API extends Restassured
{
	
	public String EE_Base_APIURL;


	public EE_Z_API()
	{
		 EE_Base_APIURL = MSGEventEngineURLs.getEnvDetails().get("APIBASEURL");
	}
	
	public boolean Get_EventByEventID_And_Verify(String EventID,String AttributeName, String validateText)
	{
		
		RestAssured.baseURI = EE_Base_APIURL+"/events";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, EventID);

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Event/"+EventID+" Response Body Text =>  " + responseBody);
		
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		//String city = jsonPathEvaluator.get("City");
		
		
		//String artists = jsonPathEvaluator.get("results[0].artists").toString();
		String AttributeValue = jsonPathEvaluator.get("results[0]."+AttributeName).toString();



		// Let us print the city variable to see what we got
		//System.out.println("City received from Response " + city);
		System.out.println("Attribute to Verify =>> "+AttributeName +":" + AttributeValue);


		// Validate the response
		//Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
		//Assert.assertEquals(artists, "Hyderabad", "Correct city name received in the Response");
		
		if(AttributeValue.toLowerCase().contains(validateText.toLowerCase()))
		{
			return true;	
		}
		
		return false;
	}
	
	
	public String Get_Response_AttributeValue(String EventID,String AttributeName, String validateText)
	{

		//************ All Utility Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.printutilitylog("\nAPI CALL : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
		
		RestAssured.baseURI = EE_Base_APIURL+"/events";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, EventID);

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		printconsole.printutilitylog("API\t : Event/"+EventID+" Response Body Text =>  " + responseBody);
		
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		//String city = jsonPathEvaluator.get("City");
		
		
		//String artists = jsonPathEvaluator.get("results[0].artists").toString();
		String AttributeValue = jsonPathEvaluator.get("results[0]."+AttributeName).toString();

		// Let us print the city variable to see what we got
		//System.out.println("City received from Response " + city);
		printconsole.printutilitylog("API\t : Attribute to Verify =>> "+AttributeName +":" + AttributeValue);

		return AttributeValue;
		
		// Validate the response
		//Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
		//Assert.assertEquals(artists, "Hyderabad", "Correct city name received in the Response");
		
		/*if(AttributeValue.toLowerCase().contains(validateText.toLowerCase()))
		{
			return true;	
		}
		
		return false;*/
	}
	
	
	
	
}
