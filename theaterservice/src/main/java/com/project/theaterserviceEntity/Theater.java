package com.project.theaterserviceEntity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Theater {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 private long id;
	 private String name;
	 private String description;
	 private Long contactnumber;
	 private String mailid;
	 private int totalscreens;
	 private String status;
	 private String city;
	 
	@OneToOne
	    private Address address;
    @OneToMany
    @JsonIgnore
    private List<Show> allplayingshows = new ArrayList<>();
    
     
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
	 public List<Show> getAllplayingshows() {
		 return allplayingshows;
	 }
	 public void setAllplayingshows(List<Show> allplayingshows) {
		 this.allplayingshows = allplayingshows;
	 }
	 public Theater(String name, String description, long contactnumber, String mailid, int totalscreens,
			String status, List<Show> allplayingshows) {
		super();
		this.name = name;
		this.description = description;
		this.contactnumber = contactnumber;
		this.mailid = mailid;
		this.totalscreens = totalscreens;
		this.status = status;
		this.allplayingshows = allplayingshows;
	 }
	 public Theater() {
		super();
	 }
	 
	 
	

	 
	

}
