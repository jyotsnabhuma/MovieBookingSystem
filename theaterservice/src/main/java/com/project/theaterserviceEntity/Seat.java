package com.project.theaterserviceEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	private String seatno;
	@ManyToOne
	@JoinColumn(name = "show_id")
	@JsonBackReference
	private Show show;
	private String catogory;
	private int catagoryprice;
	private String status;
	
	
	
	
	
	public int getCatagoryprice() {
		return catagoryprice;
	}
	public void setCatagoryprice(int catagoryprice) {
		this.catagoryprice = catagoryprice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeatno() {
		return seatno;
	}
	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public String getCatogory() {
		return catogory;
	}
	public void setCatogory(String catogory) {
		this.catogory = catogory;
	}
	public Seat(String seatno, Show show, String catogory,int catagoryprice,String status) {
		super();
		this.seatno = seatno;
		this.show = show;
		this.catogory = catogory;
		this.catagoryprice=catagoryprice;
		this.status=status;
	}
	public Seat() {
		super();
	}
	

}
