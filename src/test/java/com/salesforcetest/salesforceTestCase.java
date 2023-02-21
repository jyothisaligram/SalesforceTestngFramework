package com.salesforcetest;

import static com.utility.Constants.REPORTPATH;
import static com.utility.Constants.SALESFORCE;
import static com.utility.Constants.PROFILE_PHOTO;
import static com.utility.Constants.FILE_UPLOAD;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.base.BaseTest;

import com.utility.PropertiesUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class salesforceTestCase extends BaseTest{
	//static WebDriver driver;
public static ExtentReports extent = null;
private static Logger logger = LogManager.getLogger(salesforceTestCase.class.getName());	
	
	
	/** this @BeforeMethod annotation will be executed before each test case **/
	@BeforeMethod 
	public void setUp()
	 {
		String reportPath = REPORTPATH;	
	    extent = new ExtentReports();
		System.out.println(reportPath);
		ExtentSparkReporter sparkHtml= new ExtentSparkReporter(reportPath);
		extent.attachReporter(sparkHtml);
		
		extent.setSystemInfo("Host name", "salesforce");
		extent.setSystemInfo("username", "Jyothi");
		sparkHtml.config().setDocumentTitle("Test execution report");
		sparkHtml.config().setReportName("Salesforce regression tests");
		sparkHtml.config().setTheme(Theme.STANDARD);
		
		String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		sparkHtml.config().setTimeStampFormat(timeStamp1);
		
		
		logger.info("Navigating to the url");
		  GetDriverInstance("chrome");
		  goToUrl(SALESFORCE);
		  driver.manage().window().maximize();
		 logger.info("URL opened successfully");
		  
		  
	 }
	
	/** this @AfterMethod annotation will be executed after each test case **/
	@AfterMethod
	public void tearDown()
	{
		//takescreenshot(driver);
		logger.info("driver closed");
		extent.flush();
		driver.close();
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() //verify the title of salesforce application
	{
		logger.info("start of test case salesforce TC_1");
		String title = driver.getTitle();
		System.out.println("The page title is: "+title);
		Assert.assertEquals(title, "Login | Salesforce");
		logger.info("end of test case verifyPageTitleTest");
	}
		
/*********************************************************************/
	public void loginSalesforceSuccess() throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("loginToSalesforceSuccess");
		System.out.println();
		logger.info("loginToSalesforce is successful");
		test.info("Event test test report for loginsalesforce successful login");
		
		PropertiesUtility propertiesUtility = new PropertiesUtility();
        String username = propertiesUtility.getProperty("login.valid.userid");
        String password = propertiesUtility.getProperty("login.valid.password");
        Thread.sleep(5000);
       
    	
    WebElement userName = driver.findElement(By.xpath("//input[@id='username']"));
    
    // waitforVisibilty(30, userName);
  //  Thread.sleep(3000);

       enterText(userName, username , "usernameEle");
       Thread.sleep(2000); 
       test.generateLog(Status.INFO, "username");
       test.generateLog(Status.PASS, "username");
       
      WebElement passWord = driver.findElement(By.id("password"));
      waitforVisibilty(30, passWord);
      passWord.clear();
      enterText(passWord, password , "passwordEle");
      
     test.generateLog(Status.INFO, "password");
  	test.generateLog(Status.PASS, "password");
     //  Thread.sleep(2000);
    	
      WebElement remember = driver.findElement(By.id("rememberUn"));
      waitforVisibilty(50, remember);
      remember.click();
      
      WebElement buttonclick = driver.findElement(By.id("Login"));
      String name = "buttonElement";
      clickonme(buttonclick,name);
      test.generateLog(Status.INFO, "buttonclick");
  	test.generateLog(Status.PASS, "buttonclick"); 
      
      String actualTitle = driver.getTitle();
  	driver.manage().window().maximize();
  	String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
  	if(actualTitle.equalsIgnoreCase(expectedTitle)) {
  		System.out.println("User is on home page");
  		test.generateLog(Status.INFO, "homepage launched");
    	test.generateLog(Status.PASS, "homepage launched");
  	}
  	else {
  		System.out.println("home page is not Lunched");
  		test.generateLog(Status.INFO, "homepage not launched");
    	test.generateLog(Status.PASS, "home page not launched");
  	}
       
    }
    
 /****************************************************************************/
    
	
	
	
	
	
	@Test(priority=2 ,description = "Salesforce login page TC_1")
	public void TC_1_loginToSalesforce() throws InterruptedException, IOException {
        
		ExtentTest test1 = extent.createTest("TC_1_loginToSalesforce");
		System.out.println();
		logger.info("Starting the test case salesforce TC_1_loginToSalesforce");
		
		test1.info("Event test test report for loginsalesforce");
		
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		String username = propertiesUtility.getProperty("login.valid.userid");
			
		Thread.sleep(5000);
					
		WebElement userName = driver.findElement(By.xpath("//input[@id='username']"));
		waitforVisibilty(10, userName);
		Thread.sleep(3000);
		logger.info("username entered ");
		
	    enterText(userName, username , "usernameEle");
	    logger.info("username entered in username field"+username);
	    Thread.sleep(2000); 
	    test1.log(Status.INFO, "username entered");
	    test1.log(Status.PASS, "username is entered");
	    
	    WebElement password = driver.findElement(By.id("password"));
		password.clear();
		Thread.sleep(2000);
		test1.log(Status.INFO, "password entered");
		test1.log(Status.PASS, "password entered");
		logger.info("password entered " +password);
		
		 WebElement buttonclick = driver.findElement(By.id("Login"));
		 String name = "buttonElement";
		 clickonme(buttonclick,name);
		
	 String errormessage = driver.findElement(By.xpath("//div[@id='error']")).getText();
	logger.error("errormessage");
	
	
	 if (errormessage.equals("Please enter your password.")) {
		System.out.println("Error message is been displayed");
		} else {
				System.out.println("Error message is not been be displayed");
	 }
	
		System.out.println("TC_1 Login Error Message completed");
		
		logger.info("Test case end");
}
	
/*******************************************************************************/
	
    @Test(description = "Salesforce login page TC_2")
	public void TC_2_Errorloginsalesforce() throws InterruptedException {
    	ExtentTest test2 = extent.createTest("TC_2_Errorloginsalesforce");
		System.out.println();
		logger.info("Starting the test case salesforce TC_2");
		
		test2.info("Event test test report for Errorloginsalesforce");
		
		WebElement username = driver.findElement(By.id("username"));
		String attriValue = username.getAttribute("class");
		System.out.println("attribute of the class = " + attriValue);
		String name = username.getTagName();
		System.out.println("tag name for the username field " + name);

		username.sendKeys("username@ta.com");
		driver.findElement(By.id("password")).sendKeys("");
		Thread.sleep(2000);
		 driver.findElement(By.id("Login")).click();
		 Thread.sleep(8000);
		 
		 String actual = driver.findElement(By.id("error")).getText();
		 String expected = "Please enter your password.";
		 
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Error_login_salesforece script passed");
		} else {
			System.out.println("Error_login_salesforece script failed");
		}
		Assert.assertEquals(actual,expected);
	  }
