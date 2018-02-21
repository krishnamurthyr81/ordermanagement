package com.ordermanagement.order.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordermanagement.order.model.BricksOrder;

@Repository
public class BricksOrderDaoImpl implements BricksOrderDao{
	
	  
	@Autowired
	   private SessionFactory sessionFactory;
	
	public long saveBricksOrder(BricksOrder bricksOrder) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(bricksOrder);
	      return bricksOrder.getOrderReference();

	}

	public BricksOrder getBricksOrder(long orderReference) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(BricksOrder.class, orderReference);

	}

	public List<BricksOrder> getBricksOrderList() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<BricksOrder> cq = cb.createQuery(BricksOrder.class);
	      Root<BricksOrder> root = cq.from(BricksOrder.class);
	      cq.select(root);
	      Query<BricksOrder> query = session.createQuery(cq);
	      return query.getResultList();

	}

	public long updateBricksOrder(long orderReference,BricksOrder bricks) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		BricksOrder bricks2 = session.byId(BricksOrder.class).load(orderReference);
		bricks2.setNumberOfBricks(bricks.getNumberOfBricks());
	    session.flush();
	    if(bricks2!=null){
	    	return orderReference;
	    }else{
	    	return 0;
	    }
		

	}


}
