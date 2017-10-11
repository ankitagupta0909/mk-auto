package com.markit.demo;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Login {

	//@Test
	//public void login()
	public static void main(String[] args) 
	{/*
		System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://ufeqa.markit.com");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		WebElement username=driver.findElement(By.xpath("//*[@id='username']"));
		username.sendKeys("rfaProd_bs@markit.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("Welcome99#");
		
		WebElement login=driver.findElement(By.xpath("//fieldset[@id='loginActions']/a"));
		login.click();

		System.out.println("Login finished");
		*/

//linear search
		/*int [] Oldarr={1,2,3,5};
		int newElem=4;
		int elemToBeREplaced=0;
		int [] arr=new int[Oldarr.length+1];
		int j=0;
		for(int i=0;i<Oldarr.length;i++){
			if(Oldarr[i]<newElem){
				 arr[j]=Oldarr[i];
				 j++;
			}
			 else{
				   elemToBeREplaced=Oldarr[i];
				   arr[j]=newElem;
				   arr[++j]=elemToBeREplaced;
			   }
			}				
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
		*/
		
		
		
	/*	int [] InputArr={1,2,3,4,5};
		int elemToFind=4;
		int index=binarySearch(InputArr,elemToFind);
		System.out.println(index);*/
		
		int[] arr1 = {10,34,2,56,7,67,88,42};
	    int[] arr2 = doSelectionSort(arr1);

	}

public static int[]  doSelectionSort(int[] arr1){
	int[] arr2 = new int[arr1.length];
	int index=0;
	int cnt=0;
	for(int i=cnt;i<arr1.length;i++){		
		for(int j=i+1;j<arr1.length;j++){
			if(arr1[j]<arr1[i]){
				arr2[index]=arr1[j];			
				index++;
				cnt--;
			}
		}
	}
	
	return arr2;
}

public static int binarySearch(int[] InputArr,int elemToFind){
int start=0;
int end=InputArr.length-1;
while(start<end){
	int mid=(start+end)/2;
if(InputArr[mid]==elemToFind){
	return mid;
}	else if (elemToFind<InputArr[mid]){
	end=mid-1;
}else {
	start=mid+1;
}
}
return -1;

}
}