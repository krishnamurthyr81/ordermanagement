package com.ordermanagement.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Bricks")
public class BricksOrder {
	
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long orderReference;
	   private Long numberOfBricks;
	   
	public BricksOrder(Long orderReference, Long numberOfBricks) {
		super();
		this.orderReference = orderReference;
		this.numberOfBricks = numberOfBricks;
	}
	public BricksOrder(long numberOfBricks) {
		// TODO Auto-generated constructor stub
		super();
		this.numberOfBricks = numberOfBricks;
	}
	public Long getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(Long orderReference) {
		this.orderReference = orderReference;
	}
	public Long getNumberOfBricks() {
		return numberOfBricks;
	}
	public void setNumberOfBricks(Long numberOfBricks) {
		this.numberOfBricks = numberOfBricks;
	}


}
