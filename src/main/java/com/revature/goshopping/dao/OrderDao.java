package com.revature.goshopping.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.goshopping.entity.OrderEntity;

@Repository
@Transactional
public class OrderDao {
	@Autowired
    SessionFactory sessionFactory;
    
    public void addOrder(OrderEntity order) throws Exception {
        sessionFactory.getCurrentSession().persist(order);
    }

    public OrderEntity getOrder(int id) throws Exception {
        return sessionFactory.getCurrentSession().get(OrderEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<OrderEntity> getOrdersForUser(int id) throws Exception {
        return sessionFactory
            .getCurrentSession()
            .createQuery("from OrderEntity where userId = :id")
            .setParameter("id", id)
            .list();
    }

    @SuppressWarnings("unchecked")
    public List<OrderEntity> getAllOrders() throws Exception {
        return sessionFactory
            .getCurrentSession()
            .createQuery("from OrderEntity")
            .list();
    }

    public void removeOrder(int id) throws Exception {
        OrderEntity order = new OrderEntity();
        order.setId(id);
        sessionFactory.getCurrentSession().delete(order);
    }
}
