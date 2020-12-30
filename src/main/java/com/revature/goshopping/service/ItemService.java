package com.revature.goshopping.service;

import com.revature.goshopping.dao.ItemDao;
import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.Item;
import com.revature.goshopping.dto.Tag;
import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.TagEntity;
import com.revature.goshopping.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
	@Autowired
	ItemDao dao;

	public Item getItem(int id) throws Exception {
		ItemEntity item = dao.getItem(id);
		if(item == null)
			throw new ServiceException(HttpStatus.NOT_FOUND);
		return new Item(item);
	}

	public int removeItem(int id, Auth auth) throws Exception {
		if (auth == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "No auth");
		} else if (auth.isAdmin()) {
			dao.removeItem(id);
			return 1;
		} else {
			throw new ServiceException(HttpStatus.FORBIDDEN, "Not admin");
		}
	}

	public Item updateItem(Item item, int id, Auth auth) throws Exception {
		if (!item.isValid()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Empty or null values");
		}
		removeItem(id, auth);
		item.setId(0);
		return addItem(item, auth);
	}

	public List<Item> searchItems(String text, String tag, Integer quantity, Integer page, String sort) throws Exception {
		List<Item> items = new ArrayList<>();
		for (ItemEntity i : dao.searchItems(text, tag, quantity, page, sort)) {
			items.add(new Item(i));
		}
		return items;
	}

	public List<Tag> getTags() throws Exception {
		List<Tag> tags = new ArrayList<Tag>();
		for (TagEntity t : dao.getTags()) {
			tags.add(new Tag(t));
		}
		return tags;
	}

	public Item addItem(Item item, Auth auth) throws Exception {
		if (!item.isValid()) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Empty or null values");
		} else if (auth == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "No auth");
		} else if (auth.isAdmin()) {
			ItemEntity itemE = new ItemEntity(item);
			for (Tag t : item.getTags()) {
				TagEntity tag = dao.getTag(t.getName());
				if (tag == null)
					tag = new TagEntity(t.getName());
				itemE.addTag(tag);
			}
			itemE.setForSale(true);
			;
			return new Item(dao.addItem(itemE));
		} else {
			throw new ServiceException(HttpStatus.FORBIDDEN, "Not admin");
		}
	}
}
