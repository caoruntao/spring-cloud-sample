package com.rtc.aspect;

import org.aspectj.lang.JoinPoint;
import org.springframework.util.StringUtils;

import com.rtc.datasource.DynamicDataSourceHolder;

public class DataSourceAspect {
	private String[] methodPattern = new String[] {"query", "select", "find", "get"};
	
	public void before(JoinPoint point) {
		String methodName = point.getSignature().getName();
		System.err.println(isSlave(methodName));
		if(isSlave(methodName)) {
			DynamicDataSourceHolder.markSlave01();
		}else {
			DynamicDataSourceHolder.markMaster();
		}
	}
	
	public boolean isSlave(String methodName) {
		for (String pattern : methodPattern) {
			if(StringUtils.startsWithIgnoreCase(methodName, pattern)) {
				return true;
			}
		}
		return false;
	}
}
