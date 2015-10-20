package com.landhightech.service.test;

public class A {
	public static int i;
	static {
		try {
			int a = i/0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void test(){
		System.out.println(111);
	}
}
