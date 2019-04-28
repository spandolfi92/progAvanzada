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
	
	

	public static String getPropertyCliente()
	{
		return prop.getProperty("pathCliente");
	}
	
	

}
