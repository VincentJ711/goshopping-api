package com.revature.goshopping.dto;

import java.util.ArrayList;
import java.util.List;

import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.TagEntity;

public class Item {
	private int id;

	private Float price;

	private String description;

	private String name;

	private List<Tag> tags = new ArrayList<>();

	@Override
	public String toString() {
		return "Item [id=" + id + ", price=" + price + ", description=" + description + ", name=" + name + ", tags="
				+ tags + "]";
	}

	// for jackson
	protected Item() {

	}

	public Item(int id, Float price, String description, String name, List<Tag> tags) {
		this.id = id;
		this.price = price;
		this.description = description;
		this.name = name;
		this.tags = tags;
	}
	
	public Item(Float price, String description, String name) {
		this.price = price;
		this.description = description;
		this.name = name;
	}

	public Item(ItemEntity itemEntity) {
		this.id = itemEntity.getId();
		this.price = itemEntity.getPrice();
		this.description = itemEntity.getDescription();
		this.name = itemEntity.getName();
		for (TagEntity t : itemEntity.getTags()) {
			this.tags.add(new Tag(t));
		}
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
