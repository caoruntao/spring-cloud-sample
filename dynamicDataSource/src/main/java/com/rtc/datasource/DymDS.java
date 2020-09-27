package com.rtc.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DymDS extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DymDSHolder.getHodler();
	}

}
