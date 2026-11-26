package com.project.theaterservice.dto;

import com.project.theaterserviceEntity.Movie;

public class CreateShowDTO {

	    private long movieid;
	  
		private String title;
	    private String description;
	    private double duration;
	    private String language;
	    private String date;
	    private String time;
	    private int screenno;
	    private int seatcapacity;
	    private Movie movie;
	    private int price;
	    
	    
	    
	    public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public CreateShowDTO() {
			super();
		}
		public CreateShowDTO(long movieid, String title, String description, double duration, String language,
				String date, String time, int screenno, int seatcapacity, Movie movie,int price) {
			super();
			this.movieid = movieid;
			this.title = title;
			this.description = description;
			this.duration = duration;
			this.language = language;
			this.date = date;
			this.time = time;
			this.screenno = screenno;
			this.seatcapacity = seatcapacity;
			this.movie = movie;
			this.price=price;
		}
		public int getSeatcapacity() {
			return seatcapacity;
		}
		public void setSeatcapacity(int seatcapacity) {
			this.seatcapacity = seatcapacity;
		}
		public Movie getMovie() {
			return movie;
		}
		public void setMovie(Movie movie) {
			this.movie = movie;
		}
		public long getMovieid() {
			return movieid;
		}
		public void setMovieid(long movieid) {
			this.movieid = movieid;
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
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public int getScreenno() {
			return screenno;
		}
		public void setScreenno(int screenno) {
			this.screenno = screenno;
		}

	    // Generate getters and setters
	    
	    
	}
	
	