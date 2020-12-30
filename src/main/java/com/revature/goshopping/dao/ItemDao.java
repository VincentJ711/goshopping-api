package com.revature.goshopping.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

	public ItemEntity addItem(ItemEntity item) throws Exception {
		Session session = sessionFactory.getCurrentSession();
			for(TagEntity t : item.getTags()) {
				session.saveOrUpdate(t);
			}
			session.persist(item);
			return item;
	}

	public void removeItem(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		ItemEntity item = session.get(ItemEntity.class, id);
		item.setForSale(false);
	}

	public ItemEntity updateItem(ItemEntity item) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		for(TagEntity t : item.getTags()) {
			session.saveOrUpdate(t);
		}
		session.update(item);
		return item;
	}

	public ItemEntity getItem(int id) throws Exception {
		return sessionFactory.getCurrentSession().get(ItemEntity.class, id);
	}

	public TagEntity getTag(String name) throws Exception {
		return (TagEntity) sessionFactory.getCurrentSession().createQuery("from TagEntity where name=:name")
				.setParameter("name", name).uniqueResult();
	}

	public List<TagEntity> getTags() throws Exception {
		return sessionFactory.getCurrentSession().createQuery(
				"select t from TagEntity t", TagEntity.class).list();
	}

	public List<ItemEntity> searchItems(String text, String tag,
			Integer quantity, Integer page, String sort) {
		Session session = sessionFactory.getCurrentSession();
		Query<ItemEntity> query;

		if (text != null && tag != null) {
			String queryString = "select i from ItemEntity i, IN (i.tags) t " +
					"where LOWER(i.name) like :text and (t.name=:tag and i.forSale=true) order by " + orderBy(sort);
			query = session.createQuery(queryString, ItemEntity.class)
					.setParameter("text", "%" + text.toLowerCase() + "%")
					.setParameter("tag", tag);
		} else if (text != null) {
			String queryString = "select i from ItemEntity i " +
					"where LOWER(i.name) like :text and i.forSale=true order by " + orderBy(sort);
			query = session.createQuery(queryString, ItemEntity.class)
					.setParameter("text", "%" + text.toLowerCase() + "%");
		} else if (tag != null) {
			String queryString = "select i from ItemEntity i, IN (i.tags) t " +
					"where t.name=:tag and i.forSale=true order by " + orderBy(sort);
			query = session.createQuery(queryString, ItemEntity.class)
					.setParameter("tag", tag);
		} else {
			query = session.createQuery("from ItemEntity where forSale=true order by " + orderBy(sort),
					ItemEntity.class);
		}

		if (quantity != null && quantity > 0) {
			query.setMaxResults(quantity);

			if (page != null && page > -1) {
				query.setFirstResult(quantity * page);
			}
		}

		return query.list();
	}
	
	private String orderBy(String sort) {
		if(sort == null) {
			return "id";
		} else if(sort.equals("price_asc")) {
			return "price ASC";
		} else if(sort.equals("price_desc")) {
			return "price DESC";
		} else if(sort.equals("name_asc")) {
			return "name ASC";
		} else if(sort.equals("name_desc")) {
			return "name DESC";
		} else {
			return "id";
		}
	}
}
