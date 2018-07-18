package com.dwm.hst.main4;

import java.util.Scanner;

public class TMain {
	public static void main(String[] args) {
		// 소문자 10
		// 대문자 5
		// 숫자 3
		// 특수 0
		 //Scanner sc = new Scanner(System.in);
	     String source = "name88@shinhan.com";
	     String result = "";
	     char temp = 0;
	     
	     for(int i = 0;i < source.length();i++) {
	    	 temp = source.charAt(i);
	    	 
	    	 // 특수문자를 포함한 아스키코드 수
	    	 if(temp > 32) {
	    		 // 숫자이면
	    		 if(48 <= temp && temp <= 57) {
	    			 temp += 3;
	    			 if(temp > 57) {
	    				 temp -= 10;
	    			 }
	    		 } else if(65 <= temp && temp <= 90) {
	    			 // 대문자
	    			 temp += 5;
	    			 if(temp > 90) {
	    				 temp -= 26;
	    			 }
	    		 } else if(97 <= temp && temp <= 122) {
	    			 // 소문자
	    			 temp += 10;
	    			 if(temp > 122) {
	    				 temp -= 26;
	    			 }
	    		 }
	    		 result += temp;
	    	 }
	     }
	     System.out.println(result);
	     
	}
}
