package SupportReporting;

import static org.junit.Assert.assertEquals;

public class Reporter 
{
	
	public static void Reportfail(String Failreason, boolean Expected, boolean Actual)
	{
		assertEquals("Failed : " + Failreason + " ? ",Expected,Actual);
	}

}
