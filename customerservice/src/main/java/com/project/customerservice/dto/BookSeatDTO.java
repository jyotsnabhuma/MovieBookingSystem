package com.project.customerservice.dto;

import java.util.List;

public class BookSeatDTO {
	private long customerid;
	    public long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}
		private long showid;
	    private List<String> seats;
	    
	    private int totalprice;
	    
	    
		public int getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(int totalprice) {
			this.totalprice = totalprice;
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

