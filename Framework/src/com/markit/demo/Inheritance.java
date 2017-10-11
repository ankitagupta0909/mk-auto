package com.markit.demo;

import java.io.IOException;

public class Inheritance {

	public static void main(String[] args) {
	
	A a=new A();
	System.out.println(a.x); //10
	a.display();//base
	a.show();//base
	
	B b= new B();
	System.out.println(b.x); //20
	b.display(); //sub
	b.show();//sub
	
	A obj= new B();
	System.out.println(obj.x);//10
	obj.display();//sub class
     obj.show();// base class
     
     
	}

}

	class A{
	
		int x=10;
		void display(){
			System.out.println("base class"+x);
		}
		
		static void show(){
			System.out.println("Show method base class");
		}
		
		void abc(int a) throws IOException{
		
			System.out.println("RTE");
		}

	

	}
	
	class B extends A{
		int x=20;
		void display(){
			System.out.println("sub class"+x);
		}
		
		static void show(){
			System.out.println("Show method sub class");
		}


		void abc() throws Exception{
			
		}
	}
	
	

