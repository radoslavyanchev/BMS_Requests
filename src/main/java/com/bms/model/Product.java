package com.bms.model;

import com.bsm.exception.ProductException;

public class Product {
	private int id;
	private String name;
	private Department deparment;

	public Product(int id, String name, Department deparment) {
		this.id = id;
		try {
			setName(name);
		} catch (ProductException e) {
			e.getMessage();
			e.printStackTrace();
		}
		setDeparment(deparment);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws ProductException {
		if (name.length() > 3) {
			this.name = name;
		} else {
			throw new ProductException("Invalid name for product");
		}
	}

	public Department getDeparment() {
		return deparment;
	}

	public void setDeparment(Department deparment) {
		if (deparment != null) {
			this.deparment = deparment;
		}

	}

}
