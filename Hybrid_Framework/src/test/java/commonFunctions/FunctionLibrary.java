package commonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary {
	
	public static Properties conpro;
	public static WebDriver driver;
	public static WebDriver startBrowser() throws Throwable
	{
		
	
		conpro=new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.Properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
			
		{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser is not matching",true);
		}
		
			return driver;
					

}


//method for openurl
    public static void openUrl()
	{

      driver.get(conpro.getProperty("url"));		
	}
    
 //method for waitforelement
    
    public static void waitForElement(String LocatorType,String LocatorValue,String wait)
    {
    	WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(wait)));
    	if(LocatorType.equalsIgnoreCase("name"))
         {
	     mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
	    		 
         }
    	
    	if(LocatorType.equalsIgnoreCase("xpath"))
    	{
    		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
    		
    	}
    	if(LocatorType.equalsIgnoreCase("id"))
    	{
    		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
    		
    	}
    }


 
	
	//method for (textbox)typeaction
    
    public static void typeAction(String LocatorType,String LocatorValue,String TestData)
    {
    	if(LocatorType.equalsIgnoreCase("name"))
    	{
    		driver.findElement(By.name(LocatorValue)).clear();
    	    driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
    	    
    	}
       if(LocatorType.equalsIgnoreCase("id"))
       {
    	   driver.findElement(By.id(LocatorValue)).clear();
    	   driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
       }
    
       if(LocatorType.equalsIgnoreCase("xpath"))
       {
    	   driver.findElement(By.xpath(LocatorValue)).clear();
    	   driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
    	   
       }
    }
    
    public static void clickAction(String LocatorType,String LocatorValue) 
    {
    	
 	 if(LocatorType.equalsIgnoreCase("xpath"))
         {
      	   driver.findElement(By.xpath(LocatorValue)).click();
      	   }
    
    
 	if(LocatorType.equalsIgnoreCase("id"))
    {
 	   driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
 	   }

 	if(LocatorType.equalsIgnoreCase("name"))
    {
 	   driver.findElement(By.name(LocatorValue)).click();
    }
    }
    

 	//method for validateTitle
 	public static void validateTitle(String Expected_Title)
 	{
 		
 		String Actual_Title=driver.getTitle();
 		try
 		{
 			Assert.assertEquals(Actual_Title, Expected_Title,"Title is not matching");
 	
 		
 	}
 		catch(AssertionError e)
 		{
 			System.out.println(e.getMessage());
 		}
    }
    public static void closeBrowser()
    {
    	driver.quit();
 }
 //dropDownAction
    public static void dropDownAction(String LocatorType,String LocatorValue,String TestData)
    {
    	
  
    if(LocatorType.equalsIgnoreCase("xpath"))
    {
    	int value=Integer.parseInt(TestData);
    	Select Element=new Select(driver.findElement(By.xpath(LocatorValue)));
        Element.selectByIndex(value);
    	
    }
    if(LocatorType.equalsIgnoreCase("name"))
    {
    	int value=Integer.parseInt(TestData);
    	Select Element=new Select(driver.findElement(By.name(LocatorValue)));
        Element.selectByIndex(value);
    	
    }
    
    if(LocatorType.equalsIgnoreCase("id"))
    {
    	int value=Integer.parseInt(TestData);
    	Select Element=new Select(driver.findElement(By.id(LocatorValue)));
        Element.selectByIndex(value);
    	
    }
    }
    
    
    //capturestocknumber
    public static void capturestock(String LocatorType,String LocatorValue) throws Throwable
    
    {
    	String StockNumber="  ";
    	if(LocatorType.equalsIgnoreCase("xpath"))
    	{
    		StockNumber=driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
    		
    	}
    	
    	if(LocatorType.equalsIgnoreCase("id"))
    	{
    		StockNumber=driver.findElement(By.id(LocatorValue)).getAttribute("value");
    		
    	}
    	
    	if(LocatorType.equalsIgnoreCase("name"))
    	{
    		StockNumber=driver.findElement(By.name(LocatorValue)).getAttribute("value");
    		
    	}
    	
    //create notepad and stock number
    FileWriter fw=new FileWriter("./captureData/stocknum.txt");
    BufferedWriter bw=new BufferedWriter(fw);
    bw.write(StockNumber);
    bw.flush();
    bw.close();
    }
    
    
   //   method for entering stocknumber
    public static void stocknumber(String LocatorType,String LocatorValue,String TestData)
    {
    	if(LocatorType.equalsIgnoreCase("xpath"))
    	{
    		driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
    }
    
    	if(LocatorType.equalsIgnoreCase("id"))
    	{
    		driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
    }
    
    	if(LocatorType.equalsIgnoreCase("name"))
    	{
    		driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
    }
    }
    	
    
    
    
    
    
    
    	public static void dropDownAction2(String LocatorType,String LocatorValue,String TestData)
    	{
    		if(LocatorType.equalsIgnoreCase("xpath"))
    		{
    			int value=Integer.parseInt(TestData);
    			Select element=new Select(driver.findElement(By.xpath(LocatorValue)));
    			element.selectByIndex(value);
    		}
    		}

    	
    	
