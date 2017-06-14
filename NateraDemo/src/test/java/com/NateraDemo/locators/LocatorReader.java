package com.NateraDemo.locators;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.NateraDemo.util.PropertyReader;

public class LocatorReader
{
	private Document doc;
	private  PropertyReader prop;  
	
	public LocatorReader(String xmlName)
	{
		SAXReader reader = new SAXReader();
		try 
		{
			prop = new PropertyReader();
			String localPath = prop.getFilePath();
			
			localPath = localPath+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"com"+File.separator+"NateraDemo"+File.separator+"locators"+File.separator;
			
			doc = reader.read(localPath+xmlName);
		} 
		catch (DocumentException e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getLocator(String locator)
	{
		return doc.selectSingleNode("//" + locator.replace('.', '/')).getText();
		
	}

}
