package com.revature.goshopping.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.TagEntity;

public class Item {
	protected int id;

	protected Float price;

	protected String description;

	protected String name;
	
	protected String img;

	protected List<Tag> tags = new ArrayList<>();

	// for jackson
	protected Item() {

	}

	public Item(int id, Float price, String description, String name, String img, List<Tag> tags) {
		this.id = id;
		this.price = price;
		this.description = description;
		this.name = name;
		this.img = img;
		this.tags = tags;
	}
	
	public Item(Float price, String description, String name, String img) {
		this.price = price;
		this.description = description;
		this.name = name;
		this.img = img;
	}

	public Item(ItemEntity itemEntity) {
		this.id = itemEntity.getId();
		this.price = itemEntity.getPrice();
		this.description = itemEntity.getDescription();
		this.name = itemEntity.getName();
		this.img = itemEntity.getImg();
		for (TagEntity t : itemEntity.getTags()) {
			this.tags.add(new Tag(t));
		}
	}

	@JsonIgnore
	public boolean isValid() {
		try {
			if(this.price < 0.0 || (this.description == null) || this.name.equals(""))
				return false;
			return true;
		} catch(NullPointerException e) {
			return false;
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
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", price=" + price +
				", description='" + description + '\'' +
				", name='" + name + '\'' +
				", img='" + img + '\'' +
				", tags=" + tags +
				'}';
	}
}
