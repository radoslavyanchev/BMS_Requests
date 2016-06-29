package com.bms.model;

public class Request {
	private int id;
	private Department fromDeparment;
	private Department forDeparment;

	public Request(int id, Department fromDeparment, Department forDeparment) {
		this.id = id;
		this.fromDeparment = fromDeparment;
		this.forDeparment = forDeparment;
	}

	public Request(Department fromDeparment, Department forDeparment) {
		this.fromDeparment = fromDeparment;
		this.forDeparment = forDeparment;
	}

	public Department getFromDeparment() {
		return fromDeparment;
	}

	private void setFromDeparment(Department fromDeparment) {
		if (fromDeparment != null) {
			this.fromDeparment = fromDeparment;
		}

	}

	public Department getForDeparment() {
		return forDeparment;
	}

	private void setForDeparment(Department forDeparment) {
		if (forDeparment != null) {
			this.forDeparment = forDeparment;
		}
	}

	public int getId() {
		return id;
	}

}
