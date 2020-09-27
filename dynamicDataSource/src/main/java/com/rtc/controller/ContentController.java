package com.rtc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rtc.entity.Content;
import com.rtc.entity.Order;
import com.rtc.entity.OrderItem;
import com.rtc.service.ContentService;
import com.rtc.service.OrderService;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/query")
	public void queryContents() {
		contentService.queryCotents();
	}
	
	@RequestMapping("/add/{request}/{response}")
	public void addContent(@PathVariable String request, @PathVariable String response) {
		Content content = new Content();
		content.setRequest(request);
		content.setResponse(response);
		contentService.addContent(content);
	}
	
	@RequestMapping("/addOrders")
	public void addOrder() {
		for (int i = 0; i < 100; i++) {
			Order order = new Order();
			order.setUserId(i);
			order.setOrderId(i + 10000);
			orderService.addOrder(order);
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order.getOrderId());
			orderItem.setOrderItemId(order.getOrderId() + 10000);
			orderService.addOrderItem(orderItem);
		}
	}
}
