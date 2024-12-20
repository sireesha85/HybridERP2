package driverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class AppTest {
	
		String inputpath="./FileInput/DataEngine.xlsx";
		String outputpath="./FileOutput/HybridResults.xlsx";
		ExtentReports reports;
		ExtentTest logger;
		String TCSheet="MasterTestCase";
		WebDriver driver;
		
		@Test
		public void StartTest() throws Throwable
		{
			String Module_Status="  ";
			String Module_New="  ";
			//create object for excelfile util class

	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
			int rc=xl.rowCount(TCSheet);
			for(int i=1;i<rc;i++)
			{
				if(xl.getCellData(TCSheet, i, 2).equalsIgnoreCase("Y"))

				{
					String TCModule =xl.getCellData(TCSheet, i, 1);
					reports=new ExtentReports("./target/Reports/"+TCModule+FunctionLibrary.generateDate()+".html");
					logger=reports.startTest(TCModule);
		
					
					for(int j=1;j<=xl.rowCount(TCModule);j++)
					 {
					
						
						
						String Description=xl.getCellData(TCModule, j, 0);
						String ObjectType=xl.getCellData(TCModule, j, 1);
						String Locationtype=xl.getCellData(TCModule, j, 2);
						String LocatorValue=xl.getCellData(TCModule, j, 3);
						String TestData=xl.getCellData(TCModule,j, 4);
						try
						{
							if(ObjectType.equalsIgnoreCase("startBrowser"))
							{
								driver=FunctionLibrary.startBrowser();
								logger.log(LogStatus.INFO, Description);
								
							}
							if(ObjectType.equalsIgnoreCase("openUrl"))
							{
								FunctionLibrary.openUrl();
								logger.log(LogStatus.INFO, Description);
								
								
							}
					        
							if(ObjectType.equalsIgnoreCase("waitForElement"))
							{
								FunctionLibrary.waitForElement(Locationtype, LocatorValue, TestData);
								logger.log(LogStatus.INFO, Description);
								
								
							}
							if(ObjectType.equalsIgnoreCase("typeAction"))
							{
								
								FunctionLibrary.typeAction(Locationtype, LocatorValue, TestData);
								logger.log(LogStatus.INFO, Description);
						
								
							}
							if(ObjectType.equalsIgnoreCase("clickAction"))
							{
								FunctionLibrary.clickAction(Locationtype, LocatorValue);
								logger.log(LogStatus.INFO, Description);
							}
							if(ObjectType.equalsIgnoreCase("validateTitle"))
							{
								FunctionLibrary.validateTitle(TestData);
								logger.log(LogStatus.INFO, Description);
							}
							
							if(ObjectType.equalsIgnoreCase("closeBrowser"))
							{
								FunctionLibrary.closeBrowser();
								logger.log(LogStatus.INFO, Description);
								
							}
							
							if(ObjectType.equalsIgnoreCase("dropDownAction"))
							{
								FunctionLibrary.dropDownAction(Locationtype, LocatorValue, TestData);
								logger.log(LogStatus.INFO, Description);
							}
							
							if(ObjectType.equalsIgnoreCase("capturestock"))
							{
								FunctionLibrary.capturestock(Locationtype, LocatorValue);
								logger.log(LogStatus.INFO, Description);
							}
							
							if(ObjectType.equalsIgnoreCase("stockTable"))
							{
								FunctionLibrary.stockTable();
								logger.log(LogStatus.INFO, Description);
								
							}
							
							if(ObjectType.equalsIgnoreCase("capturesupplierno"))
								
							{
								FunctionLibrary.capturesupplierno(Locationtype, LocatorValue);
								logger.log(LogStatus.INFO, Description);
							}
							
							if(ObjectType.equalsIgnoreCase("supplierTable"))
							{
								FunctionLibrary.supplierTable();
								logger.log(LogStatus.INFO, Description);
							}
							
							if(ObjectType.equalsIgnoreCase("capturecustomerNo"))
							{
								FunctionLibrary.capturesupplierno(Locationtype, LocatorValue);
								logger.log(LogStatus.INFO, Description);
							}
							
							if(ObjectType.equalsIgnoreCase("customerTable()"))
							{
								FunctionLibrary.supplierTable();
								logger.log(LogStatus.INFO, Description);
							}
							
							
							
							xl.setCellData(TCModule, j, 5, "pass",outputpath);
							logger.log(LogStatus.PASS, Description);
							Module_Status="True";
						}
							catch (Exception e) {
								System.out.println(e.getMessage());
								
								xl.setCellData(TCModule, j, 5, "Fail", outputpath);
								logger.log(LogStatus.FAIL, Description);
								Module_New="False";
								
							}
					 
						if(Module_Status.equalsIgnoreCase("True"))
						{
							xl.setCellData(TCSheet, i, 3, "pass",outputpath);
							logger.log(LogStatus.PASS, Description);
							
						}
						if(Module_Status.equalsIgnoreCase("False"))
						{
				       xl.setCellData(TCSheet, i, 3,"Fail",outputpath);
				       logger.log(LogStatus.FAIL, Description);
							
					
					 }
					 }
						
					}
			
					
						else
						{
							xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
						 
						}
				
			}
				}
	}
			


	

