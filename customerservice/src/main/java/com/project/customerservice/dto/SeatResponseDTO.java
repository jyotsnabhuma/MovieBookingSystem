package com.project.customerservice.dto;

import java.util.List;

public class SeatResponseDTO {
	private int statuscode;
    private String mesaage;
    private List<SeatCDTO> data;
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
	public List<SeatCDTO> getData() {
		return data;
	}
	public void setData(List<SeatCDTO> data) {
		this.data = data;
	}
    

}
