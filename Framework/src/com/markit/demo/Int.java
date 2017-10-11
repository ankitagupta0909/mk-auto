package com.markit.demo;

public interface Int {
	
	int i=1;
	
	 void a();

	 abstract void jj();
	 
	 default void ab(){
		 System.out.println("");
	 }
	 
	 static void abc(){
		 System.out.println("");
	 }
	 
	 public class A implements Int{

		 
		@Override
		public void a() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void jj() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void ab(){}
		
	
			
	 }
}