/**
 * @throws InterruptedException 
 * @throws IOException ***************************************************************/
 
    
    
    @Test(description = "Salesforce login page TC_3")  

  public void TC_3_rememberMe() throws InterruptedException, IOException {
    
    	ExtentTest test3 = extent.createTest("TC_3_rememberMe");
		System.out.println();
		logger.info("Starting the test case salesforce TC_3");
		test3.info("Event test test report for loginsalesforce");
   
		loginSalesforceSuccess();
     
	WebElement UserMenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
    waitforVisibilty(30, UserMenu); 
	selectDropdown(UserMenu, "UserMenu");
	//Thread.sleep(3000);
	
	WebElement logoutButton =  driver.findElement(By.xpath("//a[text() = 'Logout']"));
	waitforVisibilty(500, logoutButton);
	clickonme(logoutButton, "logout");
	
	Thread.sleep(5000);	
	
	
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
	WebElement idLoc = driver.findElement(By.xpath("//span[@id='idcard-identity']"));
	waitforVisibilty(30, idLoc);
	String actualTitle = idLoc.getText();
	String expectedName = "jan23.jyothi@ta.com";
	if(actualTitle.equalsIgnoreCase(expectedName))
		System.out.println("Username is displayed");
	else
		System.out.println("Username is not displayed");
	
	 if(actualTitle.equalsIgnoreCase(expectedName)) {
		 System.out.println("The script test for rememberme passed");
	 }
	 else {
		 System.out.println("The script test failed");
	 }
	 Assert.assertEquals(actualTitle, expectedName);
   }
/**
 * @throws InterruptedException 
 * @throws IOException ****************************************************************/
   
    @Test(description = "Salesforce login Page TC_4A")
    public void TC_4A_switchToForgotPassword() throws InterruptedException, IOException {
    	
    	ExtentTest test4 = extent.createTest("TC_3_rememberMe");
		System.out.println();
		logger.info("Starting the test case salesforce TC_4");
		test4.info("Event test test report for loginsalesforce");
    	
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		String username = propertiesUtility.getProperty("login.valid.userid");
    	By idLoc = By.id("username");
    	
    	
    	
		WebElement userName = driver.findElement(idLoc);
		enterText(userName,username, "usernameEle");
		test4.generateLog(Status.INFO, "username");
		test4.generateLog(Status.PASS, "userName");
	    Thread.sleep(2000); 
		
        WebElement forgotpassword = driver.findElement(By.linkText("Forgot Your Password?"));
        forgotpassword.click();
        
        WebElement forgotMyPassword = driver.findElement(By.cssSelector("#un"));
		waitforVisibilty(1000,forgotMyPassword);
		enterText(forgotMyPassword,username,"usernameelement");
		test4.generateLog(Status.INFO, "forgotMyPassword");
		
		WebElement continuebutton = driver.findElement(By.xpath("//input[@id='continue']"));
	    continuebutton.click();
	    
	    WebElement headerLine = driver.findElement(By.xpath("//h1[@id='header']"));
		String expectedHeader = "Check Your Email";
		String actualHeader = headerLine.getText();
		if(actualHeader.equalsIgnoreCase(expectedHeader)) {
		System.out.println("Test script for forgot your password PASSED");	
		}
		else {
			System.out.println("Test script for forgot your password Failed");
		}
		
		System.out.println("TC_4A switchToForgotPassword completed");  	   
    	
    	}
    
    
    /**
     * @throws IOException 
     * @throws InterruptedException ***************************************************************/
    @Test
    
    public void TC_4B_validateinvalidUsernameandPassword() throws InterruptedException, IOException {
  
 ExtentTest test4b = extent.createTest("TC_4B_validateinvalidUsernameandPassword");
System.out.println();
logger.info("Starting the test case salesforce TC_4");
test4b.info("Event test test report for loginsalesforce");    	
    	
    	
PropertiesUtility propertiesUtility = new PropertiesUtility();
String username = propertiesUtility.getProperty("login.invalid.userid");
String password = propertiesUtility.getProperty("login.invalid.password");
 By idLoc = By.id("username");
WebElement userName = driver.findElement(idLoc);
enterText(userName,username, "usernameEle");
test4b.generateLog(Status.INFO, "userName entered");

logger.info("username entered");
Thread.sleep(2000); 
		
WebElement passWord = driver.findElement(By.id("password"));
passWord.clear();
enterText(passWord,password, "passwordEle");
logger.info("Password entered");
test4b.generateLog(Status.INFO, "passWord entered");
test4b.generateLog(Status.PASS, "passWord pass");

WebElement buttonclick = driver.findElement(By.id("Login"));
String name = "buttonElement";
clickonme(buttonclick,name);
logger.info("login button clicked");
test4b.generateLog(Status.INFO, "buttonclick entered");
test4b.generateLog(Status.PASS, "buttonclick pass");


WebElement loginerrorMessage = driver.findElement(By.xpath("//div[@id='error']"));
//div[@id='error']

String expectederrormsg = "Please check your username and password. "
		+ "If you still can't log in, contact"
		+ " your Salesforce administrator.";

test4b.generateLog(Status.INFO, "expectederror message is displayed");

String actualerrormsg = loginerrorMessage.getText();
if (actualerrormsg.equalsIgnoreCase(expectederrormsg)) {
	System.out.println("Test Script for login error message passed");
	test4b.generateLog(Status.INFO, "login success");
	test4b.generateLog(Status.PASS, "login success");
	
}
else {
	System.out.println("Test Script for login error message Failed");
	test4b.generateLog(Status.INFO, "login failure");
	test4b.generateLog(Status.FAIL, "login failure");
}
  	
System.out.println("TC_4B validateinvalidusernameandpassword completed");  	   	
    }
    
  /**
 * @throws IOException 
 * @throws InterruptedException *******************************************************************/
    @Test
    
    public void TC_5_Selectusermenudropdown () throws InterruptedException, IOException {
    	
    	loginSalesforceSuccess();
    	
    	ExtentTest test5 = extent.createTest("TC_5_Selectusermenudropdown");
		System.out.println();
		logger.info("Starting the test case TC_5_Selectusermenudropdown");
		
		test5.info("Event test test report for TC_5_Selectusermenudropdown");
		
    	
    	WebElement UserMenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
		UserMenu.click();

		waitforVisibilty(100, UserMenu);
		String dropdownverify=driver.findElement(By.xpath("//div[@id='userNav-menuItems']")).getText();
		test5.info("user menu dropdown selected");
		System.out.println(dropdownverify);
		logger.info("dropdownverify complete");
		test5.info(dropdownverify);
		
		System.out.println("TC_5 Check RemeberMe completed");  	
    	
     }
    
/**
 * @throws IOException 
 * @throws InterruptedException *******************************************************************/
    @Test
    
    public void TC_6_SelectMyProfile () throws InterruptedException, IOException {
    	
loginSalesforceSuccess();
    	
WebElement UserMenu = driver.findElement(By.xpath("//div[@id='userNav']"));
selectDropdown(UserMenu, "UserMenu");
WebElement MyProfile = driver.findElement(By.xpath("//a[@title='My Profile']"));
clickonme(MyProfile, "MyProfile");
WebDriverWait w = new WebDriverWait(driver, 60);
w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]")));

		// Edit Profile link
WebElement Editprofile = driver
				.findElement(By.xpath("//a[contains(@class,'contactInfoLaunch editLink')]//img"));
	clickonme(Editprofile, "Editlink");
	
WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='contactInfoContentId']"));
System.out.println("I am inside frame");
driver.switchTo().frame(frame1);
	
 WebElement aboutTab = driver.findElement(By.cssSelector("#aboutTab"));
 aboutTab.click();
 WebElement newLastname = driver.findElement(By.xpath("//input[@id='lastName']"));
 newLastname.clear();
 newLastname.sendKeys("SA");
 System.out.println("LastName is Entered");
 
        	
WebElement savebutton = driver.findElement(By.xpath("//input[@value='Save All']"));
   Thread.sleep(4000);

  savebutton.click();	

// edit post link----------------------------------------------------
  
  WebElement postele = driver.findElement(By.xpath("//span[text()='Post']"));
  waitforVisibilty(60, postele);
	postele.click();
	//iframe[contains(@title ,'Rich Text Editor, publisherRichTextEditor')]
	
WebElement postframe = driver.findElement(By.xpath("//iframe[contains(@title,'Rich Text Editor, publisherRichTextEditor')]"));
	driver.switchTo().frame(postframe);
  
  WebElement framebody = driver.findElement(By.xpath("//html[1]/body[1]"));
  framebody.click();
  framebody.sendKeys("Hello This is new message");
   
   //driver.switchTo().parentFrame();
   driver.switchTo().defaultContent();
   Thread.sleep(5000);
  WebElement shareButton = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
  shareButton.click();
  System.out.println("Message is posted");
  System.out.println("Message is posted");
 
  
  //Uploading a File--------------------------------------------
  
//  WebElement fileTab = driver.findElement(By.xpath("//a[@id = 'publisherAttachContentPost']"));
 // waitforVisibilty(60, fileTab);
 // fileTab.click();
  //Thread.sleep(3000);
 
 WebElement FileLink = driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
 //waitforVisibilty(60, FileLink);
 clickonme(FileLink, "FileLink");
System.out.println("---------------Clicked on filelink");
  
WebElement UploadFile = driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
clickonme(UploadFile, "UploadFile");
System.out.println("clicked on uploadfile button on computer");
Thread.sleep(3000);

String filepath = FILE_UPLOAD;
System.out.println(filepath);
WebElement choosefile = driver.findElement(By.xpath("//input[@id='chatterFile']"));
enterText(choosefile,filepath,"choosefile");


System.out.println("choosefile option is selected");
WebElement Share = driver.findElement(By.id("publishersharebutton"));
clickonme(Share, "ShareButton"); 
logger.info("file uploaded successfully");  
  
 
 	//Uploading photo--------------------------------------------------
	//span[@id='displayBadge']
WebElement moderator = driver.findElement(By.xpath("//span[@id='displayBadge']"));
mouseOver(driver, moderator);
WebElement AddPhotolink = driver.findElement(By.xpath("//a[@id='uploadLink']"));
clickonme(AddPhotolink, "AddPhotolink");


Thread.sleep(5000);	
// mouseOver(driver, photoBox);

WebElement photoFrame= driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']"));	 
driver.switchTo().frame(photoFrame);	 
Thread.sleep(3000);	 
//input[@id='j_id0:uploadFileForm:uploadInputFile']	 

WebElement fileuploadButton = driver.findElement(By.id("j_id0:uploadFileForm:uploadInputFile"));

fileuploadButton.sendKeys(PROFILE_PHOTO);
 System.out.println("upload profile page opened");
 
WebElement saveButton = driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn")); 
saveButton.click();
Thread.sleep(3000);

//input[@id='j_id0:j_id7:save']
WebElement save = driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
save.click();
Thread.sleep(2000);
System.out.println("My profile completed successfully");
  
System.out.println("TC_6 SelectmyProfile completed");



    }
    
  /**
 * @throws InterruptedException 
 * @throws IOException *******************************************************/   
   
    @Test
    public void TC_7_SelectMysettings() throws InterruptedException, IOException {
    	
    loginSalesforceSuccess();
    	
    WebElement UserMenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
	UserMenu.click();
	String dropdownverify=driver.findElement(By.xpath("//div[@id='userNav-menuItems']")).getText();
	System.out.println(dropdownverify);
		
    WebElement MySettings = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]"));
	waitforVisibilty(100, MySettings);
	MySettings.click();
	 
	String expectedUrl = driver.getCurrentUrl();
	//String actualUrl = "https://ap8.salesforce.com/ui/setup/Setup?setupid=PersonalSetup";
	String actualUrl = "https://tekarch59-dev-ed.develop.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup";
	if(actualUrl.equalsIgnoreCase(expectedUrl)) {
		System.out.println("User is on MySettings page");
	}
	else {
		System.out.println("MySettings page is not Lunched");
	}
		
	  //span[@id='PersonalInfo_font']
//WebElement personalTab = driver.findElement(By.xpath("//span[@id='PersonalInfo_font']"));
//personalTab.click();
Thread.sleep(5000);
WebElement Personal = driver.findElement(By.xpath("//*[@id=\"PersonalInfo_font\"]"));
Personal.click();	
WebElement LoginHistory = driver.findElement(By.xpath("//*[@id=\"LoginHistory_font\"]"));
LoginHistory.click();

	  //span[@id='LoginHistory_font']
//WebElement loginHistoryTab = driver.findElement(By.xpath("//span[@id='LoginHistory_font']"));
//loginHistoryTab.click();  
	    
	  //downloading .csv file
WebElement LoginHistory6Months= driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
LoginHistory6Months.click();
	    
		//Display and layout link
		//span[@id='DisplayAndLayout_font']
WebElement displayandLayoutTab = driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
displayandLayoutTab.click();  
	    
 WebElement customizeTab = driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
 customizeTab.click();  
	    
 WebElement dropdown = driver.findElement(By.id("p4"));
Select dropdownOptions = new Select(dropdown); 
dropdownOptions.selectByVisibleText("Salesforce Chatter");
		
		Thread.sleep(5000);
	    		
		//Available Tabs dropdown
dropdown = driver.findElement(By.id("duel_select_0"));
dropdownOptions = new Select(dropdown); 
dropdownOptions.selectByVisibleText("Reports");
WebElement Add_Arrow = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]/img"));
Add_Arrow.click();
	    
//checking if the selected element is in Selected Tabs dropdown

dropdown= driver.findElement(By.id("duel_select_1"));
dropdownOptions = new Select(dropdown); 
List<WebElement> elements = dropdownOptions.getOptions();
for(int i=0; i<elements.size(); i++) {
			if(elements.get(i).getText().equals("Reports")) {
				System.out.println("Reports Tab is added");	
			}
			else {
				System.out.println("Reports Tab is  not added");
			}
		}
		//Email Tab
WebElement Email = driver.findElement(By.id("EmailSetup"));
Email.click();
WebElement MyEmailSettings = driver.findElement(By.id("EmailSettings_font"));
MyEmailSettings.click();

WebElement Email_Name = driver.findElement(By.id("sender_name"));
Email_Name.clear();
Email_Name.sendKeys("jyothisa");
WebElement Email_Address = driver.findElement(By.id("sender_name"));
Email_Address.clear();
Email_Address.sendKeys("jyothisa@ta.com");
WebElement Bcc_radioButton = driver.findElement(By.id("auto_bcc1"));
Bcc_radioButton.click();
WebElement Save_button = driver.findElement(By.name("save"));
Save_button.click();		
		
		//Calender & Remainders
