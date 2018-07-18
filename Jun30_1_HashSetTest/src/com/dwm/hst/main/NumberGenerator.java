package com.dwm.hst.main;

import java.util.Random;

@SuppressWarnings("serial")
public class NumberGenerator extends Random {
	
	private static final NumberGenerator NG = new NumberGenerator();
	
	private NumberGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static NumberGenerator getNG() {
		return NG;
	}
	
	@Override
	public int nextInt() {
		return super.nextInt(45) + 1;
	}
}
