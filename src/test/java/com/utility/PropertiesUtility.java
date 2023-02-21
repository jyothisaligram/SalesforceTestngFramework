package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static com.utility.Constants.USER_DIR;
import static com.utility.Constants.APPLICATION_PROPERTIES;

public class PropertiesUtility {
	public String getProperty(String key) throws IOException {
		String spath = USER_DIR+APPLICATION_PROPERTIES;
		FileInputStream fileput;
		fileput = new FileInputStream(spath);
		Properties prop = new Properties();
		prop.load(fileput);
		String value = prop.getProperty(key);
		return value;
	}
	
}	
	
	/*protected FileInputStream stream= null;
	protected Properties propertyFile = null;
	
	
	
	//This method is for loading the property file 
	public Properties loadFile(String filename){
		Properties propertyFile = new Properties();
		String PropertyFilePath = null;
		
		switch(filename) {
		case "applicationDataProperties":
			PropertyFilePath = Constants.APPLICATION_PROPERTIES;
		      break;
		case " ":
			 System.out.println("No FILENAME FOUND");
			 break;
		
		}
		
		try {
			stream = new FileInputStream(PropertyFilePath);
			propertyFile.load(stream);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return propertyFile;
		
   }
	
	
	// This method will fetch the key value from the property file
	 public String getPropertyValue(String Key) {
		
		 String value = propertyFile.getProperty(Key);
		 System.out.println("property we got from the file is "+value);
		try {
			stream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return value;
	}*/