WebElement Calender_and_Reminders = driver.findElement(By.id("CalendarAndReminders"));
Calender_and_Reminders.click();
Thread.sleep(5000);
WebElement ActivityReminders = driver.findElement(By.xpath("//*[@id=\"Reminders_font\"]"));
ActivityReminders.click();
Thread.sleep(5000);
WebElement Test_Reminder = driver.findElement(By.className("btn"));
//Testeminder.click();
Thread.sleep(5000);
String parentWindow = driver.getWindowHandle();
		for(String handle : driver.getWindowHandles()) { 
			if(handle != parentWindow) {
				driver.switchTo().window(handle);
			String	expectedUrl1 = driver.getCurrentUrl();
			String	actualUrl1 = "https://tekarch59-dev-ed.develop.my.salesforce.com/ui/core/activity/ReminderSettingsPage?setupid=Reminders&retURL=%2Fui%2Fsetup%2FSetup%3Fsetupid%3DCalendarAndReminders";
				
			if(actualUrl1.equalsIgnoreCase(expectedUrl1)) {
					System.out.println("MySettings page is not Lunched");
			}
				else {
					System.out.println("User is on MySettings page");
				}	
			}
		}
			
		System.out.println("Tc_7_SelectMySettings is completed");
		}
		
		
		
	   	
    
   /**
 * @throws IOException *********************************************************************/
   @Test
   
    public void TC_8_Selectdeveloperconsole() throws InterruptedException, IOException {
    	
    	loginSalesforceSuccess();
    	
    	WebElement switchTousername = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
        waitforVisibilty(500, switchTousername);
    	switchTousername.click();
    	
    	//a[contains(text(),'Developer Console')]
    //	Thread.sleep(5000);
    	String parentwindow = driver.getWindowHandle();
    	WebElement developerele = driver.findElement(By.xpath("//a[contains(text(),'Developer Console')]"));
       	Thread.sleep(3000);
    	developerele.click();
    	
    	Thread.sleep(5000);
    	Set<String> getAllWindowsHandles=driver.getWindowHandles();
		String[] window=getAllWindowsHandles.toArray(new String[getAllWindowsHandles.size()]);
		driver.switchTo().window(window[1]).close();
    	driver.switchTo().window(parentwindow);
    	 
    	System.out.println("TC_8_Selectdeveloperconsole completed");
    }
    
  /**
 * @throws IOException 
 * @throws InterruptedException **********************************************************************/
   
   @Test
   
   public void TC_9_SelectLogout() throws InterruptedException, IOException {
	   
	   
	   loginSalesforceSuccess();
	   WebElement switchTousername = driver.findElement(By.xpath("//span[@id='userNavLabel']"));  
	   waitforVisibilty(500, switchTousername);
		switchTousername.click();
			
		WebElement logoutButton =  driver.findElement(By.xpath("//a[text() = 'Logout']"));
		waitforVisibilty(500, logoutButton);
		moveTOElementAction(logoutButton,"logout element");
		Thread.sleep(2000);
		logoutButton.click();
		waitforMe(5000);
		
		String	expectedUrl1 = driver.getCurrentUrl();
		String	actualUrl1 = SALESFORCE;
		
			
		if(actualUrl1.equalsIgnoreCase(expectedUrl1)) {
				System.out.println("User is not in login page");
		}
			else {
				System.out.println("User is on login page");
			}
		System.out.println("TC_9_SelectLogout completed");
   }
 /**
 * @throws IOException 
 * @throws InterruptedException ***********************************************/
   @Test
   
   public void TC_10_CreateanAccount() throws InterruptedException, IOException {
	    
	   loginSalesforceSuccess();   
	   
	   WebElement accountsTab = driver.findElement(By.xpath("//a[text()='Accounts']"));
	   
	    accountsTab.click();
	    System.out.println("Clicked on Accounts Ta..");
	    Thread.sleep(4000);
	    WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
	    popupwindow.click();
	    
	    WebElement newTab = driver.findElement(By.xpath("//*[@id=\'hotlist\']/table/tbody/tr/td[2]/input"));
		newTab.click();
		System.out.println("Clicked on New tab..");	
		
			//input[@id = 'acc2']
		WebElement accountname = driver.findElement(By.id("acc2"));
		accountname.clear();
		accountname.sendKeys("Jyothi");
		
		WebElement accounttype = driver.findElement(By.xpath("//select[@id ='acc6' ]"));
		Select acctypeselect = new Select(accounttype);
		acctypeselect.selectByVisibleText("Technology Partner");
		
		//select[@id='00NDn00000BSTTv']
//		Thread.sleep(4000);
		WebElement prioritytype = driver.findElement(By.xpath("//select[@id='00NDn00000Ujo0E']"));
		Select priorityselect = new Select(prioritytype);
		priorityselect.selectByVisibleText("High");
		
			//select[@id ='acc6' ]
		WebElement savebutton = driver.findElement(By.name("save"));
		savebutton.click();
		System.out.println("Clicked on Save button..");
		System.out.println("Tc_10_create account is completed");
		}	
	   
	   
