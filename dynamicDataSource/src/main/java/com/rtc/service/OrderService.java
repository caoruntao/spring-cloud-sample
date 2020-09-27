package com.rtc.service;

import com.rtc.entity.Order;
import com.rtc.entity.OrderItem;

public interface OrderService {
	public void addOrder(Order order);
	
	public void addOrderItem(OrderItem orderItem);
}
