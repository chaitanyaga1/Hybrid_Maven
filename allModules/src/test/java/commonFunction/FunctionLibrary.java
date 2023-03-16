package commonFunction;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Propertiesfile;

public class FunctionLibrary {
	public static WebDriver driver;

	public static WebDriver launchBrowser() throws Throwable {

		if(Propertiesfile.getkey("browser").equalsIgnoreCase("chrome")){
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(Propertiesfile.getkey("url"));
		}else if(Propertiesfile.getkey("browser").equalsIgnoreCase("firefox")){

			driver=new FirefoxDriver();
			driver.get(Propertiesfile.getkey("url"));

		}else {
			System.out.println("property key Values Not Matching");
		}
		return driver;
	}

	public static void openUrl(WebDriver driver) throws Throwable {
		driver.get(Propertiesfile.getkey("url"));
	}

	public static void waitForElement(WebDriver driver,String Locator_Type,
			String Locator_Value,String TestData ) {

		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(TestData));
		if(Locator_Type.equalsIgnoreCase("xpath")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));

		}else if(Locator_Type.equalsIgnoreCase("name")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
		}else if(Locator_Type.equalsIgnoreCase(Locator_Type)) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
		}
	}
	public static void typeAction(WebDriver driver,String Locator_Type,
			String Locator_Value,String TestData) {
		if(Locator_Type.equalsIgnoreCase("name")) {
			driver.findElement(By.name(Locator_Value)).clear();
			driver.findElement(By.name(Locator_Value)).sendKeys(TestData);
		}else if(Locator_Type.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(Locator_Value)).clear();
			driver.findElement(By.xpath(Locator_Value)).sendKeys(TestData);

		}else if(Locator_Type.equalsIgnoreCase("id")) {
			driver.findElement(By.id(Locator_Value)).clear();
			driver.findElement(By.id(Locator_Value)).sendKeys(TestData);
		}
	}
	public static void selectAction(WebDriver driver,String Locator_Type,
			String Locator_Value,String TestData) {
		if(Locator_Type.equalsIgnoreCase("xpath")) {
			Select select=new Select(driver.findElement(By.xpath(Locator_Value)));
			select.selectByVisibleText(TestData);

		}else if(Locator_Type.equalsIgnoreCase("name")) {
			Select select=new Select(driver.findElement(By.name(Locator_Value)));
			select.selectByVisibleText(TestData);
		} else if(Locator_Type.equalsIgnoreCase("id")) {
			Select select=new Select(driver.findElement(By.id(Locator_Value)));
			select.selectByVisibleText(TestData);

		}
	}
	public static void clickAction(WebDriver driver,String Locator_Type,
			String Locator_Value, String TestData) {
		if(Locator_Type.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(Locator_Value)).click();
		}else if(Locator_Type.equalsIgnoreCase("id")) {
			driver.findElement(By.id(Locator_Value)).sendKeys(Keys.ENTER);
		}else if(Locator_Type.equalsIgnoreCase("name")) {
			driver.findElement(By.xpath(Locator_Value)).click();
		}
	}

	public static void mouseClick(WebDriver driver,String Locator_Type,
			String Locator_Value, String TestData) {
		if(Locator_Type.equalsIgnoreCase("xpath")) {
			Actions ac=new Actions(driver);
			ac.moveToElement(driver.findElement(By.xpath(Locator_Value))).perform();    	   
		}

	}
  
	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}
	
	
	public static String dateGenarate() {
    	 
    	 
    	 Date date=new Date();
    	 DateFormat format=new SimpleDateFormat("YYYY/MM/DD");
    	return format.format(date);
    	 
     }
    public static void addition() {
    	
    	int a=10;
    	int b=15;
    	
    	
    }

}