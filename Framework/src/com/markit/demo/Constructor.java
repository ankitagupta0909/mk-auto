package com.markit.demo;

public class Constructor {

	
	public Constructor(){
        System.out.println("Default constructor");
  }
  public Constructor(String str){
	  this();  
   	 System.out.println("Parametrized constructor with single param");
   	
  }
  
  public static void main(String args[]){
      //Creating an object using Constructor with 3 int arguments
	  Constructor obj = new Constructor("hello");
 }
  


}
