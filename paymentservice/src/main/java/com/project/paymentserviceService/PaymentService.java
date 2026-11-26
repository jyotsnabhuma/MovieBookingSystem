package com.project.paymentserviceService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.paymentservice.dto.PaymentDTO;
import com.project.paymentservice.dto.ResponseStructure;
import com.project.paymentservice.dto.TicketResponseDTO;
import com.project.paymentserviceEntity.Payment;
import com.project.paymentserviceRepository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentrepo;
	@Autowired
	private RestTemplate restemplete;
	
	public TicketResponseDTO getTicketDetails(long ticketid) {

	    String url = "http://localhost:8081/customer/findTicket?ticketid=" + ticketid;

	    ResponseEntity<ResponseStructure<TicketResponseDTO>> response = restemplete.exchange( url, HttpMethod.GET, null, new ParameterizedTypeReference<ResponseStructure<TicketResponseDTO>>() {} );

	    return response.getBody().getData();
	}
	
	public boolean simulateUpiPayment(double amount) {

	    System.out.println("Processing UPI payment...");
	    System.out.println("Payment amount: " + amount);

	    // Simulating successful UPI payment
	    return true;
	}
	
	public String makePayment(PaymentDTO pdto) {

	    // 1. Fetch ticket details from Customer Service
	    TicketResponseDTO ticket =
	            getTicketDetails(pdto.getTicketId());

	    // 2. Validate ticket status
	    if (!"PENDING_PAYMENT".equals(ticket.getStatus())) {
	        throw new RuntimeException("Ticket is not eligible for payment");
	    }

	    // 3. Get the actual amount from the ticket
	    double amount = ticket.getTotalAmount();

	    // 4. Simulate UPI payment
	    boolean isPaymentSuccess = simulateUpiPayment(amount);

	    // 5. Create Payment object
	    Payment payment = new Payment();

	    payment.setTicketId(ticket.getId());
	    payment.setCustomerId(ticket.getCustomerId());
	    payment.setAmount(amount);
	    payment.setPaymentMethod(pdto.getPaymentMethod());
	    payment.setPaymentDate(LocalDateTime.now().toString());

	    // 6. Set payment status based on provider response
	    if (isPaymentSuccess) {

	        payment.setStatus("SUCCESS");
	        paymentrepo.save(payment);

	        return "Payment successful. Payment ID: "
	                + payment.getId();
	    }
	    else {

	        payment.setStatus("FAILED");
	        paymentrepo.save(payment);

	        return "Payment failed";
	    }
	}

}
