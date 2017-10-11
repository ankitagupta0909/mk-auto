package com.markit.framework.mcpm.pageMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;








import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.server.browserlaunchers.FirefoxLauncher;

public class CopyOfMCPM_login {

	public static WebDriver launch(int browsernumber)
	{
		WebDriver browser=null;
		if(browsernumber==1)
		{
			ProfilesIni allProfiles = new ProfilesIni();
			 // Use FirefoxProfile Constructor
			 FirefoxProfile myProfile = allProfiles.getProfile("CertificateIssue");
			myProfile.setAcceptUntrustedCertificates(true);
			myProfile.setAssumeUntrustedCertificateIssuer(true);
			
			
	        // Accept Untrusted Certificates  
	      
	        //Intialize Forfox driver  


			browser= new FirefoxDriver(myProfile);
		}
		
		else if(browsernumber==2)
		{
			System.setProperty("webdriver.chrome.driver", "C:/Common framework/Framework/src/drivers/chromedriver.exe");
			browser =new ChromeDriver();
		}
		
		else if(browsernumber==3)
		{
			System.setProperty("webdriver.ie.driver", "C:/Common framework/Framework/src/drivers/IEDriverServer.exe");
			browser =new InternetExplorerDriver();
		}
		return browser;
		
	}
	public static WebDriver mcpmLogin() throws InterruptedException
	{
	
		WebDriver driver= launch(2) ;
		driver.manage().window().maximize();
		driver.get("https://productsuat.markit.com/home/login.jsp");
		//driver.get("https://ufeqa.markit.com/home/login.jsp");
		//driver.get("https://mc.stage.markit.com");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		WebElement uname= driver.findElement(By.xpath("//input[@id='username']"));
		uname.clear();
		//uname.sendKeys("varun.joshi@markit.com");
		uname.sendKeys("kycAutoUser@ihsmarkit.com");
		//uname.sendKeys("madmin");
		WebElement pwd= driver.findElement(By.xpath("//input[@id='password']"));
		pwd.clear();
		//pwd.sendKeys("Welcome99#");
		pwd.sendKeys("India@10");
		//pwd.sendKeys("m4rk1t@123");
		WebElement login= driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
		login.click();
		System.out.println("logined");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("Markit - Counterparty Manager > Welcome"));
		
		
			System.out.println("Refresh");
			driver.navigate().refresh();
			System.out.println("done");
		
		System.out.println("Start");
		
		/*wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@name='MFrame']")));*/		
	
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(),'Counterparties')]")));
		
	/*	String pageSource = driver.getPageSource();
		
		
		System.out.println(pageSource);*/
		//driver.switchTo().frame("MFrame");
		
		//WebElement create= driver.findElement(By.xpath("//li[@class='item Logout']/a"));
		
		/*WebElement create1= driver.findElement(By.cssSelector(""));
		
		create1.click();*/
				/*xpath("//ul[@class='mdeCssMenu mdeCssMenum']"));
		create1.findElement(By.xpath("//span[contains(text(),'Create')]")).click();*/
		//create.click();
	
		
		/*WebElement arrow= driver.findElement(By.xpath("//a[contains(@href, 'javascript:void(0)')]"));
		arrow.click();
		WebElement logout= driver.findElement(By.xpath("//li[@class='item Logout']/a"));
		logout.click();*/
		//li[@class='mdeCssMenui'][2]/a/span
		
		/*WebElement cp=driver.findElement(By.xpath("//div[@class='ProductMenuIcon hasLayout MMenuIconItem-selected']/div"));
		cp.click();*/
		/*WebElement create= driver.findElement(By.xpath("//span[contains(text(),'Create')]"));
		
		Actions action = new Actions(driver);
		action.click(create).build().perform();
		create.click();
		//ul[@class='mdeCssMenu mdeCssMenum']//li[2]/a/span
		////li[@class='mdeCssMenui'][2]/a
		//span[contains(text(),'Create')]
		Actions action = new Actions(driver);
		action.moveToElement(create).build().perform();
		action.moveToElement(create).click().perform();
		Thread.sleep(1000);
		action.click(create).build().perform();
		create.click();
		System.out.println("Click at create");
		WebElement entity= driver.findElement(By.xpath("//li[@class='mdeCssInsideLI'][1]/a"));
		entity.click();
	
		WebElement ename= driver.findElement(By.name("legalName"));
		ename.sendKeys("(Selenium8)");
		
		WebElement displayname= driver.findElement(By.xpath("//input[@name='nameToUse']"));
		
		displayname.sendKeys("True/Legal Name");
		WebElement truename= driver.findElement(By.xpath("//li[contains(text(),'True/Legal Name')]"));
		
		//input[@name='nameToUse']/following-sibling::div/div
		Actions actions = new Actions(driver);
		actions.moveToElement(truename).click().perform();
		
		//truename.click();
		//li[contains(text(),'True/Legal Name')]
		
		WebElement country= driver.findElement(By.xpath("//input[@name='domicileCountryCd']"));
		country.sendKeys("India");
		WebElement countryname= driver.findElement(By.xpath("//li[contains(text(),'India')]"));
		actions.moveToElement(countryname).click().perform();
		
		WebElement entitytype= driver.findElement(By.xpath("//input[@name='legalType']"));
		entitytype.sendKeys("Broker / Dealer");
		WebElement entitytypename= driver.findElement(By.xpath("//li[contains(text(),'Broker / Dealer')]"));
		actions.moveToElement(entitytypename).click().perform();
		
		WebElement entitystatus= driver.findElement(By.xpath("//div[3]/div[2]/div/div/div/div[2]/div/div/div/div/div"));
		entitystatus.click();
		WebElement entitystatusname= driver.findElement(By.xpath("//li[contains(text(),'Active')]"));
		entitystatusname.click();
		
		
		WebElement save= driver.findElement(By.xpath("//span[contains(text(),'Save')]"));
		save.click();
		
		WebElement ok= driver.findElement(By.xpath("//div[@id='button-1009']//button"));
		ok.click();
		
		WebElement exit= driver.findElement(By.xpath("//span[contains(text(),'Exit')]"));
		exit.click();
		*/
		return(driver);
	}
	
}
