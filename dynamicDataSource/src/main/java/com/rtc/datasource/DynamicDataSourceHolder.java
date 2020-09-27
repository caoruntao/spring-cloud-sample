package com.rtc.datasource;

public class DynamicDataSourceHolder {
	private static final String MASTER = "master";
	private static final String SLAVE01 = "slave01";
	private static final ThreadLocal<String> holder = new ThreadLocal<>();
	
	public static String getDataSourceKey() {
		return holder.get();
	}
	public static void setDataSourceKey(String dataSourceKey) {
		holder.set(dataSourceKey);
	}
	
	public static void markMaster() {
		setDataSourceKey(MASTER);
	}
	
	public static void markSlave01() {
		setDataSourceKey(SLAVE01);
	}
	
}
