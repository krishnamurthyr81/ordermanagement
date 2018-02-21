package com.ordermanagement.order.services;

import java.util.List;

import com.ordermanagement.order.model.BricksOrder;

public interface BricksOrderService {
	
	long saveBricksOrder(BricksOrder bricksOrder);
	BricksOrder getBricksOrder(long orderReference);
	   List<BricksOrder> getBricksOrderList();
	   long updateBricksOrder(long orderReference,BricksOrder bricks);


}
