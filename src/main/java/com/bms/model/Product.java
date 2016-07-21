package com.bms.model;

import com.bsm.exception.DeparmentException;
import com.bsm.exception.ProductException;

public class Product {
	private int id;
	private String name;
	private Department deparment;
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Create product from DB
	public Product(int id, String name, Department deparment) {
		this.id = id;
		try {
			setName(name);
			setDeparment(deparment);
		} catch (ProductException | DeparmentException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	// Create new product in DB
	public Product(String name, Department deparment) {
		try {
			setName(name);
			setDeparment(deparment);
		} catch (ProductException | DeparmentException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
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

	public void setDeparment(Department deparment) throws DeparmentException {
		if (deparment != null) {
			this.deparment = deparment;
		} else {
			throw new DeparmentException("Invalid deparment for product");
		}

	}

}
