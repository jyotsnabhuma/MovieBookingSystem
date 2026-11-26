package com.project.customerservice.dto;

import java.util.List;

public class BookSeatCDTO {
	private long customerid;
	private long showid;
	private List<String> seats;
	public long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}
	public long getShowid() {
		return showid;
	}
	public void setShowid(long showid) {
		this.showid = showid;
	}
	public List<String> getSeats() {
		return seats;
	}
	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

}
