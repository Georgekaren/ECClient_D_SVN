package com.lianmeng.core.product.vo;

public class ProductFilterVo {
	private String id;
	private String name;

	public ProductFilterVo() {
	}

	public ProductFilterVo(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
