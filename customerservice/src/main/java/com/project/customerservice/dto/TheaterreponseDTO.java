package com.project.customerservice.dto;

import java.util.List;

public class TheaterreponseDTO {
	private int statuscode;
    private String mesaage;
    private List<TheaterCDTO> data;
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
	public List<TheaterCDTO> getData() {
		return data;
	}
	public void setData(List<TheaterCDTO> data) {
		this.data = data;
	}

}
