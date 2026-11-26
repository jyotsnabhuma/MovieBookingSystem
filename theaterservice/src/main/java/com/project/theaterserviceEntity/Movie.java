package com.project.theaterserviceEntity;



import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {
	 
	 @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	private String discription;
    @Column(name = "movie_cast")
	private  String cast;
	private  double duration;
	private  String languages;
	  @OneToMany
	  @JoinColumn(name = "movie_id")
	  @JsonIgnore
	  private List<Show> shows = new ArrayList<>();
	
	
	public Movie( String title, String discription, String cast, double duration, String languages,
			List<Show> shows) {
		super();
		this.title = title;
		this.discription = discription;
		this.cast = cast;
		this.duration = duration;
		this.languages = languages;
		this.shows = shows;
	}
	public List<Show> getShows() {
		return shows;
	}
	  public void setShows(List<Show> shows) {
		  this.shows = shows;
	  }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public Movie() {
		super();
	}
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", discription=" + discription + ", cast=" + cast
				+ ", duration=" + duration + ", languages=" + languages + ", shows=" + shows + "]"+"\n";
	}
	
	 
	

}
