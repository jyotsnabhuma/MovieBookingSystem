package com.project.customerserviceEntity;



import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private long showid;

    private String showTime;

    private String movieName;

    private int screenno;

    private Double totalAmount;

    private String date;

    private String language;

    private String bookingDate;

    private String theatreName;

    private int thearterid;

    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection
    @CollectionTable(
        name = "ticket_seats",
        joinColumns = @JoinColumn(name = "ticket_id")
    )
    @Column(name = "seat_no")
    private List<String> seats;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getShowid() {
		return showid;
	}

	public void setShowid(long showid) {
		this.showid = showid;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getScreenno() {
		return screenno;
	}

	public void setScreenno(int screenno) {
		this.screenno = screenno;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getThearterid() {
		return thearterid;
	}

	public void setThearterid(int thearterid) {
		this.thearterid = thearterid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	public Ticket( String username, int showid, String showTime, String movieName, int screenno,
			Double totalAmount, String date, String language, String bookingDate, String theatreName, int thearterid,
			String status, Customer customer, List<String> seats) {
		super();
		this.username = username;
		this.showid = showid;
		this.showTime = showTime;
		this.movieName = movieName;
		this.screenno = screenno;
		this.totalAmount = totalAmount;
		this.date = date;
		this.language = language;
		this.bookingDate = bookingDate;
		this.theatreName = theatreName;
		this.thearterid = thearterid;
		this.status = status;
		this.customer = customer;
		this.seats = seats;
	}

	public Ticket() {
		super();
	}
    
    

 

	   // private Theater theater;

	    

}