package utilityScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile 
{
		
	public String getPropertyValue(String Key) throws IOException 
	{
		Properties obj = new Properties();	
		String propertyFilePath = System.getProperty("user.dir")+"\\trackit.properties";
		FileInputStream objfile = new FileInputStream(propertyFilePath);									
		obj.load(objfile);
		String value = obj.getProperty(Key);
		return value;
	}

}