/**
 * @throws IOException 
 * @throws InterruptedException *****************************************************************/	   
   @Test
   
   public void TC_11_Createnewview () throws InterruptedException, IOException {
	   
	   loginSalesforceSuccess();      
	   
	 //Opening Accounts page
	   WebElement accountsTab = driver.findElement(By.xpath("//a[text()='Accounts']"));
	   Actions action = new Actions(driver);
	   action.build().perform();
	   waitforVisibilty(500, accountsTab);
	   accountsTab.click();
	   Thread.sleep(4000);
	   WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
	   popupwindow.click();
	   
	   System.out.println("Clicked on Accounts Ta..");
	 //Creating New View
	   WebElement createview1 = driver.findElement(By.xpath("//*[@id=\'filter_element\']/div/span/span[2]/a[2]"));
		createview1.click();
		System.out.println("Clicked on Create New View..");
		
			//input[@id='fname']	
		WebElement viewname = driver.findElement(By.xpath("//input[@id='fname']"));
		waitforVisibilty(30, viewname);
		viewname.clear();
		viewname.sendKeys("hhhhh");
		
		String value = driver.findElement(By.xpath("//*[@id=\"fname\"]")).getAttribute("value");
		
		
		System.out.println(value);
		//input[@id='devname']
		WebElement viewuniqname = driver.findElement(By.xpath("//input[@id='devname']"));
		viewuniqname.clear();
		viewuniqname.sendKeys("h");
		//input[@name='save']
		Thread.sleep(5000);
		
	//	WebElement Save = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]"));
	//	Save.click();
	//	System.out.println("Clicked on save button"); 

		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
		saveButton.click();
		System.out.println("clicked on save button");
		
		Thread.sleep(5000);
		WebElement dropdown = driver.findElement(By.className("title"));
		dropdown.click();
		Select dropdown_options = new Select(dropdown);
		List<WebElement> options = dropdown_options.getOptions();
		Thread.sleep(5000);
		for(int i=0; i<options.size(); i++) {
			if(options.get(i).getText().equals(value)) {
				System.out.println("Account name is displayed in the dropdown");
			}
		}
		System.out.println("TC_11 Create new view completed....");
			
	    
   }
	
  /**
 * @throws IOException 
 * @throws InterruptedException ****************************************************************/
   @Test
   
   public void TC_12_Editview() throws InterruptedException, IOException {
	   
	   loginSalesforceSuccess();      
	   
		 //Opening Accounts page
		   WebElement accountsTab = driver.findElement(By.xpath("//a[text()='Accounts']"));
		   Actions action = new Actions(driver);
		   action.build().perform();
		   waitforVisibilty(500, accountsTab);
		   accountsTab.click();
		   Thread.sleep(4000);
		   WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
		   popupwindow.click();
		   
		   System.out.println("Clicked on Accounts Ta.."); 
	   
		   WebElement viewdropdown = driver.findElement(By.xpath("//select[@id='fcf']"));
			viewdropdown.click();
			Select select = new Select(viewdropdown);
			select.selectByIndex(1);
		//edit accounts	
			WebElement editTab = driver.findElement(By.xpath("//a[text()='Edit']"));
			editTab.click();
			
			WebElement newfname = driver.findElement(By.xpath("//input[@id='fname']"));
			newfname.clear();
			newfname.sendKeys("viewname");
			System.out.println("Viewname entered in the field");
			
			WebElement firstcolumn = driver.findElement(By.xpath("//select[@id='fcol1']"));
			firstcolumn.click();
				
			
			Select accfield = new Select(driver.findElement(By.xpath("//select[@id='fcol1']")));
			accfield.selectByIndex(1);
				
			
			Select operator = new Select(driver.findElement(By.xpath("//select[@id='fop1']")));
			operator.selectByIndex(3);
				
			WebElement value = driver.findElement(By.id("fval1"));
			value.clear();
			value.sendKeys("<A>");
			
				
				
			Thread.sleep(5000);
			WebElement saveTab = driver.findElement(By.xpath("//input[@name = 'save']"));
			saveTab.click();
			Thread.sleep(5000);
			
			System.out.println("TC12Accounts_Edit_view is completed");
				  
	     
	   
   }
   
   /**
 * @throws IOException 
 * @throws InterruptedException **********************************************************************/
   
   @Test
   
   public void TC_13_Mergeaccounts() throws InterruptedException, IOException {
	   
loginSalesforceSuccess(); 


WebElement accountsTab = driver.findElement(By.xpath("//a[text()='Accounts']"));
Actions action = new Actions(driver);
action.build().perform();
waitforVisibilty(500, accountsTab);
accountsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();

WebElement Mergeacc = driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]"));
Mergeacc.click();
Thread.sleep(4000);


WebElement fb = driver.findElement(By.id("srch"));
fb.clear();
fb.sendKeys("Jy");

WebElement FindAccount = driver.findElement(By.xpath("//input[@value ='Find Accounts']"));
waitforVisibilty(500, FindAccount);
FindAccount.click();
Thread.sleep(4000);

// WebElement Button1 = driver.findElement(By.xpath("//input[@id='cid0']"));
// Button1.click();
		
// WebElement Button2 = driver.findElement(By.xpath("//input[@id='cid1']"));
// Button2.click();

// WebElement Button3 = driver.findElement(By.xpath("//input[@id='cid2']"));
//   Button3.click();

//  Thread.sleep(4000);


WebElement nextbut = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/form[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
if(nextbut.isDisplayed()){
nextbut.click(); 
System.out.println("next clicked");
}
else
{
System.out.println("Next button not clicked"); 
}


WebElement Merge = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/form[1]/div[1]/div[2]/div[1]/div[1]/input[2]"));


if(Merge.isDisplayed())
{
System.out.println("merge displayed");
 Thread.sleep(5000);
}
Merge.click();	    
driver.switchTo().alert().accept();
System.out.println("Pass: alert is present and accept");	

System.out.println("TC13_MergeAccounts is completed");
   
	   
}
   
/**
 * @throws IOException 
 * @throws InterruptedException 
 * @throws AWTException *********************************************************/
   @Test
     
   public void TC_14_Createaccountreport() throws InterruptedException, IOException, AWTException {
	  
	   loginSalesforceSuccess(); 


	   WebElement accountsTab = driver.findElement(By.xpath("//a[text()='Accounts']"));
	   Actions action = new Actions(driver);
	   action.build().perform();
	   waitforVisibilty(500, accountsTab);
	   accountsTab.click();
	   Thread.sleep(4000);
	   WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
	   popupwindow.click();
	   
	   Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);

		WebElement LastActivity = driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
	    LastActivity.click();
		
		WebElement  dropdown = driver.findElement(By.xpath("//img[@id='ext-gen148']"));
		dropdown.click();
		
		WebElement createddate = driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		createddate.click();
		
		WebElement startdate =  driver.findElement(By.name("startDate"));
		startdate.clear();
		startdate.sendKeys("01/04/2019");
		
		WebElement enddate = driver.findElement(By.name("endDate"));
		enddate.clear();
		enddate.sendKeys("03/04/2019");
		
		WebElement save = driver.findElement(By.xpath("//button[@id='ext-gen49']"));
		save.click();
		WebElement Reportnames =  driver.findElement(By.name("reportName"));
		Reportnames.clear();
		Reportnames.sendKeys("Accountreports");
		
		WebElement ReportUniName =  driver.findElement(By.id("saveReportDlg_DeveloperName"));
		ReportUniName.clear();
		ReportUniName.sendKeys("Accountreports");
		Thread.sleep(4000);
	  
		WebElement Saveandrun = driver.findElement(By.xpath("//table[@id='dlgSaveAndRun']"));
		Saveandrun.click();
		//driver.close();
		System.out.println("TC_14_Createaccountre completed");
		   
   }
   
/**
 * @throws IOException 
 * @throws InterruptedException ******************************************************************/
   @Test
   
   public void TC_15_opportunitiesdropdown() throws InterruptedException, IOException {
loginSalesforceSuccess();
WebElement Opportunities = driver.findElement(By.xpath("//li[@id='Opportunity_Tab']"));
Opportunities.click();
Thread.sleep(5000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Select dropdownOpp = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));

Thread.sleep(2000);
System.out.println("TC_15_opportunitiesdropdown is completed");

}
   
 /**
 * @throws IOException 
 * @throws InterruptedException ********************************************************************/
   
  @Test
  
   public void TC_16_CreatenewOpty() throws InterruptedException, IOException {
loginSalesforceSuccess();	   
	   
WebElement Opportunities = driver.findElement(By.xpath("//li[@id='Opportunity_Tab']"));
Opportunities.click();
Thread.sleep(3000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Select dropdownOpp = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));

WebElement NewButton = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]"));
NewButton.click();

WebElement Oppname = driver.findElement(By.id("opp3"));
Oppname.clear();
Oppname.sendKeys("Marketing");	

//input[@id='opp4']
WebElement Oppnaccount = driver.findElement(By.id("opp4"));
Oppnaccount.clear();
Oppnaccount.sendKeys("Jyothi");

WebElement Oppntype = driver.findElement(By.xpath("//select[@id ='opp5']"));
Select oppselect1 = new Select(Oppntype);
oppselect1.selectByVisibleText("New Customer");
//select[@id='opp11']

