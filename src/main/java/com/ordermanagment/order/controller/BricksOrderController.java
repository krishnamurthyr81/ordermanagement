package com.ordermanagment.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.order.model.BricksOrder;
import com.ordermanagement.order.services.BricksOrderService;

@RestController
public class BricksOrderController {

   @Autowired
   private BricksOrderService bricksOrderService;

   /*---Add bricks Order---*/
   @PostMapping("/bricksOrder")
   public ResponseEntity<?> saveBricksOrder(@RequestBody BricksOrder bricksOrder) {
      long orderReference = bricksOrderService.saveBricksOrder(bricksOrder);
      return ResponseEntity.ok().body("New Bricks Order has been saved with Order Reference:" + orderReference);
   }

   /*---Get a bricks Order by id---*/
   @GetMapping("/bricksOrder/{id}")
   public ResponseEntity<?> getBricksOrder(@PathVariable("id") long orderReference) {
      BricksOrder bricksOrder = bricksOrderService.getBricksOrder(orderReference);
      if(bricksOrder == null){
       return new ResponseEntity("No Order found for orderReference " + orderReference, HttpStatus.NOT_FOUND);

      }else{
    	  return ResponseEntity.ok().body(bricksOrder);
      }
      
   }

   /*---get all bricks Order---*/
   @GetMapping("/bricksOrders")
   public ResponseEntity<List<BricksOrder>> getBricksOrderList() {
      List<BricksOrder> bricksOrders = bricksOrderService.getBricksOrderList();
      return ResponseEntity.ok().body(bricksOrders);
   }

   /*---Update a bricks order by id---*/
   @PutMapping("/bricksOrder/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long orderReference, @RequestBody BricksOrder bricksOrder) {
	    orderReference  = bricksOrderService.updateBricksOrder(orderReference, bricksOrder);
	    if(orderReference > 0){
      return ResponseEntity.ok().body("Bricks Order has been updated successfully."+ orderReference);
	    }else{
	    	return ResponseEntity.badRequest().body("Bricks Order not updated successfully."+ orderReference);
	    }
   }
   
}
