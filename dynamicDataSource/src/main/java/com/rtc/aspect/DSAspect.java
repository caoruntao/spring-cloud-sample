package com.rtc.aspect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.lang.JoinPoint;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import com.rtc.datasource.DymDS;
import com.rtc.datasource.DymDSHolder;

public class DSAspect {
	private List<String> slaveMethods = new ArrayList<>();
	
	
	public void setTxAdvice(TransactionInterceptor txAdvice) throws Exception {
		TransactionAttributeSource transactionAttributeSource = txAdvice.getTransactionAttributeSource();
		if(!(transactionAttributeSource instanceof NameMatchTransactionAttributeSource)) {
			return ;
		}
		NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = (NameMatchTransactionAttributeSource) transactionAttributeSource;
		Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
		nameMapField.setAccessible(true);
		Map<String, TransactionAttribute> map = (Map<String, TransactionAttribute>) nameMapField.get(nameMatchTransactionAttributeSource);
		
		for (Entry<String, TransactionAttribute> entry : map.entrySet()) {
			if(entry.getValue().isReadOnly()) {
				slaveMethods.add(entry.getKey());
			}
		}
	}
	
	public void before(JoinPoint point) {
		String name = point.getSignature().getName();
		if(slaveMethods.isEmpty()) {
			return ;
		}
		for (String method : slaveMethods) {
			if(PatternMatchUtils.simpleMatch(method, name)) {
				DymDSHolder.markSlave01();
				break;
			}
		}
	}
}
