package com.project.theaterserviceException;

import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.project.theaterservice.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(TheaterAlreadyExistContactException.class)
	public ResponseEntity<ResponseStructure<String>> handlingTheaterAlreadyExistContactException() 
	{
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("theater acct already present");
		resstr.setData("theater acc already creatwd");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
	}
	
	@ExceptionHandler(MovieAlreadyCreatedException.class)
	public ResponseEntity<ResponseStructure<String>> handlingMovieAlreadyCreatedException() {
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("movie alredy already present");
		resstr.setData("movie is  already creatwd");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
		
	}
	
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlingMovieNotFoundException() {
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("movie with this id not found,create a new movie");
		resstr.setData("movie is not creatwd");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
}
	
	@ExceptionHandler(ShowAlreadyExistbyMovieException.class)
	public ResponseEntity<ResponseStructure<String>> handleShowAlreadyExists() {

	    ResponseStructure<String> resstr = new ResponseStructure<>();

	    resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
	    resstr.setMesaage("Show already exists for this screen,date and time");
	    resstr.setData(null);

	    return new ResponseEntity<ResponseStructure<String>>(resstr, HttpStatus.FOUND);
	}
	
	@ExceptionHandler(ShowNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlingShowNotFoundException() {
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("show with this id not found,create  new show");
		resstr.setData("shows is not creatwd");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
}
	
	@ExceptionHandler(TheaterNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlingTheaterNotFoundException() {
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("theater with given condition not found");
		resstr.setData("theater doest playing with given conditions");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.FOUND);
		
}
	
	@ExceptionHandler(SeatsNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlingSeatNotFoundException() 
	{
		ResponseStructure<String> resstr=new ResponseStructure<String>();
		resstr.setStatuscode(HttpStatus.NOT_FOUND.value());
		resstr.setMesaage("seat not found");
		resstr.setData("this seat alrady booked.");
		return   new ResponseEntity<ResponseStructure<String>>(resstr,HttpStatus.NOT_FOUND);
		
	}
}
