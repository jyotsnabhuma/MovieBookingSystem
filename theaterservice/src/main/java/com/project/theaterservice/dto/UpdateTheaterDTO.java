package com.project.theaterservice.dto;

public class UpdateTheaterDTO {
	private String name;
	private String mailid;
	private Long contactnum;
	private int totalscreens;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getTotalscreens() {
		return totalscreens;
	}
	public void setTotalscreens(int totalscreens) {
		this.totalscreens = totalscreens;
	}
	
	

}
