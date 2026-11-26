package com.project.theaterservice.dto;

public class TheaterDTO {
	private String name;
	private String description;
	private String mailid;
	private Long contactnum;
	private String city;
	private int totalscreens;
	public TheaterDTO(String name, String description, String mailid, Long contactnum, String city, int totalscreens) {
		super();
		this.name = name;
		this.description = description;
		this.mailid = mailid;
		this.contactnum = contactnum;
		this.city = city;
		this.totalscreens = totalscreens;
	}
	public int getTotalscreens() {
		return totalscreens;
	}
	public void setTotalscreens(int totalscreens) {
		this.totalscreens = totalscreens;
	}
	public TheaterDTO() {
		super();
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
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public Long getContactnum() {
		return contactnum;
	}
	public void setContactnum(Long contactnum) {
		this.contactnum = contactnum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
