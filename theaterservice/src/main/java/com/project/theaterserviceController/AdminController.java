package com.project.theaterserviceController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.theaterservice.dto.CreateMovieDTO;
import com.project.theaterservice.dto.ResponseStructure;
import com.project.theaterserviceEntity.Movie;
import com.project.theaterserviceService.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private  AdminService adminservice;
	
	
	
	@PostMapping("/createMovie")
	public ResponseEntity<ResponseStructure<Movie>> cretaeMovie(@RequestBody CreateMovieDTO mdto) {	
		return adminservice.createMovie(mdto);	
		
	}
	
	@GetMapping("/movie/find")
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam long id) {
		return adminservice.findMovie(id);
	}
	
	@GetMapping("/movie/All")
	public ResponseEntity<ResponseStructure<List<Movie>>> getAll() {
		return adminservice.getAll();
	}
	
	
	
	@DeleteMapping("/movie/delete")
    public ResponseEntity<ResponseStructure<Movie>> deletMovie(long id) {
		return adminservice.deleteMovie(id);
	}
	
	
	
}
