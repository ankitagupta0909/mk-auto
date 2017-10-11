package com.markit.demo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.markit.common.framework.util.WebUtil;

public class JIRA {

	public static void main(String[] args){
		
		System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://jira.markit.com");
		
		List<WebElement> e=WebUtil.findElements("", driver);
		
		e.forEach(elem -> elem.click());
		
		
		
		
	}
	

	
}
