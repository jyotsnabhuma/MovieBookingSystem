package com.project.paymentservice.dto;

public class ResponseStructure<T>{
	 private int statuscode;
		private  String mesaage;
		private T data;
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
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public ResponseStructure(int statuscode, String mesaage, T data) {
			super();
			this.statuscode = statuscode;
			this.mesaage = mesaage;
			this.data = data;
		}
		public ResponseStructure() {
			super();
		}
		
		
	

}
