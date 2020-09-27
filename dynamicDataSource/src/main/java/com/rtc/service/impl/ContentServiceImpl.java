package com.rtc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtc.entity.Content;
import com.rtc.mapper.ContentMapper;
import com.rtc.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public List<Content> queryCotents() {
		List<Content> contents = contentMapper.queryCotents();
		for (Content content : contents) {
			System.err.println(content.getId());
		}
		return contents;
	}

	@Override
	public void addContent(Content content) {
		contentMapper.addContent(content);
	}

}
