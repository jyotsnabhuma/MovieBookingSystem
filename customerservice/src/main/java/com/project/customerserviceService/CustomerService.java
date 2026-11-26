package com.project.customerserviceService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.customerservice.dto.BookSeatCDTO;
import com.project.customerservice.dto.BookSeatDTO;
import com.project.customerservice.dto.CustomerDTO;
import com.project.customerservice.dto.MovieDTO;
import com.project.customerservice.dto.MovieResponseDTO;
import com.project.customerservice.dto.ResponseStructure;
import com.project.customerservice.dto.SeatCDTO;
import com.project.customerservice.dto.SeatResponseDTO;
import com.project.customerservice.dto.SelectSeatDTO;
import com.project.customerservice.dto.ShowDeetsCDTO;
import com.project.customerservice.dto.TheaterCDTO;
import com.project.customerservice.dto.TheaterreponseDTO;
import com.project.customerservice.dto.TicketResponseDTO;
import com.project.customerserviceEntity.Customer;
import com.project.customerserviceEntity.Ticket;
import com.project.customerserviceException.CustomerAlreadyExistContactException;
import com.project.customerserviceException.CustomerNotFoundException;
import com.project.customerserviceException.SeatNotFoundException;
import com.project.customerserviceException.TickitNotFoundException;
import com.project.customerserviceRepository.CustomerRepository;
import com.project.customerserviceRepository.TicketRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository custrepo;

	public ResponseEntity<ResponseStructure<Customer>> CreateCustomeracct(CustomerDTO cdto) {
		Optional<Customer> cusoptn = custrepo.findByPhone(cdto.getPhone());
		ResponseStructure<Customer> crs = new ResponseStructure<Customer>();
		if (cusoptn.isPresent()) {
			throw new CustomerAlreadyExistContactException();
		}
		Customer cust = new Customer();
		cust.setName(cdto.getName());
		cust.setPhone(cdto.getPhone());
		cust.setAge(cdto.getAge());
		cust.setEmail(cdto.getEmail());
		cust.setGender(cdto.getGender());

		Customer savecust = custrepo.save(cust);

		crs.setStatuscode(HttpStatus.CREATED.value());
		crs.setMesaage("account created succesfully");
		crs.setData(savecust);
		return new ResponseEntity<ResponseStructure<Customer>>(crs, HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<Customer>> FindCustomer(Long id) {

		Optional<Customer> cusoptn = custrepo.findById(id);
		ResponseStructure<Customer> crs = new ResponseStructure<Customer>();
		if (cusoptn.isPresent()) {
			Customer cust = cusoptn.get();
			System.out.println("customer deets are" + cust);
			crs.setStatuscode(HttpStatus.FOUND.value());
			crs.setMesaage("customer is found");
			crs.setData(cust);
			return new ResponseEntity<ResponseStructure<Customer>>(crs, HttpStatus.FOUND);

		}
		throw new CustomerNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(long id) {
		Customer custmer = custrepo.findById(id).orElseThrow(() -> new CustomerNotFoundException());
		ResponseStructure<Customer> crs = new ResponseStructure<Customer>();
		custrepo.delete(custmer);
		crs.setStatuscode(HttpStatus.CREATED.value());
		crs.setMesaage("customer account " + id + " deleted successfulluy");
		crs.setData(null);
		return new ResponseEntity<ResponseStructure<Customer>>(crs, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomerDeets() {
		List<Customer> customers = custrepo.findAll();
		ResponseStructure<List<Customer>> crs = new ResponseStructure<List<Customer>>();
		System.out.println("movie:" + customers);
		crs.setStatuscode(HttpStatus.CREATED.value());
		crs.setMesaage("customers details are: ");
		crs.setData(customers);
		return new ResponseEntity<ResponseStructure<List<Customer>>>(crs, HttpStatus.FOUND);

	}

	public void selectSeats(SelectSeatDTO ssdto) {
		Customer cust = custrepo.findById(ssdto.getCustomerid()).orElseThrow(() -> new CustomerNotFoundException());

		// return ResponseEntity<ResponseStructure<Customer>>();

	}

	@Autowired
	private RestTemplate restemplete;

//	public Object findMovie(String title) {
//		String url="http://localhost:8080/theater/findMovie?title="+title;
//		System.out.println(url);
//		Object obj= restemplete.getForObject(url,Object.class);
//		return obj;
//		
//	
//	} 

//	public MovieDTO findMovie(String title) {
//		String url="http://localhost:8080/theater/findMovie?title="+title;
//		System.out.println(url);
//		MovieDTO obj= restemplete.getForObject(url,MovieDTO.class);
//		return obj;
//		
//		
//	}
	public MovieDTO findMovie(String title) {

		String url = "http://localhost:8080/theater/findMovie?title=" + title;

		MovieResponseDTO response = restemplete.getForObject(url, MovieResponseDTO.class);

		if (response.getData() == null) {
			throw new RuntimeException("Movie not found");
		}

		return response.getData();
	}

//	public Object  findMoviesInTheater(String title, String language,String date) {
//		
//		String url="http://localhost:8080/theater/buyTicets?title="+title+"&language="+language+"&date="+date;
//		System.out.println(url);
//		Object obj= restemplete.getForObject(url,Object.class);
//		return obj;
//	
//	}

	public List<TheaterCDTO> findMoviesInTheater(String title, String language, String date) {

		String url = "http://localhost:8080/theater/buyTicets?title=" + title + "&language=" + language + "&date="
				+ date;

		TheaterreponseDTO resp = restemplete.getForObject(url, TheaterreponseDTO.class);

		if (resp == null || resp.getData() == null || resp.getData().isEmpty()) {
			throw new RuntimeException("theater not found");
		}

		return resp.getData();
	}

//	public Object  selectSeatbyshowid(int showid) {
//		
//		String url="http://localhost:8080/theater/selectSeat?showid="+showid;
//		System.out.println(url);
//		Object obj= restemplete.getForObject(url,Object.class);
//		return obj;
//	
//	}

//	

	public List<SeatCDTO> selectSeatbyshowid(int showid) {

		String url = "http://localhost:8080/theater/selectSeat?showid=" + showid;

		SeatResponseDTO resp = restemplete.getForObject(url, SeatResponseDTO.class);

		if (resp == null || resp.getData() == null) {
			throw new SeatNotFoundException();
		}

		List<SeatCDTO> seats = resp.getData();

		if (seats.isEmpty()) {
			throw new SeatNotFoundException();
		}

		return seats;
	}

//	public Object bookSeats(BookSeatCDTO bscdto) {
//		
//		
//		    String url ="http://localhost:8080/theater/bookSeats";
//		    BookSeatCDTO cdto = new BookSeatCDTO();
//		    cdto.setCustomerid(bscdto.getCustomerid());
//		    cdto.setShowid(bscdto.getShowid());
//		    cdto.setSeats(bscdto.getSeats());
//		    Object obj = restemplete.postForObject( url,cdto,Object.class );
//		    return obj;
//		}
//	

//	public String bookSeats(BookSeatCDTO bscdto) {
//		System.out.println("Customer ID received: " + bscdto.getCustomerid());
//
//    Customer customer = custrepo.findById(bscdto.getCustomerid()).orElseThrow(() -> new CustomerNotFoundException());
//
//	    String url = "http://localhost:8080/theater/bookSeats";
//
//	    BookSeatDTO cdto = new BookSeatDTO();
//	    cdto.setShowid(bscdto.getShowid());
//	    cdto.setSeats(bscdto.getSeats());
//
//	    String response = restemplete.postForObject( url, cdto,String.class);
//	    return response;
//	}
//		    
	@Autowired
	private TicketRepository ticketrepo;

	public String bookSeats(BookSeatCDTO bscdto) {

		Customer customer = custrepo.findById(bscdto.getCustomerid())
				.orElseThrow(() -> new CustomerNotFoundException());
		String bookingUrl = "http://localhost:8080/theater/bookSeats";

		BookSeatDTO cdto = new BookSeatDTO();
		cdto.setShowid(bscdto.getShowid());
		cdto.setSeats(bscdto.getSeats());

		BookSeatDTO response = restemplete.postForObject(bookingUrl, cdto, BookSeatDTO.class);

		String showUrl = "http://localhost:8080/theater/showDetails/" + bscdto.getShowid();

		ShowDeetsCDTO showdetails = restemplete.getForObject(showUrl, ShowDeetsCDTO.class);

		Ticket ticket = new Ticket();

		ticket.setCustomer(customer);
		ticket.setUsername(customer.getName());
		cdto.setCustomerid(bscdto.getCustomerid());
		ticket.setShowid(bscdto.getShowid());
		ticket.setSeats(bscdto.getSeats());
		ticket.setTotalAmount((double) response.getTotalprice());

		ticket.setShowTime(showdetails.getShowTime());
		ticket.setMovieName(showdetails.getMovieName());
		ticket.setScreenno(showdetails.getScreenno());
		ticket.setDate(showdetails.getDate());
		ticket.setLanguage(showdetails.getLanguage());
		ticket.setTheatreName(showdetails.getTheatreName());
		ticket.setThearterid(showdetails.getThearterid());
		ticket.setBookingDate(LocalDateTime.now().toString());
		ticket.setStatus("PENDING_PAYMENT");

		ticketrepo.save(ticket);

		return "Ticket created successfully. Ticket ID: " + ticket.getId() + " | Movie: " + ticket.getMovieName()
				+ " | Seats: " + bscdto.getSeats() + " | Total price: " + ticket.getTotalAmount();
	}

	public ResponseEntity<ResponseStructure<TicketResponseDTO>> findTicketById(long ticketid) {

		Ticket ticket = ticketrepo.findById(ticketid).orElseThrow(() -> new TickitNotFoundException());

		TicketResponseDTO tdto = new TicketResponseDTO();

		tdto.setId(ticket.getId());
		tdto.setCustomerId(ticket.getCustomer().getId());
		tdto.setTotalAmount(ticket.getTotalAmount());
		tdto.setStatus(ticket.getStatus());

		ResponseStructure<TicketResponseDTO> crs = new ResponseStructure<>();

		crs.setStatuscode(HttpStatus.OK.value());
		crs.setMesaage("Ticket details fetched successfully");
		crs.setData(tdto);

		return new ResponseEntity<ResponseStructure<TicketResponseDTO>>(crs, HttpStatus.OK);

	}

	public String confirmTicket(long ticketid) {

		Ticket ticket = ticketrepo.findById(ticketid).orElseThrow(() -> new TickitNotFoundException());

		if (!"PENDING_PAYMENT".equals(ticket.getStatus())) {
			throw new RuntimeException("Ticket is not pending payment");
		}

		ticket.setStatus("CONFIRMED");

		ticketrepo.save(ticket);

		return "Ticket confirmed successfully";
	}
}
