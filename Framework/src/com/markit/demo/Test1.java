package com.markit.demo;






public class Test1 {
	

	
    private static int[] gregtaer; 
	int a;
	public static void main(String args[]){  
/*		System.out.println(gregtaer[0]);
		int counter=0;
		for (int x = 6; x>counter; x--, ++counter)  Line 14 
        {
            System.out.print(" " + counter);
        }*/
		Test1 t=new Test1();
	/*	int x=10;  
		System.out.println(x++);
		System.out.println(++x);
		System.out.println(x--);
		System.out.println(--x); 
		System.out.println(x++ + ++x);*/
	//	
		//t.primeNo(43);
		
	//	t.pallindrome("init");
		
	//	t.factorial(10);
		
/*		Integer i = new Integer(10);
	      Integer j = new Integer(20);
	    String ij=  swap(i, j);
	    String[] arr=ij.split("_");
	    String ju=arr[1];
	    String iu=arr[0];
	      System.out.println("i = " + iu + ", j = " + ju);*/
		
		//print(6);
		
		//int n=t.recfactorial(5);
		
		int n=t.recfibonacci(5, 0);
	    System.out.println(n);
	   
	}
	

	public static void main (){
		
	}
	
	public static void main (String ar1, String ar2){
		
	}
	

	
	public static void print(int n){
		
		
		for(int i=1;i<=n;i++){
			
			for(int j=n-i;j>=1;j--){	
			   System.out.print(" ");		   
			}
			for(int k=1;k<=i;k++){
				System.out.print("#");	
				
			}
			System.out.println("");
				
			}
		
		
		 
	}

	 public static String swap(Integer i, Integer j) 
	   {
	      Integer temp = new Integer(i);
	      i = j;
	      j = temp;
	      return i+"_"+j;
	   }
	 
public void swapstring(){

	String a="Hello";
	String b="World";
	int c=b.length();
	 a=a+b;
	 System.out.println(a);

	 b=a.substring(0, a.length()-b.length());
	
 
	 System.out.println(b);
	 
	 a=a.substring(a.length()-b.length(), a.length());
		
	 System.out.println(a);
}
public void fibonacci(){
	
	int i=0;
	int j=1;
	System.out.println(i);
	System.out.println(j);
	for (int k=1;k<10;k++){			
		int p=i+j;
		i=j;
		j=p;
	System.out.println(p);		
	}
	
}
// 0 1 1 2 3 5 8 13....
public int recfibonacci(int k, int s){
	int i=0;
	int j=1;
	
	int sum=s+j;
	k--;
	recfibonacci(k, sum);	
	
	
	return sum;
}
	public void primeNo(int no){
		int flag=0;
		int m=no/2;
		for (int i=2;i<=m;i++){			
			if (no%i==0){				
				System.out.println("not prime");
				flag=1;
				break;
			}		
		}
		if(flag==0){
		System.out.println("prime");
		}
	}

public void pallindrome(String s){
	char[] newChar=null;
	newChar=s.toCharArray();
	String newStr="";

	StringBuilder b=new StringBuilder();
	for (int i=newChar.length-1;i>=0;i--){
	newStr=newStr+newChar[i];	
	}
	System.out.println(b);
	if(newStr.trim().equals(s.trim())){
		System.out.println("pallindrome");
	}else{
		System.out.println("not pallindrome");
	}
}

public void factorial(int no){
	
	int fact=1;
	for (int i=no;i>=1;i--){
		
		 fact=fact*i;
		 
	}
	System.out.println(fact);
}

public int recfactorial(int n){

	if(n==1)
		return 1;
	else
	return n*recfactorial(n-1);
}



}