package com.rtc.mapper;

import java.util.List;

import com.rtc.entity.Content;

public interface ContentMapper {
	public List<Content> queryCotents();
	
	public void addContent(Content content);
}
