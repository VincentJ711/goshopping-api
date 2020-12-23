package com.revature.goshopping.dto;

import com.revature.goshopping.entity.TagEntity;

public class Tag {
	private int id;

	private String name;

	protected Tag() {

	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}

	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Tag(String name) {
		this.name = name;
	}

	public Tag(TagEntity tagEntity) {
		this.id = tagEntity.getId();
		this.name = tagEntity.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
