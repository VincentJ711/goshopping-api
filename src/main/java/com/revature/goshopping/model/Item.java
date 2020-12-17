package com.revature.goshopping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int item_id;
	private float price;
	private String name;
	private String description;
	@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinTable(name="item_tags",
		joinColumns = @JoinColumn(name="item_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
		)
	private Set<Tag> tags = new HashSet<Tag>();
	
	public Item() {
		super();
	}
	public Item(float price, String name, String description) {
		super();
		this.price = price;
		this.name = name;
		this.description = description;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
}
