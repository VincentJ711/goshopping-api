package com.revature.goshopping.dto;

import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.ItemOrderEntity;
import com.revature.goshopping.entity.TagEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderItem extends Item {
	private int quantity;

	// for jackson
	public OrderItem() {

	}

	public OrderItem(int itemID, int quantity) {
		this.id = itemID;
		this.quantity = quantity;
	}

	public OrderItem(int id, float price, String description, String name, String img, List<Tag> tags, int quantity) {
		super(id, price, description, name, img, tags);
		this.quantity = quantity;
	}

	public OrderItem(ItemOrderEntity ioe) {
		ItemEntity item = ioe.getItem();
		List<Tag> tags = new ArrayList<>();

		for (TagEntity tag : item.getTags()) {
			tags.add(new Tag(tag));
		}

		this.id = (item.getId());
		this.price = item.getPrice();
		this.description = item.getDescription();
		this.name = item.getName();
		this.img = item.getImg();
		this.tags = tags;
		this.quantity = ioe.getQuantity();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", price=" + price +
				", quantity=" + quantity +
				", description='" + description + '\'' +
				", name='" + name + '\'' +
				", img='" + img + '\'' +
				", tags=" + tags +
				'}';
	}
}
