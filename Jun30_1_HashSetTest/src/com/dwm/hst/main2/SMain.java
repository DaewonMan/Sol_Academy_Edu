package com.dwm.hst.main2;

import java.util.StringTokenizer;

public class SMain {
	public static void main(String[] args) {
		String str = "3people unFollowed me";
		String [] arrStr = str.split(" ");
		String result = "";
		
		for (int i = 0;i < arrStr.length;i++) {
			result += arrStr[i].substring(0, 1).toUpperCase() + arrStr[i].substring(1).toLowerCase();
			// 마지막 인덱스에 공백 없게
			if(i < arrStr.length-1) {
				result += " ";				
			}
		}
		System.out.println(result);
		///////////////////////////////////////////////////////
		StringTokenizer st = new StringTokenizer(str);
        String result2 = "";
        String tempStr = "";
        while(st.hasMoreTokens()) {
        	tempStr = st.nextToken();
        	result2 += tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1).toLowerCase();
        	//System.out.println(tempStr.substring(1).toLowerCase());
           
        	if(st.hasMoreTokens()) result2 += " ";
        }
        System.out.println(result2);
	}
}
