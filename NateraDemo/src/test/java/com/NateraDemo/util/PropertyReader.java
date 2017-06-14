package com.NateraDemo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader
{
	public String readApplicationFile(String key) throws Exception
	{
		String value="";
		try
		{
			Properties prop = new Properties();
			File file =new File(getFilePath()+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"com"+File.separator+"NateraDemo"+File.separator+"config"+File.separator+"Application.properties");

			if(file.exists())
			{
				prop.load(new FileInputStream(file));
				value = prop.getProperty(key);
				if(value==null)
				{

					throw new NullPointerException("Given key is not found or equal to null in the properties file...");
				}
			}
			else
			{
				throw new FileNotFoundException("File not found...");
			}
		}
		catch(NullPointerException e)
		{
			throw e;
		}
		catch(Exception e)
		{  
			System.out.println("Failed to read from application.properties file...");
			throw e;
		}
		return value;
	} 

	public String getFilePath()
	{
		String filepath = "";
		final File file = new File("");
		final String absolutePathOfFirstFile = file.getAbsolutePath();
		filepath = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return filepath;
	}
}
