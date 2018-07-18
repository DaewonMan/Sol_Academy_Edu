package com.dwm.hst.main3;

public class NMain {
	public static void main(String[] args) {
		int n = 1, result = 0, cnt = 0;
		int mok = 0, nam = 0;
		
		if(n > 3) {
			for(int i = 1;n > 3;i*=10) {
				nam = n%3;
				n /= 3;
				mok = n;
				
				if(mok <= 4) {
					if(nam == 0) {
						mok -= 1;
						n -= 1;
						nam = 4;
					}
					if(mok == 3) {
						mok = 4;
					}
				}
				cnt = i;
				result += (i * nam);
			}
			result += (cnt * 10 * mok);			
		} else {
			if(n == 3) {
				result = 4;
			} else {
				result = n;
			}
		}
		
		
		System.out.println(result);
	}
}
