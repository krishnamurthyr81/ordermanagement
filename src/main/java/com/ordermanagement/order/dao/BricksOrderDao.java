package com.ordermanagement.order.dao;

import java.util.List;

import com.ordermanagement.order.model.BricksOrder;

public interface BricksOrderDao {
	
	long saveBricksOrder(BricksOrder bricksOrder);
	BricksOrder getBricksOrder(long orderReference);
	   List<BricksOrder> getBricksOrderList();
	   long updateBricksOrder(long orderReference,BricksOrder bricks);


}
