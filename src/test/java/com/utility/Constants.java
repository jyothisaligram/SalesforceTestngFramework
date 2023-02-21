package com.utility;

import static com.utility.Constants.USER_DIR;

import java.io.File;

public class Constants {
  public static final String USER_DIR = System.getProperty("user.dir");
  public static final String APPLICATION_PROPERTIES="/src/test/resources/testData.properties";
  public static final String SCREENSHOT_PATH = USER_DIR+"\\ScreenShots\\";
  public static final String REPORTPATH = USER_DIR+"\\src\\test\\java\\reports\\sfdc.html";
  public static final String SALESFORCE = "https://login.salesforce.com";
  public static final String PROFILE_PHOTO = USER_DIR+"\\src\\test\\resources\\jyothi2018.jpg";
  public static final String FILE_UPLOAD = USER_DIR+"\\src\\test\\resources\\MasalaDose.jpg";
  
}
