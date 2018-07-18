package com.dwm.hst.main4;

import java.util.Arrays;
import java.util.HashSet;

public class FOURMain {
	public static boolean judge(int num) {
		int cnt = 0;
		for (int i = 1; i <= num;i++) {
			if (num % i == 0) {
				cnt++;
			}
		}
		
		if(cnt <= 2) {
			return true;			
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] numbers = {1,2,3,4};
		int arr_Size = 0, result = 0;
		int temp = 0, dot = 0;
		String temp2 = "";
		arr_Size = numbers.length;
		
		HashSet<Integer> hs = new HashSet<>();
		
		for (int i : numbers) {
			hs.add(i);
		}
		
		if(arr_Size < 3) {
			result = -1;
		}
		else if (hs.size() != arr_Size) {
			result = -2;
		} else {
			//System.out.println(arr_Size);
			Arrays.sort(numbers);
			
			for(int i = 0;i < arr_Size;i++) {
				if(i == 0 && numbers[0] == 0) {
					continue;
				}
				for(int j = 0;j < arr_Size;j++) {
					for(int k = 0;k < arr_Size;k++) {
						if(i != j && j != k && i != k) {
							temp = Integer.parseInt(""+ numbers[i] + numbers[j] + numbers[k]);
							temp2 = temp/7. + "";
							i = arr_Size;
							j = arr_Size;
							break;
						}
					}
				}
			}
			
			dot = temp2.indexOf(".");
			result = temp2.charAt(dot+1) - 48;
			
		}
		System.out.println(result);
	}
}
