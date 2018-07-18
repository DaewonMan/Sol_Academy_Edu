package com.dwm.hst.main4;

import java.util.Scanner;

public class FMain {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
	     String sBuffer = sc.next();
	     int [][] arr = new int[4][4];
	     int temp = 0, sum = 0;
	     
	     
	     if(sBuffer.length() < 9) {
	    	 System.out.println("-1");	    	 
	     } else {
	    	 for(int i = 0;i < 3;i++) {
	    		 temp = 0;
	    		 for(int j = 0;j < 3;j++) {
	    			 arr[i][j] = (int)sBuffer.charAt((i * 3) + j) - 48;
	    			 temp += arr[i][j];
	    		 }
	    		 arr[i][3] = temp;
	    		 sum += temp;
	    	 }
	    	 
	    	 for(int i = 0;i < 3;i++) {
	    		 temp = 0;
	    		 for(int j = 0;j < 3;j++) {
	    			 temp += arr[j][i];
	    		 }
	    		 arr[3][i] = temp;
	    	 }
	    	 
	    	 arr[3][3] = sum;
	    	 ////////////////////////
	    	 for(int i = 0;i < 4;i++) {
	    		 for(int j = 0;j < 4;j++) {
	    			 System.out.print(arr[i][j]);
	    			 if(i != 3 || j != 3) {
	    				 System.out.print(",");
	    			 }
	    		 }
	    	 }
	    	 
	    	 
	     }
	     
	}
}
