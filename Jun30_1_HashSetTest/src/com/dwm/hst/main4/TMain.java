package com.dwm.hst.main4;

import java.util.Scanner;

public class TMain {
	public static void main(String[] args) {
		// �ҹ��� 10
		// �빮�� 5
		// ���� 3
		// Ư�� 0
		 //Scanner sc = new Scanner(System.in);
	     String source = "name88@shinhan.com";
	     String result = "";
	     char temp = 0;
	     
	     for(int i = 0;i < source.length();i++) {
	    	 temp = source.charAt(i);
	    	 
	    	 // Ư�����ڸ� ������ �ƽ�Ű�ڵ� ��
	    	 if(temp > 32) {
	    		 // �����̸�
	    		 if(48 <= temp && temp <= 57) {
	    			 temp += 3;
	    			 if(temp > 57) {
	    				 temp -= 10;
	    			 }
	    		 } else if(65 <= temp && temp <= 90) {
	    			 // �빮��
	    			 temp += 5;
	    			 if(temp > 90) {
	    				 temp -= 26;
	    			 }
	    		 } else if(97 <= temp && temp <= 122) {
	    			 // �ҹ���
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
