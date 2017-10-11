package com.markit.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


	// Main Class
	public class Threading
	{
	    public static void main(String[] args)
	    {
	       // int n = 5; // Number of threads
	        for (int i=0; i<3; i++)
	        {
	        	ThreadDemo object = new ThreadDemo();
	            object.start();
	        }
	        
	    }
	    
	}  
	
 class ThreadDemo extends Thread {

    public void run()
    { WebDriver driver=null;
        try
        {
            // Displaying the thread that is running
            System.out.println ("Thread " +
                  Thread.currentThread().getId() +
                  " is running");
            
            System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
             driver=new ChromeDriver();
            
            
            driver.get("https://www.google.co.in");
                              
 
        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
            e.printStackTrace();
        }
        finally{
        	driver.close();
        	driver.quit();
        }
    }
 }
 
