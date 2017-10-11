package com.markit.framework.kyc.pageMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.markit.common.framework.util.WebUtil;

public class Navigationbar {


	/**Navigate to KYC Dashboard
	 * @param driver
	 */
	public static void DashboardNav(WebDriver driver) {
		WebElement DashboardNav = WebUtil.findElement(
				"Dashboard.xpath.Navigate", driver);
		WebUtil.clickAt(DashboardNav);
	}
	/**Navigate to KYC Entity Screener
	 * @param driver
	 */
	public static void EntityNav(WebDriver driver) throws InterruptedException {
		WebElement EntityNav = WebUtil.findElement("Entity.linkText.Navigate",
				driver);
		WebUtil.clickAt(EntityNav);
		Thread.sleep(25000);

	}
	/**Navigate to KYC Notifications
	 * @param driver
	 */
	public static void NotificNav(WebDriver driver) {
		WebElement NotificNav = WebUtil.findElement("Notific.xpath.Navigate",
				driver);
		WebUtil.clickAt(NotificNav);

	}
	/**Navigate to KYC Entity Screener View Individuals
	 * @param driver
	 */
	public static void navigateToViewIndividuals(WebDriver driver) throws Exception
	{
		EntityNav(driver);
		WebElement viewLink= WebUtil.findElement("EntityScreener.xpath.ViewIndividuals",driver);
		Thread.sleep(5000);
		viewLink.click();
		Thread.sleep(10000);
	}

	/**Navigate to KYC Entity Screener Search Unsubscribed
	 * @param driver
	 */
	public static void navigateToSrchUnsub(WebDriver driver) throws Exception
	{
		EntityNav(driver);
		WebElement Link= WebUtil.findElement("EntityScreener.xpath.SrchUnsub",driver);
		Link.click();
		//Thread.sleep(1000);
	}

}