WebElement Oppnlead = driver.findElement(By.xpath("//select[@id ='opp6']"));
Select oppselect3 = new Select(Oppnlead);
oppselect3.selectByVisibleText("Web");

WebElement Oppnclosedate = driver.findElement(By.id("opp9"));
Oppnclosedate.click();

WebElement today=driver.findElement(By.xpath("//a[@class='calToday']"));
today.click();

Actions action = new Actions(driver);
action.sendKeys(Keys.ESCAPE).build().perform();

WebElement Stage = driver.findElement(By.xpath("//select[@id='opp11']"));
Stage.click();

WebElement Oppnstage = driver.findElement(By.xpath("//select[@id ='opp11']"));
Select oppselect2= new Select(Oppnstage);
oppselect2.selectByVisibleText("Qualification");

WebElement probability = driver.findElement(By.id("opp12"));
probability.clear();
probability.sendKeys("10");


WebElement pcampaign = driver.findElement(By.id("opp17"));
pcampaign.clear();
pcampaign.sendKeys("  ");

//input[@name = 'save']
 WebElement savebutton = driver.findElement(By.xpath("//input[@name = 'save']"));
 savebutton.click();

System.out.println("TC_15_opportunitiesdropdown is completed");

  
   }
   
 /**
 * @throws IOException 
 * @throws InterruptedException *****************************************************/
  @Test
  
  public void TC_17_TestOpportunityPipelineReport () throws InterruptedException, IOException {
	  
loginSalesforceSuccess();	   
	   
WebElement Opportunities = driver.findElement(By.xpath("//li[@id='Opportunity_Tab']"));
Opportunities.click();
Thread.sleep(3000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
	  
WebElement pipeline = driver.findElement(By.xpath("//a[text()='Opportunity Pipeline']"));
pipeline.click();	
System.out.println("TC_17 opportunity pipeline report completed");
	  
  }
   
   
/**
 * @throws IOException 
 * @throws InterruptedException ***********************************************************************/
  @Test
  
  public void TC_18_TestStuckOpportunitiesReport() throws InterruptedException, IOException {
	  
loginSalesforceSuccess();	   
WebElement Opportunities = driver.findElement(By.xpath("//li[@id='Opportunity_Tab']"));
Opportunities.click();
Thread.sleep(3000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();	  
	  
WebElement stuck = driver.findElement(By.xpath("//a[text()='Stuck Opportunities']"));
stuck.click();

System.out.println("TC_18 stuck opportunities report completed");	
	  
  }
   
  /**
 * @throws IOException 
 * @throws InterruptedException *********************************************************/
  
  @Test
  public void TC_19_TestQuarterlySummaryReport() throws InterruptedException, IOException {
 loginSalesforceSuccess();	   
 WebElement Opportunities = driver.findElement(By.xpath("//li[@id='Opportunity_Tab']"));
 Opportunities.click();
Thread.sleep(3000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();	  
	  	    
WebElement interval = driver.findElement(By.xpath("//select[@id='quarter_q']"));
interval.click();
Select interval1 = new Select(interval);
interval1.selectByVisibleText("Next FQ");

WebElement include = driver.findElement(By.xpath("//select[@id='quarter_q']"));
include.click();
Select include1 = new Select(include);
include1.selectByIndex(1);
//selectByVisibleText("Open Opportunities");

//input[@value='Run Report']
WebElement runbutton = driver.findElement(By.xpath("//input[@value='Run Report']"));
runbutton.click();	  
	  
System.out.println("TC_19 stuck opportunities report completed");	  
	  
  }
   
/**
 * @throws IOException 
 * @throws InterruptedException ******************************************************************/   
  @Test
  
  public void TC_20_leadstab() throws InterruptedException, IOException {
loginSalesforceSuccess();	   
WebElement lead = driver.findElement(By.xpath("//a[text()='Leads']"));
lead.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);


System.out.println("TC_20 Leads tab completed");	
		
 }
  
 /**
 * @throws IOException 
 * @throws InterruptedException *****************************************************************/
  @Test
  
  public void TC_21_leadsSelectView() throws InterruptedException, IOException {
loginSalesforceSuccess();	   
WebElement lead = driver.findElement(By.xpath("//a[text()='Leads']"));
lead.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);


//select[@id='scope']
		WebElement viewdropdown = driver.findElement(By.xpath("//select['#fcf']"));
		viewdropdown.click();
		
		Select selectview = new Select(viewdropdown);
		
		//Get all options
	    List<WebElement> vew = selectview.getOptions();

	    //Get the length
	    System.out.println(vew.size());

	    // Loop to print one by one
	    for (int j = 0; j < vew.size(); j++) {
	        System.out.println(vew.get(j).getText());
	    }
	
	   System.out.println("TC_21 leadsview dropdown completed"); 
	    
  
  }
  
 /**
 * @throws IOException 
 * @throws InterruptedException ************************************************************/
  @Test
  
  public void TC_22_defaultView() throws InterruptedException, IOException {
loginSalesforceSuccess();	   
WebElement lead = driver.findElement(By.xpath("//a[text()='Leads']"));
lead.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);
  
WebElement leadviewdropdown = driver.findElement(By.xpath("//select['#fcf']"));
leadviewdropdown.click();
Select view = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
view.selectByIndex(1);
Thread.sleep(5000);
 
//WebElement gobutton = driver.findElement(By.xpath("//input[@title = 'Go!']"));
//gobutton.click();

	WebElement UserMenu = driver.findElement(By.xpath("//*[@id='userNavButton']"));
  waitforVisibilty(30, UserMenu); 
  selectDropdown(UserMenu, "UserMenu");
	//Thread.sleep(3000);
	
	WebElement logoutButton =  driver.findElement(By.xpath("//a[text() = 'Logout']"));
	waitforVisibilty(50, logoutButton);
	clickonme(logoutButton, "logout");
	
	loginSalesforceSuccess();
	
	WebElement lead1 = driver.findElement(By.xpath("//a[text()='Leads']"));
	lead1.click();
	//Thread.sleep(4000);
//	WebElement popupwindow1 = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
//	popupwindow1.click();
//	Thread.sleep(3000);
	  
	System.out.println("TC22 Go leads completed");

   
  }
  
  /**
 * @throws IOException 
 * @throws InterruptedException ************************************************************/
  @Test
  
public void TC_23_TodaysLeads() throws InterruptedException, IOException {
	  
loginSalesforceSuccess();	   
WebElement lead = driver.findElement(By.xpath("//a[text()='Leads']"));
lead.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);	  
	  
WebElement leadviewdropdown = driver.findElement(By.xpath("//select['#fcf']"));
leadviewdropdown.click();
Select view = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
view.selectByIndex(3);

WebElement gobutton = driver.findElement(By.xpath("//span[@class='fBody']//input[@name='go']"));
gobutton.click();
Thread.sleep(5000);

System.out.println("TC23 Go leads completed");
 	  
 
  }
 /**
 * @throws IOException 
 * @throws InterruptedException ***************************************************************/
@Test

public void TC_24_CheckNewbutton() throws InterruptedException, IOException {
	  
loginSalesforceSuccess();	   
 WebElement lead = driver.findElement(By.xpath("//a[text()='Leads']"));
lead.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);	  	  
	  
