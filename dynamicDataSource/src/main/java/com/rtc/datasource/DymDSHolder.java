package com.rtc.datasource;

public class DymDSHolder {
	private static final String MASTER = "master";
	private static final String SLAVE01 = "slave01";
	private static ThreadLocal<String> holder = new ThreadLocal<>();
	
	public static void setHolder(String value) {
		holder.set(value);
	}
	
	public static String getHodler() {
		return holder.get();
	}
	
	public static void markMaster() {
		setHolder(MASTER);
	}
	
	public static void markSlave01() {
		setHolder(SLAVE01);
	}
}
