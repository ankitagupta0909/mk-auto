package com.markit.demo;

public class LUR {

	public static void main(String[] args){
/*	int arr[]={4,5,1,2,3};
	
	for(int i=0;i<arr.length;i++){
		if(arr[i]>arr[i+1]){
			System.out.println(arr[i]);
			break;
		}		
	}
	
	*/
		
	/*	String orig="catty";
		String anag="actt";
		int j=0;
		for(int i=0;i<anag.length();i++){			
			if(orig.contains(""+anag.charAt(i))){
				orig=orig.replace(anag.charAt(i), ' ');
				anag=anag.replace(anag.charAt(i), ' ');
			}					
			}
		
		if(anag.equals("")){
			System.out.println("anagram");
		}else{
			System.out.println("Not an anagram");			
		}*/
		
	
/*		int[] arr={3,2,8,5,10,9};
		
		int highest=0;
		int sec_highest=0;
		
		for(int i=0;i<arr.length-1;i++){			
			int temp=arr[i+1];
			if(arr[i]>temp){
				highest=arr[i];
				sec_highest=temp;
			} else if(temp>highest){
				sec_highest=highest;
				highest=temp;
			}
		}		
		System.out.println(sec_highest);
		
		
	*/
	
/*	String a="NITIN";
	String b="";
	for(int i=a.length()-1;i>=0;i--){
		
		 b=b+a.charAt(i);
	}
		
	System.out.println(b);*/
		
	
	
	
	int[] arr={3,2,8,5,10,9};
	
	for(int i=0;i<arr.length;i++){
		for(int j=i+1;j<arr.length;j++){
		
		if(arr[i]>arr[j]){
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;			
		}
		}
		
		
	}
	
	
	System.out.println(arr[arr.length-2]);
	
	
	
	
}
}