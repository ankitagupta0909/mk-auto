package com.markit.demo;

public class BubbleSort {

	public static void main(String[] args) {
		
		sortArray();

		
	} 
	
	
	public void putSongsArtists(){
		String [] arrSongs={"ASong","BSong2","CSong3"};
		String [] arrArtists={"Art1","Art2","Art3"};		
		for(int i=0;i<arrSongs.length;i++){			
			System.out.println(arrSongs[i]);
		}
	    for(int i=0;i<arrArtists.length;i++){			
			System.out.println(arrArtists[i]);
		}
	}
	
	public static void sortArray(){
		String [] arrSongs={" despacito"," I can be ur hero baby"," I am not afraid"};	
		for(int i=0;i<arrSongs.length;i++){
			for(int j=i+1;j<arrSongs.length;j++){			
				if(arrSongs[i].trim().compareTo(arrSongs[j].trim())>0){					
					String temp=arrSongs[i];
					arrSongs[i]=arrSongs[j];
					arrSongs[j]=temp;
				}
			}
		}		
		for(String st:arrSongs)
		System.out.println(st);
	
	}
	
	
}
