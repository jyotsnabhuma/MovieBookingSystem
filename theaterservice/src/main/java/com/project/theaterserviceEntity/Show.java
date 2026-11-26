package com.project.theaterserviceEntity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Show {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	 @ManyToOne
	    @JoinColumn(name = "movie_id")
	private Movie movie;
	private String title;
	private String description;
	private double duration;
	private String language;
	private String time;
	private String date;
	private int screenno;
	private int price;
	@OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Seat> seats = new ArrayList<>();
    @ManyToOne
    private Theater theater;
    
    private int seatcapacity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getScreenno() {
		return screenno;
	}

	public void setScreenno(int screenno) {
		this.screenno = screenno;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public int getSeatcapacity() {
		return seatcapacity;
	}

	public void setSeatcapacity(int seatcapacity) {
		this.seatcapacity = seatcapacity;
	}

	public Show(long id, Movie movie, String title, String description, double duration, String language, String time,
			String date, int screenno, int price, List<Seat> seats, Theater theater, int seatcapacity) {
		super();
		this.id = id;
		this.movie = movie;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.time = time;
		this.date = date;
		this.screenno = screenno;
		this.price = price;
		this.seats = seats;
		this.theater = theater;
		this.seatcapacity = seatcapacity;
	}

	public Show() {
		super();
	}
    
    

}