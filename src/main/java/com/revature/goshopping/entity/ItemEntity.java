package com.revature.goshopping.entity;

import javax.persistence.*;

import com.revature.goshopping.dto.Item;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private int id;

	private float price;

	private String name;

	@Column(columnDefinition="text", nullable = false)
	private String description;

	@Column(columnDefinition="text")
	private String img;
	
	private Boolean forSale;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "item_tag", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<TagEntity> tags = new HashSet<>();

	public ItemEntity() {
		super();
	}

	public ItemEntity(float price, String name, String description, String img, Boolean forSale) {
		super();
		this.price = price;
		this.name = name;
		this.description = description;
		this.img = img;
		this.forSale = forSale;
	}

	public ItemEntity(Item item) {
		this.price = item.getPrice();
		this.name = item.getName();
		this.description = item.getDescription();
		this.img = item.getImg();
	}

	public void addTag(TagEntity tag) {
		tags.add(tag);
	}

	public int getId() {
		return id;
	}

	public void setId(int itemID) {
		this.id = itemID;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Boolean getForSale() {
		return forSale;
	}

	public void setForSale(Boolean forSale) {
		this.forSale = forSale;
	}

	public Set<TagEntity> getTags() {
		return tags;
	}

	public void setTags(Set<TagEntity> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ItemEntity [id=" + id + ", price=" + price + ", name=" + name + ", description=" + description
				+ ", tags=" + tags + "]";
	}

}
