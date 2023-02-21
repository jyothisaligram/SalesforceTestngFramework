package com.base;

import java.io.File;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.salesforcetest.salesforceTestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import static com.utility.Constants.SCREENSHOT_PATH;;



public class BaseTest {

	protected static WebDriver driver = null;
	protected static Logger logger = LogManager.getLogger(salesforceTestCase.class.getName());	
//	public static ExtentReports extent = new ExtentReports();
	public static ExtentReports extent = null;
	
	
	
	/* name of the method:   entertext
	 * BriefDescription  :   entering textvalue for textbox
	 * Arguments         :  element -->object
	 *                      text--->to be entered 
	 *                      name--->object name
	 */
	
	public static void enterText(WebElement element,String text,String name){
		if(element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
		}
		else {
			System.out.println( name+ "web element is not displayed");
		}
		driver.getTitle();
	}
	
	
	
	
	public static void teardown() {
		driver.close();
	}
	
	
	public static void goToUrl(String url) {
		driver.get(url);
	}
	
	
	/* name of the method:   clickonme--->Button
	 * BriefDescription  :   clicking a button
	 * Arguments         :  element-->object, name --->object name
	 */
	
	public static void clickonme(WebElement element, String name) {
		if(element.isDisplayed()) {
			element.click();
		}
		else
		{
			System.out.println("Unable to click because web element is not displayed"+name);
		}
	}
	
	/*
	 * Name of the method: selectDropdown
	 * Brief Description: Select the Dropdown list
	 * Arguments: obj --> web object, objName--> name of the object
	 */
	
	public static void selectDropdown(WebElement obj, String objName) {
		
		if(obj.isDisplayed()) {
			System.out.println("Pass: "+objName+" is  selected");
			obj.click();
		}else
		{
		
			System.out.println("Fail:"+objName+" is not present.Please chk application");	
	
	    }
	}
	
	/*
	 * Name of the method: validateErrormsg
	 * Brief Description: to validate the error msg 
	 * Arguments: actualmsg --> name of the object, errormsg--> name of the object
	 */
public static void validateErrormsg(String actualmsg, String errormsg) {
		
		if(actualmsg.equals(errormsg))
		{
			System.out.println("TestCase is passed");
		
		}else
	    {
		System.out.println("TestCase is failed");

	    }
	}
	
	
/* name of the method:   mouseOver
 * BriefDescription  :   mouseOver  
 * Arguments         :  obj,index 
 * */
public static void mouseOver(WebDriver driver,WebElement obj) {
	if(obj.isDisplayed()) {
   Actions action=new Actions(driver);
   action.moveToElement(obj).build().perform();
	System.out.println("Pass: "+obj+" is present");

	}
 else {
		System.out.println("Fail:"+obj+" is not present.Please chk application");

	}
}
	

/* name of the method:   selectbyText
 * BriefDescription  :   selected by clicking the dropdown 
 * Arguments         :  obj,objName */

public static void SelectbyText(WebElement we, String VisibleText){
	   if(we.isDisplayed())
	   {
		   Select selObj=new Select(we);
        selObj.selectByVisibleText(VisibleText);
	           System.out.println("Pass: "+VisibleText+ " is Selected by VisibleText" );

	           
	   } 
	   else
	   {
	    System.out.println("Fail: "+VisibleText+ " is not available");

	    
	   }
	   
}
	
/* name of the method:   switchtoAlert
 * BriefDescription  :   Switch to alert
 * Arguments         :  driver*/
	
public static void switchtoAlert(WebDriver driver) {
	 driver.switchTo().alert().accept();
	 System.out.println("Pass: alert is present and accept");
}

	
/*
 * Name of the method: Radiobutton
 * Brief Description: To click on the radio button 
 * Arguments: obj --> web object, objName--> name of the object*/
public static void Radiobutton(WebElement obj, String objName) {
	
	if(obj.isDisplayed() ){
		obj.click();
		System.out.println("Pass: "+objName+" is clicked");
	}else {
		System.out.println("Fail:"+objName+" is not displayed .Please check your application");			
	}
}

	
	public static void waitforVisibilty(int time, WebElement element)
	{
		
		WebDriverWait wait = new WebDriverWait(driver,time);
	    wait.pollingEvery(2,TimeUnit.SECONDS);
	    wait.ignoring(NoSuchElementException.class);
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public static void waitforMe(int time) {
		WebDriverWait wait = new WebDriverWait(driver,time);
		
		
  }
	
	/* Name of the method: : moveToElementAction
	 * Description : to move the cursor to webelement
	 * Arguments : ele--webelement , objName ---name of the object
	 */
	
	public static void moveTOElementAction(WebElement ele, String objName) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element "+objName);
	}
			
	/*Method name: GetDriverInstance
	 * Arguments --browserName: name of the object
	 * 
	 */
	
	
	public static void GetDriverInstance(String browserName) {
		
		switch(browserName) {
		case "firefox":		WebDriverManager.firefoxdriver().setup();
							driver=new FirefoxDriver();
							driver.manage().window().maximize();
							break;
							
		case "chrome":  
			
			//ChromeOptions option=new ChromeOptions();
			//option.addArguments("--headless");
			//option.addArguments("--incognito");
			//option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					
			            WebDriverManager.chromedriver().setup();
						driver=new ChromeDriver();
						driver.manage().window().maximize();
						break;
						
		default: System.out.println("not entered proper browsername");
		}
		
	}
	
	
public void takescreenshot(WebDriver driver) {
		//Convert web driver object to TakeScreenshot

   TakesScreenshot screenshot = ((TakesScreenshot)driver);
		
		 //Call getScreenshotAs method to create image file
	File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		Date currentDate = new Date();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(currentDate);
		
//String curDir = System.getProperty("user.dir");
String filepath = SCREENSHOT_PATH+"Salesforce"+timeStamp+".png";
System.out.println("---------->"+filepath);
//Move image file to new destination

File destFile = new File(filepath);
//Copy file at destination
try {
	FileUtils.copyFile(srcFile, destFile);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
     }
  }	
	
	
}
