package com.project.customerservice.dto;

public class MovieResponseDTO {
	
	private int statuscode;
    private String mesaage;
    private MovieDTO data;

    // Getters and setters
	    public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getMesaage() {
		return mesaage;
	}
	public void setMesaage(String mesaage) {
		this.mesaage = mesaage;
	}
	public MovieDTO getData() {
		return data;
	}
	public void setData(MovieDTO data) {
		this.data = data;
	}
	
	}

