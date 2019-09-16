package edu.usal.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil
{
	private static Properties prop;
	
	static
	{
		prop = new Properties();
		try
		{
			prop.load(new FileReader("config.properties"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	

	
	public static String getPropertyUrl()
	{
		return prop.getProperty("url");
	}
	
	public static String getPropertyUser()
	{
		return prop.getProperty("user");
	}
	
	public static String getPropertyPass()
	{
		return prop.getProperty("pass");
	}
	public static String getPropertyDriver()
	{
		return prop.getProperty("driver");
	}
	
		public static String getPropertyAlianza()
	{
		return prop.getProperty("pathAlianzas");
	}
	
}
