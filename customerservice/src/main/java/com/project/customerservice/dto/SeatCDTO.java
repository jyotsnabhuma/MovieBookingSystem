package com.project.customerservice.dto;


public class SeatCDTO {
	private String seatno;
	private String catogory;
	private int catagoryprice;
	private String status;
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getSeatno() {
		return seatno;
	}
	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}
	public String getCatogory() {
		return catogory;
	}
	public void setCatogory(String catogory) {
		this.catogory = catogory;
	}
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

}
