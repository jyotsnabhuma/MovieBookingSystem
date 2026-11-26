package com.project.customerserviceController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.customerservice.dto.BookSeatCDTO;
import com.project.customerservice.dto.CustomerDTO;
import com.project.customerservice.dto.MovieDTO;
import com.project.customerservice.dto.ResponseStructure;
import com.project.customerservice.dto.SeatCDTO;
import com.project.customerservice.dto.SelectSeatDTO;
import com.project.customerservice.dto.TheaterCDTO;
import com.project.customerservice.dto.TicketResponseDTO;
import com.project.customerserviceEntity.Customer;
import com.project.customerserviceEntity.Ticket;
import com.project.customerserviceService.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerAppControll {

	@Autowired
	private CustomerService customerservice;

	@PostMapping("/cretaeCustomer")
	public ResponseEntity<ResponseStructure<Customer>> createCustomer(@RequestBody CustomerDTO cdto) {
		return customerservice.CreateCustomeracct(cdto);
	}

	@GetMapping("/findCustomer")
	public ResponseEntity<ResponseStructure<Customer>> FindCustomer(@RequestParam long id) {
		return customerservice.FindCustomer(id);
	}

	@DeleteMapping("/deleteCustomer")

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@RequestParam long id) {
		return customerservice.deleteCustomer(id);
	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<Customer>>> getAll() {
		return customerservice.getAllCustomerDeets();
	}

//	 @GetMapping("/findMovie")
//		public Object findMovie(@RequestParam String title) {
//			return customerservice.findMovie(title);
//	}

	@GetMapping("/findMovie")
	public MovieDTO findMovie(@RequestParam String title) {
		return customerservice.findMovie(title);
	}

//		@GetMapping("/buyTicets")
//		public Object buyTickets(@RequestParam String title,@RequestParam String language,@RequestParam String date) {
//			return customerservice.findMoviesInTheater(title,language,date);
//}
//		

	@GetMapping("/buyTicets")
	public List<TheaterCDTO> buyTickets(@RequestParam String title, @RequestParam String language,
			@RequestParam String date) {
		return customerservice.findMoviesInTheater(title, language, date);

	}

//		@PostMapping("/ConfirmedSeat")
//		public void selectSeat(@RequestBody SelectSeatDTO ssdto) {
//	          return customerservice.selectSeats(ssdto);
//		}

//		@GetMapping("/selectSeat")
//		public Object selectSeat(@RequestParam int showid) {
//		return customerservice.selectSeatbyshowid(showid);
//		}

	@GetMapping("/selectSeat")
	public List<SeatCDTO> selectSeat(@RequestParam int showid) {
		return customerservice.selectSeatbyshowid(showid);
	}

	@PostMapping("/bookSeats")
	public Object bookSeats(@RequestBody BookSeatCDTO bscdto) {

		return customerservice.bookSeats(bscdto);
	}

	@GetMapping("/findTicket")
	public ResponseEntity<ResponseStructure<TicketResponseDTO>> findTicketById(@RequestParam long ticketid) {

		return customerservice.findTicketById(ticketid);
	}

	@PutMapping("/confirmTicket/tiketid/{ticketid}")
	public String confirmTicket(@PathVariable long ticketid) {

		return customerservice.confirmTicket(ticketid);
	}

}