WebElement newbutton = driver.findElement(By.xpath("//input[@name = 'new']"));
newbutton.click();

WebElement lastname = driver.findElement(By.xpath("//input[@id='name_lastlea2']"));
lastname.sendKeys("ABCD");
WebElement companyname = driver.findElement(By.xpath("//input[@id='lea3']"));
companyname.sendKeys("ABCD");
WebElement save = driver.findElement(By.xpath("//td[@id = 'bottomButtonRow']//input[@name ='save']"));
save.click();
Thread.sleep(2000);	  
	  
System.out.println("TC_24 create new lead completed");	  
	  
 }
  
 /**
 * @throws IOException 
 * @throws InterruptedException *********************************************************/
@Test

public void TC_25_Createnewcontact() throws InterruptedException, IOException {
	
loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);
		
WebElement newcontact = driver.findElement(By.xpath("//input[@name='new']"));
newcontact.click();

//input[@id ='name_lastcon2']
WebElement lastname = driver.findElement(By.xpath("//input[@id ='name_lastcon2']"));
lastname.sendKeys("Gandhi");

WebElement accname = driver.findElement(By.xpath("//input[@id ='con4']"));
accname.sendKeys("AAAA");
Thread.sleep(3000);
WebElement save = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name ='save']"));
save.click();	
	
System.out.println("TC_24 Create new contact completed");	  	
	
}
  
/**
 * @throws IOException 
 * @throws InterruptedException *****************************************************************/
@Test

public void TC_26_CreatenewviewintheContactPage() throws InterruptedException, IOException {
loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);	
	
WebElement createnew = driver.findElement(By.xpath("//a[text()='Create New View']"));
createnew.click();
//input[@id='fname']
WebElement viewname = driver.findElement(By.xpath("//input[@id='fname']"));
enterText(viewname, "NSDG" , "ViewName");


WebElement View_UniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
View_UniqueName.clear();
enterText(View_UniqueName, "NSDG" , "ViewUniqueName");


WebElement save = driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Save']"));
save.click();
Thread.sleep(3000);

 System.out.println("TC_26_CreatenewviewintheContactPage is Completed"); 	
	

}

/**
 * @throws IOException 
 * @throws InterruptedException **************************************************************/
@Test

public void TC_27_Checkrecentlycreatedcontact() throws InterruptedException, IOException {

loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);	
			
WebElement newcontact = driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
newcontact.click();
Select select = new Select(driver.findElement(By.xpath("//select[@id='hotlist_mode']")));
select.selectByVisibleText("Recently Created");	

System.out.println("TC_27 choose recently created contact completed");
	
}

/**
 * @throws IOException 
 * @throws InterruptedException *****************************************************************/
@Test

public void TC_28_CheckMycontacts() throws InterruptedException, IOException {
	
loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);		
	
WebElement mycontact = driver.findElement(By.xpath("//select[@id='fcf']"));
mycontact.click();
Select select = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
select.selectByVisibleText("My Contacts");
Thread.sleep(3000);

System.out.println("Tc_28 view my contacts test completed");
	
}

/**
 * @throws IOException 
 * @throws InterruptedException ************************************************************/

@Test

public void TC_29_ViewcontactinthecontactPage() throws InterruptedException, IOException {

loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);		
	
WebElement contact = driver.findElement(By.xpath("//a[text()='Gandhi']"));
contact.click();
System.out.println("TC_29 view contact in contact page completed");


}
/**
 * @throws IOException 
 * @throws InterruptedException **************************************************************/
@Test

public void TC_30_ChecktheContactErrormessage() throws InterruptedException, IOException {

loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);		
		
WebElement createnew = driver.findElement(By.xpath("//a[text()='Create New View']"));
createnew.click();
//input[@id='fname']

WebElement View_UniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
View_UniqueName.clear();
View_UniqueName.sendKeys("EFGH");

WebElement save = driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Save']"));
save.click();

//div[@class='errorMsg']
WebElement errMsg = driver.findElement(By.xpath("//div[contains(text(),'You must enter a value')]"));
String errorMessage = errMsg.getText();

String message = "Error: You must enter a value";
System.out.println(errorMessage);

if(errorMessage.equalsIgnoreCase(message)) {
 System.out.println("Test case is passed");	
 }
else {
	System.out.println("Test case failed");
}

 System.out.println("TC_30 CreatenewviewintheContactPage is Completed"); 	

}

/**
 * @throws IOException 
 * @throws InterruptedException *************************************************************/




@Test

public void TC_31_CheckingCancelbutton() throws InterruptedException, IOException {
loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);

WebElement createnew = driver.findElement(By.xpath("//a[text()='Create New View']"));
createnew.click();
//input[@id='fname']
WebElement viewname = driver.findElement(By.xpath("//input[@id='fname']"));
viewname.sendKeys("foo");

WebElement View_UniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
View_UniqueName.clear();
View_UniqueName.sendKeys("foo");

WebElement cancel = driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@value='Cancel']"));
cancel.click();

 System.out.println("TC_31 cancel create new view pass"); 


}

/**
 * @throws IOException 
 * @throws InterruptedException *********************************************************************/
@Test

public void TC_32_CheckingSaveandNewbutton() throws InterruptedException, IOException {
	
loginSalesforceSuccess();	   
WebElement contactsTab = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
contactsTab.click();
Thread.sleep(4000);
WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);	
	
WebElement newcontact = driver.findElement(By.xpath("//input[@name='new']"));
newcontact.click();


WebElement lastname = driver.findElement(By.xpath("//input[@id ='name_lastcon2']"));
lastname.sendKeys("Indian");

WebElement accname = driver.findElement(By.xpath("//input[@id ='con4']"));
accname.sendKeys("Global Media");
Thread.sleep(5000);
WebElement saveandNew = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save_new']"));
saveandNew.click();


System.out.println("TC_32 test case save and new  pass");

}


/**
 * @throws IOException 
 * @throws InterruptedException *******************************************************************/

@Test

public void TC_33() throws InterruptedException, IOException {
	
loginSalesforceSuccess();	
	
WebElement homeTab = driver.findElement(By.xpath("//a[text()='Home']"));
homeTab.click();
Thread.sleep(3000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);
		

//a[text()='Joythi SA']
WebElement namelink = driver.findElement(By.xpath("//a[text()='Joythi SA']"));
String nameonHomepage = namelink.getText();
namelink.click();
Thread.sleep(3000);
	
//span[text()='Joythi SA']
WebElement profileName = driver.findElement(By.xpath("//span[text()='Joythi SA']"));
String myUsername = profileName.getText();
System.out.println("Profile name is " + myUsername);

if(nameonHomepage.equals(myUsername)) {
	System.out.println("Username on Home page is same as username in my Profile page");
}
else {
	System.out.println("Username on home page is different from Profile page");
}


System.out.println("TC_33 verify home page and firstname lastname pass");	
	

}

/**
 * @throws IOException 
 * @throws InterruptedException ****************************************************************/

@Test

