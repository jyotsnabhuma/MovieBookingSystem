package com.project.customerserviceException;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.project.customerservice.dto.ResponseStructure;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlingCustomerNotFoundException() 
	{
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("customer acct not found");
		resstr.setData("customer acc not creatwd,create a new account");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
	}
	@ExceptionHandler(CustomerAlreadyExistContactException.class)
	public ResponseEntity<ResponseStructure<String>> handlingCustomerAlreadyExistContactException() 
	{
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("customer acct already present");
		resstr.setData("customer acc already creatwd");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
	}
	
	@ExceptionHandler(TickitNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlingTickitNotFoundException() 
	{
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("Tickit not found");
		resstr.setData("ticit not purchased,buy one");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
	}
	@ExceptionHandler(SeatNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlingSeatNotFoundException() 
	{
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("seat not found");
		resstr.setData("this seat alrady booked.");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(TicketAlreadyExistbySeatnoException.class)
	public ResponseEntity<ResponseStructure<String>> handlingTickitAlreadybySeatnoExistException() 
	{
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("this seat is not available");
		resstr.setData("this tickit bokked with seatno already");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
	}
	
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<ResponseStructure<String>> handlingmovienotfound() 
//	{
//		ResponseStructure<String> resstr=new ResponseStructure<String>();
//		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
//		resstr.setMesaage("this movie is not available");
//		resstr.setData("no movie");
//		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.NOT_FOUND);
//		
//	}
//	
	

}
