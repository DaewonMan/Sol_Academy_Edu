package com.dwm.hst.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class HSTMain {
	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<>();
		int [] arr = new int[6];
		ArrayList<Integer> al;
		
		while(hs.size() < 6) {
			hs.add(NumberGenerator.getNG().nextInt());
		}
		
		// HashSet -> ArrayList -> array -> Arrays.sort를 이용한 정렬
		/*al = new ArrayList<>(hs);
		for (int i = 0;i < 6; i++) {
			//System.out.println(i);
			arr[i] = al.get(i);
		}
		
		Arrays.sort(arr);
		for (int i : arr) {
			System.out.println(i);
		}*/
		
		// HashSet -> Iterator -> ArrayList -> ArrayList내 정렬
		Iterator<Integer> it = hs.iterator();
		al = new ArrayList<>();
		while(it.hasNext()) {
			//System.out.println(it.next());
			al.add(it.next());
		}
		al.sort(null);
		for (int i : al) {
			System.out.println(i);
		}
		
	}
}
