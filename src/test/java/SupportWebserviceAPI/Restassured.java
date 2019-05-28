package SupportWebserviceAPI;


//import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;



public class Restassured {
	
	//@Test
	public void GetWeatherDetails()
	{   
		// Specify the base URL to the RESTful web service
		//RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RestAssured.baseURI = "http://staging-api.msg.com/v2/events";

		


		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/3B005352962A17D0");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		//String city = jsonPathEvaluator.get("City");
		String artists = jsonPathEvaluator.get("results[0].artists").toString();


		// Let us print the city variable to see what we got
		//System.out.println("City received from Response " + city);
		System.out.println("Event Artist received from Response " + artists);


		// Validate the response
		//Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
		//Assert.assertEquals(artists, "Hyderabad", "Correct city name received in the Response");
	}

	
	/*@Test
	public void GetWeatherDetails_Malinator_UrlConnection_AndRachitHTTP()
	{   

        try 
        {
			//URLConnection connection = new URL("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").openConnection();
			//URLConnection connection = new URL("http://qa-api.msg.com/v2/events/3C0052D2FE330E9C").openConnection();
			//URLConnection connection = new URL("https://api.msg.com/v2/venues/").openConnection();

			DefaultHttpClient myDefaultHttpClient = new DefaultHttpClient();
			HttpGet myHttpGetRequest = new HttpGet("https://api.msg.com/v2/venues/");
			myHttpGetRequest.addHeader("accept", "application/json");
			HttpResponse myHttpResponse = myDefaultHttpClient.execute(myHttpGetRequest);
			BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader((myHttpResponse.getEntity().getContent())));

			
			
	        //InputStream response = connection.getInputStream();
	        //Reader reader = new InputStreamReader(response);
	        
			JSONParser myJSONParser = new JSONParser();
			JSONObject myJSONObject = null;
			try 
			{
				myJSONObject = (JSONObject) myJSONParser.parse(myBufferedReader);
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			
			String myJSONLine = myJSONObject.toJSONString();
			JsonElement myJSONElement = new JsonParser().parse(myJSONLine);

			JsonObject myJSONHeadObject = myJSONElement.getAsJsonObject();
			myJSONHeadObject = myJSONHeadObject.getAsJsonObject("results");
			JsonObject myJSONPromoCodes = myJSONHeadObject.getAsJsonObject("promo_codes");

			for (Entry<String, JsonElement> myUniquePromoCode : myJSONPromoCodes.entrySet())	
			{
				System.out.println(myUniquePromoCode.getKey());
			}
				
				//myPromoCodeList.add(myUniquePromoCode.getKey());
				//myDefaultHttpClient.getConnectionManager().shutdown();
				//return myVenueNames;

	        
	        
	        
	        
	        
	        //ArrayList<InboxMessage> messages = new ArrayList<>();
	        JSONObject obj = (JSONObject) JSONValue.parse(reader);
	        JSONArray jsonMessages = (JSONArray) obj.get("results/artists"); //City array    {"Temperature":"15 Degree celsius","WindDirectionDegree":"140 Degree","Humidity":"82 Percent","WeatherDescription":"mist","WindSpeed":"1.5 Km per hour","City":"Hyderabad"}
	  
	        if(jsonMessages != null) 
	        {
	          //Parse the messages, make the POJOs and add them to our custom list.
	          for (Object jsonMsg : jsonMessages) 
	          {
	              //InboxMessage message = createInboxMessageFrom((JSONObject) jsonMsg);
	              //messages.add(message);
	        	  		System.out.println(jsonMsg);
	          }
	  
	          //The messages come in the response in a reversed order, so lets give 
	          //back an ordered list
	          //Collections.reverse(messages);
	        }
	        
		} 
        catch (MalformedURLException e) 
        {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	
	
	
	
	
	public static void main(String[] args) 
	{
		/*// Specify the base URL to the RESTful web service
		RestAssured myrestAssured = new RestAssured();
		myrestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);*/

	}

}
