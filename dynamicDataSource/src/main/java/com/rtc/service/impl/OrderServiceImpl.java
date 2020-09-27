package com.rtc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtc.entity.Order;
import com.rtc.entity.OrderItem;
import com.rtc.mapper.OrderItemMapper;
import com.rtc.mapper.OrderMapper;
import com.rtc.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Override
	public void addOrder(Order order) {
		orderMapper.addOrder(order);
	}
	@Override
	public void addOrderItem(OrderItem orderItem) {
		orderItemMapper.addOrderItem(orderItem);		
	}
	
	

}
