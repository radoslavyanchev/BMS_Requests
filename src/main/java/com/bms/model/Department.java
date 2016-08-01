package com.bms.model;

public class Department {
	int id;
	String name;

	public Department() {
		super();
	} 
	
	// Create department form DB
	public Department(int id, String name) {
		this.id = id;
		setName(name);
	}
	//Create department in DB
	public Department(String name) {
		setName(name);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public String toString() { 
	    return String.valueOf(this.id);
	} 
}
