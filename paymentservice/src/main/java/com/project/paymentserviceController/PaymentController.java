package com.project.paymentserviceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.paymentservice.dto.PaymentDTO;
import com.project.paymentservice.dto.TicketResponseDTO;
import com.project.paymentserviceService.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	 @Autowired
	    private PaymentService paymentservice;

	    @GetMapping("/ticketDetails")
	    public TicketResponseDTO getTicketDetails( @RequestParam long ticketid) {

	        return paymentservice.getTicketDetails(ticketid);
	    }
	
	    @PostMapping("/bookTicket")
	    public String bookTicket(@RequestBody PaymentDTO pdto) {

	        return paymentservice.makePayment(pdto);
	    }
	

}