//stock table
    	public static void stockTable() throws Throwable
    	{
    		FileReader fr=new FileReader("./captureData/stocknum.txt");
    	    BufferedReader br=new BufferedReader(fr);
    	    String exp_Data=br.readLine();
           if(! driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
    	    driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
    	    driver.findElement(By.xpath(conpro.getProperty("saerch-textbox"))).sendKeys(exp_Data);
    	    driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
    	  String Act_Data=driver.findElement(By.xpath("//table[@class=class='table ewTable'/tbody/tr[1]/td[8]/div/span/span")).getText();
    	  Reporter.log(exp_Data+"  "+Act_Data,true);
    	    
    	  try
    	  {
    		  Assert.assertEquals(Act_Data, exp_Data,"Stock number is not matching");
    		  
    	  }
    	  catch(AssertionError e)

    	  {
    		  System.out.println(e.getMessage());
    	  }
    	
    	}
    	//supplier
    	
    	public static void capturesupplierno(String LocatorType,String LocatorValue) throws Throwable
    	{
    		String suppliernumber="  ";
    		
    	if(LocatorType.equalsIgnoreCase("xpath"))
    	{
    		suppliernumber=driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
    		
    	}
    	
    	if(LocatorType.equalsIgnoreCase("id"))
    	{
    		suppliernumber=driver.findElement(By.id(LocatorValue)).getAttribute("value");
    		
    	}
    	
    	
    	if(LocatorType.equalsIgnoreCase("name"))
    	{
    		suppliernumber=driver.findElement(By.name(LocatorValue)).getAttribute("value");
    		
    	}
    	
    	FileWriter fw=new FileWriter("./captureDAta/suppliernum.txt");
    	BufferedWriter bw=new BufferedWriter(fw);
    	bw.write(suppliernumber);
    	bw.flush();
    	bw.close();
   
    	}
    	
    	//suppliertable
    	
    	public static void supplierTable() throws Throwable
    	{
    		FileReader fr=new FileReader("./captureDAta/suppliernum.txt");
    		BufferedReader br=new BufferedReader(fr);
    		String Exp_Data=br.readLine();
    		if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
    		 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
    		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
    		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_Data);
    		 Thread.sleep(3000);
    		 
    		 String Act_Data=driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
    		 Reporter.log(Exp_Data+ "   "+Act_Data,true);
    		 try
    		 {
    		 
    			 Assert.assertEquals(Act_Data,Exp_Data,"supplier is not matching");
    		 }
    		 
    		 catch(AssertionError e)
    		 
    		 {
    			 System.out.println(e.getMessage());
    			 
    		 }
    	}
    			 		
    		 
    		 
    		 public static void capturecustomerNo(String LocatorType,String LocatorValue) throws Throwable
    	    	{
    	    		String customernumber="  ";
    	    		
    	    	if(LocatorType.equalsIgnoreCase("xpath"))
    	    	{
    	    		customernumber=driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
    	    		
    	    	}
    	    	
    	    	if(LocatorType.equalsIgnoreCase("id"))
    	    	{
    	    		customernumber=driver.findElement(By.id(LocatorValue)).getAttribute("value");
    	    		
    	    	}
    	    	
    	    	
    	    	if(LocatorType.equalsIgnoreCase("name"))
    	    	{
    	    		customernumber=driver.findElement(By.name(LocatorValue)).getAttribute("value");
    	    		
    	    	}
    	    	
    	    	FileWriter fw=new FileWriter("./captureDAta/customernum.txt");
    	    	BufferedWriter bw=new BufferedWriter(fw);
    	    	bw.write(customernumber);
    	    	bw.flush();
    	    	bw.close();
    	   
    	    	}
    	    	
    	    	//suppliertable
    	    	
    	    	public static void customerTable() throws Throwable
    	    	{
    	    		FileReader fr=new FileReader("./captureDAta/customernum.txt");
    	    		BufferedReader br=new BufferedReader(fr);
    	    		String Exp_Data=br.readLine();
    	    		if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
    	    		 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
    	    		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
    	    		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_Data);
    	    		 Thread.sleep(3000);
    	    		 
    	    		 String Act_Data=driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
    	    		 Reporter.log(Exp_Data+ "   "+Act_Data,true);
    	    		 try
    	    		 {
    	    		 
    	    			 Assert.assertEquals(Act_Data,Exp_Data,"customer is not matching");
    	    		 }
    	    		 
    	    		 catch(AssertionError e)
    	    		 
    	    		 {
    	    			 System.out.println(e.getMessage());
    	    			 
    	    		 }
    	    			 		
    	    		 
  
    	
    	}
    
    	
    	

    
public static String generateDate()
{
	Date date=new Date();
	DateFormat df=new SimpleDateFormat("YYYY_MM_DD");
	
			return df.format(date) ;
	
}
}