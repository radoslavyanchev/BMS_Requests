package com.bms.model;

import com.bsm.exception.UsernameException;

public class User {
	private int id;
	private String name;
	private int deparment;
	private String password;
	
	public User(int id, String name, int deparment, String password) {
		this.id = id;
		setName(name);
		setDeparment(deparment);
		setPassword(password);
	}

	public User(String name, int deparment, String password) {
		setName(name);
		setDeparment(deparment);
		setPassword(password);
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
		if (name != null) {
			if (name.matches(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)")) {
				this.name = name;
			}
		} else {
			try {
				throw new UsernameException("Your username is incorrect.Please try again!");
			} catch (UsernameException e) {
				e.getMessage();
			}
		}
	}

	public int getDeparment() {
		return deparment;
	}

	public void setDeparment(int deparment) {
		this.deparment = deparment;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
