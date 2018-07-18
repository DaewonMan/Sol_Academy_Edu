package com.dwm.hst.main4;

public class SMain {
	public static void main(String[] args) {
		int iCtemp = 34, iTtemp = 7;
		int cnt = 0;
		while (iCtemp != iTtemp) {
			if (iCtemp < iTtemp) {
				if (iCtemp + 10 <= iTtemp) {
					iCtemp += 10;
				} else if (iCtemp + 5 <= iTtemp) {
					iCtemp += 5;
				} else {
					iCtemp += 1;
				}
				cnt++;
			} else {
				if (iCtemp > iTtemp) {
					if (iCtemp >= iTtemp + 10) {
						iCtemp -= 10;
					} else if (iCtemp >= iTtemp + 5) {
						iCtemp -= 5;
					} else {
						iCtemp -= 1;
					}
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}