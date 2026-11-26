package com.project.customerservice.dto;

public class TheaterCDTO {
	private long id;
	 private String name;
	 private String description;
	 private long contactnumber;
	 private String mailid;
	 private int totalscreens;
	 private String status;
	 private String city;
	 public long getId() {
		 return id;
	 }
	 public void setId(long id) {
		 this.id = id;
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
	 public long getContactnumber() {
		 return contactnumber;
	 }
	 public void setContactnumber(long contactnumber) {
		 this.contactnumber = contactnumber;
	 }
	 public String getMailid() {
		 return mailid;
	 }
	 public void setMailid(String mailid) {
		 this.mailid = mailid;
	 }
	 public int getTotalscreens() {
		 return totalscreens;
	 }
	 public void setTotalscreens(int totalscreens) {
		 this.totalscreens = totalscreens;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
	 public String getCity() {
		 return city;
	 }
	 public void setCity(String city) {
		 this.city = city;
	 }

}
