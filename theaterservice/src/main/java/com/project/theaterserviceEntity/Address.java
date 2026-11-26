package com.project.theaterserviceEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Address {
	@Id
	private int id;
	private String area;
	private String city;
	private String state;
	private long pincode;
	public Address(int id,  String area, String city, String state, long pincode) {
		super();
		this.id = id;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	
	
	public Address() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

}
