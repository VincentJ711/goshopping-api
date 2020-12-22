package com.revature.goshopping.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.TagEntity;


@Repository
@Transactional
public class ItemDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public void addItem(ItemEntity item) throws Exception{
		sessionFactory.getCurrentSession().persist(item);
	}
	
	public void removeItem(int id) throws Exception{
		ItemEntity item = new ItemEntity();
		item.setId(id);
		sessionFactory.getCurrentSession().delete(item);
	}
	
	public ItemEntity getItem(int id) throws Exception{
		return sessionFactory.getCurrentSession().get(ItemEntity.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemEntity> getItems() throws Exception{
		return sessionFactory.getCurrentSession().createQuery("from ItemEntity").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemEntity> searchItems(String search) throws Exception{
		return sessionFactory.getCurrentSession().createQuery("select i from ItemEntity i "
				+ "where LOWER(i.name) like :search or LOWER(i.description) like :search")
		.setParameter("search", "%" + search.toLowerCase() + "%").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemEntity> searchItems(String search, String name) throws Exception{
			return sessionFactory.getCurrentSession().createQuery("select i from ItemEntity i inner join i.tags t "
					+ "where t.name=:name and (LOWER(i.name) like :search or LOWER(i.name) like :search)")
			.setParameter("name", name)
			.setParameter("search", "%" + search.toLowerCase() + "%").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemEntity> getItemsbyTag(String name) throws Exception{
			return sessionFactory.getCurrentSession().createQuery("select i from ItemEntity i inner join i.tags t "
					+ "where t.name=:name")
			.setParameter("name", name).list();
	}
	
	public TagEntity getTag(String name) throws Exception{
		return (TagEntity) sessionFactory.getCurrentSession().createQuery("from TagEntity where name=:name")
		.setParameter("name", name).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TagEntity> getTags() throws Exception{
		return sessionFactory.getCurrentSession().createQuery("from TagEntity").list();
	}
}
