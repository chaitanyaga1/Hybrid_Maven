package driverScript;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import utility.Excelfileproperty;
import utility.Propertiesfile;

public class Testscript {
	public static WebDriver driver;
 String inputdata="D:\\qedg\\allModules\\FileInputPath\\AMAZON.xlsx";	
  String outputpath="D:\\qedg\\allModules\\FileOutputPath\\XLAG.xlsx";
  ExtentReports report;
  ExtentTest test;
 public void startTest() throws Throwable {
	String modulestatus="";
	 Excelfileproperty xl=new Excelfileproperty(inputdata);
	for(int i=1;i<=xl.getRow("MasterTestSheet");i++) {
	if(xl.getCellData("MasterTestSheet", i, 2).equalsIgnoreCase("Y")){
		String testModules=xl.getCellData("MasterTestSheet", i, 1);
		report=new ExtentReports("./exReports/"+testModules+"  "+FunctionLibrary.dateGenarate()+".html");		
		for(int j=1;j<=xl.getRow(testModules);j++) {
				
		  test=report.startTest(testModules);
		  test.assignAuthor("chaitanya");
		  test.assignCategory("Functional");
		   String Description=xl.getCellData(testModules, j, 0);
		   String Object_Type=xl.getCellData(testModules, j, 1);
		   String Locator_Type=xl.getCellData(testModules, j, 2);
		   String Locator_Value=xl.getCellData(testModules, j, 3);
		   String TestData=xl.getCellData(testModules, j, 4);
	     
  try {
       if(Object_Type.equalsIgnoreCase("launchBrowser")) {
             driver=FunctionLibrary.launchBrowser();
         test.log(LogStatus.INFO, Description);
       }else if(Object_Type.equalsIgnoreCase("openUrl")) {
    	   FunctionLibrary.openUrl(driver);
    	   test.log(LogStatus.INFO, Description);
       }else if(Object_Type.equalsIgnoreCase("waitForElement")) {
    	   FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, TestData);
    	   test.log(LogStatus.INFO, Description);
       }else if(Object_Type.equalsIgnoreCase("selectAction")) {
    	   FunctionLibrary.selectAction(driver, Locator_Type, Locator_Value, TestData);
    	   test.log(LogStatus.INFO, Description);
       }else if(Object_Type.equalsIgnoreCase("clickAction")){
    	   FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value, TestData);
    	   test.log(LogStatus.INFO, Description);
       }else if(Object_Type.equalsIgnoreCase("type_Action")) {
    	   FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, TestData);
    	   test.log(LogStatus.INFO, Description);
       }else if(Object_Type.equalsIgnoreCase("mouseClick")) {
    		FunctionLibrary.mouseClick(driver, Locator_Type, Locator_Value, TestData);
    		test.log(LogStatus.INFO, Description);
       }else if(Object_Type.equalsIgnoreCase("closeBrowser")) {
    	   FunctionLibrary.closeBrowser(driver);
       }
      xl.setCellData(testModules, j, 5, "Pass", outputpath); 
      test.log(LogStatus.PASS, Description);
      modulestatus="true";    
      }catch(Exception e) {
    	   System.out.println(e.getMessage());
      xl.setCellData(testModules, j, 5, "Fail", outputpath); 
      test.log(LogStatus.FAIL, Description);
      modulestatus="false";
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile, new File("./Screenshots/"+Description+"_"+FunctionLibrary.dateGenarate()+".png"));
		
		String image = test.addScreenCapture("./Screenshots/"+Description+"_"+FunctionLibrary.dateGenarate()+".png");
		
		test.log(LogStatus.FAIL, image);
		break;		
	}
	catch(AssertionError e)
	{
		xl.setCellData(testModules, j, 5, "Fail", outputpath);
		modulestatus = "false";
		test.log(LogStatus.FAIL, Description + "Fail");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      
		FileUtils.copyFile(scrFile, new File("./Screenshots/"+Description+"_"+FunctionLibrary.dateGenarate()+".jpg"));
		
		String image = test.addScreenCapture("./Screenshots/"+Description+"_"+FunctionLibrary.dateGenarate()+".jpg");
		
		test.log(LogStatus.FAIL, image);
		
		break;
      }
      
	   if(modulestatus.equals("true")) {
		   xl.setCellData("MasterTestSheet", i, 3, "Pass", outputpath);
		   
	   }else {
		   xl.setCellData("MasterTestSheet", i, 3, "Fail", outputpath);
	   }
	   }
		report.endTest(test);
		report.flush();


	   
	
	
	
	}else {
		xl.setCellData("MasterTestSheet", i, 3, "Blocked", outputpath);
	}
	}
		
 }
}
