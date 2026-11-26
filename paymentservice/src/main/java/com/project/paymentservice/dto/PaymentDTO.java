package com.project.paymentservice.dto;

public class PaymentDTO {
	
	    private Long ticketId;
      
	    private String paymentMethod;
	    //private String status;
		public Long getTicketId() {
			return ticketId;
		}
		public void setTicketId(Long ticketId) {
			this.ticketId = ticketId;
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
	    
	    


}
