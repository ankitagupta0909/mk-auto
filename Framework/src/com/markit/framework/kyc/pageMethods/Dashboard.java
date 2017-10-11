package com.markit.framework.kyc.pageMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.markit.common.framework.util.WebUtil;

/**
 * @author ankita.gupta
 *
 */
public class Dashboard {


	/**Hover the mouse over a chart section
	 * @param driver
	 * @param pathFillId
	 */
	public static void mouseHoverChart(WebDriver driver,String pathFillId) {

		WebElement elem = driver.findElement(By.xpath("//*[local-name() = 'path'][@fill="+pathFillId+"]"));
		//if(elem==null){System.out.println();}
		Actions mousehover = new Actions(driver);
		mousehover.moveToElement(elem).perform();	
	}

	/**
	 * Read the hover Text for a chart
	 * 
	 * @param driver
	 * @param locatorName
	 * @return hover text
	 */
	public static String mouseHoverReadText(WebDriver driver,String locatorName) {

		WebElement elem = WebUtil.findElement(locatorName, driver);
		String toolTipText=elem.getText();
		System.out.println(toolTipText);
		return toolTipText;
	}
 
	/**Read the Number of Entities in hover text for a chart
	 * @param driver
	 * @param toolTipText
	 * @return number(int) in hover text
	 */
	public static String mouseHoverReadNumber(WebDriver driver,String toolTipText){
		String str=new String(toolTipText);
		String str1[]=str.split("\\n");
		String a[]=str1[1].split(" ");
		System.out.println(a[0]);
		return a[0];
	
	}

	/**hover over a Bar Chart
	 * @param driver
	 * @param FillId -color
	 */
	public static void mouseHoverBarChart(WebDriver driver,String FillId) {

		WebElement elem = driver.findElement(By.xpath("//*[local-name() = 'rect'][@fill="+ FillId +"]"));
		Actions mousehover = new Actions(driver);
		mousehover.moveToElement(elem).perform();	
	}

	
	/**
	 * 
	 *Reads the Legends
	 *
	 * @param driver
	 * @param locatorName
	 * @return legend text
	 * 
	 */
	public static String readLegends(WebDriver driver,String locatorName){
		WebElement elem = WebUtil.findElement(locatorName, driver);
		String elementval = elem.getText();
		return elementval;
	}
	
	/** click pie chart segments
	 * @param driver
	 * @param pathFillId
	 */
	public static void clickOnGraphSegments(WebDriver driver,String pathFillId){
		WebElement elem = driver.findElement(By.xpath("//*[local-name() = 'path'][@fill="+ pathFillId +"]"));
		Actions mousehover1 = new Actions(driver);
		mousehover1.click(elem).build().perform();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**Release the hover of mouse
	 * @param driver
	 * @param locatorName-Element Key name defined in XPath.properties file
	 */
	public static void mouseHoverRelease(WebDriver driver,String locatorName){
		WebElement elem=WebUtil.findElement(locatorName, driver);
		WebUtil.clickAt(elem);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**Hover over a segment having similar color
	 * @param driver
	 * @param pathFillId-color of graph
	 */
	public static void mouseHoverduplicateColorChart(WebDriver driver,String pathFillId) {

		List<WebElement> elem = driver.findElements(By.xpath("//*[local-name() = 'path'][@fill="+ pathFillId +"]"));
		WebElement element=elem.get(1);
		
		Actions mousehover = new Actions(driver);
		mousehover.moveToElement(element).perform();	
	}
	
	/**Click over a segment having similar color
	 * @param driver
	 * @param pathFillId
	 */
	public static void clickOnduplicateColorGraphSegments(WebDriver driver,String pathFillId){
		List<WebElement> elem = driver.findElements(By.xpath("//*[local-name() = 'path'][@fill="+ pathFillId +"]"));
		WebElement element=elem.get(1);
		Actions mousehover1 = new Actions(driver);
		mousehover1.click(element).build().perform();
	}
}

