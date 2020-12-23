package com.revature.goshopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.revature.goshopping.dao.ItemDao;
import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.Item;
import com.revature.goshopping.dto.Tag;
import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.TagEntity;
import com.revature.goshopping.exception.ServiceException;

@Service
public class ItemService {
	@Autowired
	ItemDao dao;

	public Item getItem(int id) throws Exception {
		return new Item(dao.getItem(id));
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
		if (auth == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "No auth");
		} else if (auth.isAdmin()) {
			ItemEntity itemE = new ItemEntity(item);
			for (Tag t : item.getTags()) {
				TagEntity tag = dao.getTag(t.getName());
				if (tag == null)
					tag = new TagEntity(t.getName());
				itemE.addTag(tag);
			}
			itemE.setId(id);
			return new Item(dao.addItem(itemE));
		} else {
			throw new ServiceException(HttpStatus.FORBIDDEN, "Not admin");
		}
	}

	public List<Item> getItems() throws Exception {
		List<Item> items = new ArrayList<Item>();
		for (ItemEntity i : dao.getItems()) {
			items.add(new Item(i));
		}
		return items;
	}

	public List<Item> searchItems(String search) throws Exception {
		List<Item> items = new ArrayList<Item>();
		for (ItemEntity i : dao.searchItems(search)) {
			items.add(new Item(i));
		}
		return items;
	}

	public List<Item> searchItems(String search, String tag) throws Exception {
		List<Item> items = new ArrayList<Item>();
		for (ItemEntity i : dao.searchItems(search, tag)) {
			items.add(new Item(i));
		}
		return items;
	}

	public List<Item> getItemsbyTag(String tag) throws Exception {
		List<Item> items = new ArrayList<Item>();
		for (ItemEntity i : dao.getItemsbyTag(tag)) {
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
		if (auth == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "No auth");
		} else if (auth.isAdmin()) {
			ItemEntity itemE = new ItemEntity(item);
			for (Tag t : item.getTags()) {
				TagEntity tag = dao.getTag(t.getName());
				if (tag == null)
					tag = new TagEntity(t.getName());
				itemE.addTag(tag);
			}
			return new Item(dao.addItem(itemE));
		} else {
			throw new ServiceException(HttpStatus.FORBIDDEN, "Not admin");
		}
	}
}
