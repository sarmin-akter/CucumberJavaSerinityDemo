package supportMethods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SauceJobUpdateStatus {
	private static Map<String, Object> myUpdates;

	public static void main(String args[]) throws IOException 
	{
		SauceJobAccessController mySauce = new SauceJobAccessController("rachit", "05d919ba-84e4-49fb-b84c-69f8af1918bf");
		myUpdates = new HashMap<String, Object>();
		Object myobject = "true";
		String myKey = "Passed";
		myUpdates.put("passed", false);
		mySauce.updateJobInfo("58918a9aefd0448b89ecc2e66d3d0a9a", myUpdates);
	}

}
