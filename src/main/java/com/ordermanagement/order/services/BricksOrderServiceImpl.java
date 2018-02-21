package com.ordermanagement.order.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordermanagement.order.dao.BricksOrderDao;
import com.ordermanagement.order.model.BricksOrder;


@Service
@Transactional(readOnly = true)

public class BricksOrderServiceImpl implements BricksOrderService{
	
	@Autowired
	   private BricksOrderDao bricksDao;
	
	@Transactional
	public long saveBricksOrder(BricksOrder bricksOrder) {
		// TODO Auto-generated method stub
		return bricksDao.saveBricksOrder(bricksOrder);
	}
	
	public BricksOrder getBricksOrder(long orderReference) {
		// TODO Auto-generated method stub
		return bricksDao.getBricksOrder(orderReference);
	}
	
	public List<BricksOrder> getBricksOrderList() {
		// TODO Auto-generated method stub
		return bricksDao.getBricksOrderList();
	}
	
	@Transactional
	public long updateBricksOrder(long orderReference, BricksOrder bricks) {
		// TODO Auto-generated method stub
		 orderReference = bricksDao.updateBricksOrder(orderReference, bricks);
		return orderReference;
	}
}
