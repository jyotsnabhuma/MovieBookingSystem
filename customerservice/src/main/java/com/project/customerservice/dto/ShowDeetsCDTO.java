package com.project.customerservice.dto;

public class ShowDeetsCDTO {
	
	 private long showid;
	    private String showTime;
	    private String movieName;
	    private int screenno;
	    private String date;
	    private String language;
	    private String theatreName;
	    private int thearterid;
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
		public ShowDeetsCDTO(long showid, String showTime, String movieName, int screenno, String date,
				String language, String theatreName, int thearterid) {
			super();
			this.showid = showid;
			this.showTime = showTime;
			this.movieName = movieName;
			this.screenno = screenno;
			this.date = date;
			this.language = language;
			this.theatreName = theatreName;
			this.thearterid = thearterid;
		}
	    

}
