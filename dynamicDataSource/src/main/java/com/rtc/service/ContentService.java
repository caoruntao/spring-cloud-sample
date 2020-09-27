package com.rtc.service;

import java.util.List;

import com.rtc.entity.Content;

public interface ContentService {
	public List<Content> queryCotents();
	
	public void addContent(Content content);
}