public void TC_34() throws InterruptedException, IOException {

loginSalesforceSuccess();	
	
WebElement homeTab = driver.findElement(By.xpath("//a[text()='Home']"));
homeTab.click();
Thread.sleep(3000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);
		
//span[@class ='mruText']
WebElement nameTab = driver.findElement(By.xpath("//span[@class ='mruText']"));
nameTab.click();


Thread.sleep(3000);
//img[@src='/img/func_icons/util/pencil12.gif']
WebElement editIcon = driver.findElement(By.xpath("//img[@src='/img/func_icons/util/pencil12.gif']"));
editIcon.click();

//li[@id='aboutTab']

 Thread.sleep(3000);
driver.switchTo().frame("contactInfoContentId");
WebElement about = driver.findElement(By.xpath("//li[@id='aboutTab']"));
about.click();
Thread.sleep(3000);
//input[@id='firstName']

WebElement lname = driver.findElement(By.xpath("//input[@id='lastName']"));
lname.click();

Thread.sleep(3000);

WebElement saveAll = driver.findElement(By.xpath("//input[@value='Save All']"));
saveAll.click();

System.out.println("TC_34 verify home page and firstname lastname pass");	
	
}

/**
 * @throws IOException 
 * @throws InterruptedException ******************************************************************/

@Test

public void TC_35_Verifycustomizationtab() throws InterruptedException, IOException {
	
loginSalesforceSuccess();		
WebElement homeTab = driver.findElement(By.xpath("//a[text()='Home']"));
homeTab.click();
Thread.sleep(6000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);

WebElement switchTousername = driver.findElement(By.xpath("//span[@class ='menuButtonLabel' and text() = 'Joythi SA']"));
waitforVisibilty(100, switchTousername);
Actions action = new Actions(driver);
action.moveToElement(switchTousername).build().perform();
switchTousername.click();
 waitforMe(5000);
 
 WebElement logoutButton =  driver.findElement(By.xpath("//a[text() = 'Logout']"));
	waitforVisibilty(500, logoutButton);
	moveTOElementAction(logoutButton,"logout element");
	Thread.sleep(2000);
	logoutButton.click();
	waitforMe(5000);

loginSalesforceSuccess();

System.out.println("TC_35 home page tab customization pass");
	
}

/**
 * @throws IOException 
 * @throws InterruptedException ******************************************************************/

@Test

public void TC_36_Blockingeventcalender() throws InterruptedException, IOException {
	
loginSalesforceSuccess();	
	
WebElement homeTab = driver.findElement(By.xpath("//a[text()='Home']"));
homeTab.click();
Thread.sleep(5000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
Thread.sleep(3000);
		
WebElement datelink = driver.findElement(By.xpath("//span[@class='pageDescription']/a[1]"));
clickonme(datelink, "datelink");

Thread.sleep(4000);


WebElement Time = driver.findElement(By.xpath("//a[contains(text(), '8:00 PM')]"));
clickonme(Time,"time");
Thread.sleep(4000);


String parentHandle = driver.getWindowHandle();
System.out.println("currenthandle="+parentHandle);


driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']")).click();
Thread.sleep(4000);

Set<String> windowHandles = driver.getWindowHandles();
String currentHandle = driver.getWindowHandle();
windowHandles.remove(currentHandle);
String newHandle = windowHandles.iterator().next();
driver.switchTo().window(newHandle);



WebElement subjectLookup = driver.findElement(By.xpath("//a[@href ='javascript:pickValue(4);']"));
subjectLookup.click();

driver.switchTo().window(parentHandle);

Thread.sleep(6000);


WebElement Date = driver.findElement(By.xpath("//input[@id='EndDateTime']"));
Date.sendKeys("2/20/2023");;
driver.findElement(By.xpath("//img[contains(@class,'calRight')]")).click();
WebElement pickingtodayDate = driver.findElement(By.xpath("//a[@class='calToday']"));

pickingtodayDate.click();

WebElement endtime=driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
clickonme(endtime,"endtime");

driver.findElement(By.xpath("//div[@id='timePickerItem_42']")).click();
Thread.sleep(4000);

//input[@id='reminder_select_check']
WebElement reminderCheck = driver.findElement(By.xpath("//input[@id='reminder_select_check']"));
if(reminderCheck.isSelected()) {
	reminderCheck.click();
	System.out.println("reminder checked");
}
Thread.sleep(3000);
WebElement save = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
save.click();
logger.info("event calender saved");
Thread.sleep(2000);
takescreenshot(driver);
System.out.println("TC36_EventinCalender Executed sucessfully");   
	
}

/**
 * @throws IOException 
 * @throws InterruptedException 
 * @throws AWTException *******************************************************************/

@Test
public void TC_37_Blockingcalenderweeklyrecurrance() throws InterruptedException, IOException, AWTException {

loginSalesforceSuccess();	

WebElement homeTab = driver.findElement(By.xpath("//a[text()='Home']"));
homeTab.click();
Thread.sleep(5000);

WebElement popupwindow = driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
popupwindow.click();
//Thread.sleep(5000);

WebElement datelink = driver.findElement(By.xpath("//span[@class='pageDescription']/a[1]"));
clickonme(datelink, "datelink");
Thread.sleep(4000);

String parentHandle = driver.getWindowHandle();
System.out.println("currenthandle="+parentHandle);

WebElement Time = driver.findElement(By.xpath("//a[contains(text(), '4:00 PM')]"));
Time.click();
Thread.sleep(4000);

driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']")).click();
Thread.sleep(4000);

Set<String> windowHandles = driver.getWindowHandles();
String currentHandle = driver.getWindowHandle();
windowHandles.remove(currentHandle);
String newHandle = windowHandles.iterator().next();
driver.switchTo().window(newHandle);



WebElement subjectLookup = driver.findElement(By.xpath("//a[@href ='javascript:pickValue(4);']"));
subjectLookup.click();

driver.switchTo().window(parentHandle);

Thread.sleep(6000);


WebElement recurrance = driver.findElement(By.xpath("//label[contains(text(),'Create Recurring Series of Events')]"));
recurrance.click();
WebElement weekly = driver.findElement(By.xpath("//label[text() = 'Weekly']"));
weekly.click();

WebElement recur = driver.findElement(By.xpath("//input[@id='wi']"));
recur.clear();
recur.sendKeys("1");
Thread.sleep(5000);

//input[@id='RecurrenceEndDateOnly']
WebElement recurranceendDate = driver.findElement(By.xpath("//input[@id='RecurrenceEndDateOnly']"));
recurranceendDate.sendKeys("3/06/2023");




WebElement endTime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
endTime.click();
Thread.sleep(4000);

WebElement reminderCheck = driver.findElement(By.xpath("//input[@id='reminder_select_check']"));
if(reminderCheck.isSelected()) {
	reminderCheck.click();
	System.out.println("reminder checked");
}
Thread.sleep(5000);


//takescreenshot(driver);
WebElement save = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
save.click();

Thread.sleep(5000);
//tbody/tr[1]/td[2]/div[1]/div[1]/div[2]/span[2]/a[3]/img[1]
// month view
WebElement monthTab = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/div[1]/div[1]/div[2]/span[2]/a[3]/img[1]"));
monthTab.click();
Thread.sleep(3000);
Robot r = new Robot();
r.mouseWheel(10);

takescreenshot(driver);

System.out.println("TC37_BlockingAnEvent_Calendar_WeeklyRecurring is done");
	

}

/*********************************************************************/



	   
   }
   
   
   
   
    

    
    
    		
    

