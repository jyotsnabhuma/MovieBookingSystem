package com.project.adminserviceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.adminservice.dto.MovieDTO;
import com.project.adminservice.dto.ResponseStructure;
import com.project.adminserviceService.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminservice;
	
	
	@GetMapping("/findallplayingshows/theaterid/{theaterid}")
	public Object findShowsInTheater(@PathVariable long theaterid) {
		return adminservice.findShowsInTheater(theaterid);
		
	}
	
	
	@PutMapping("/updateMovie")
	public MovieDTO updateMovie(@RequestParam long movieid,@RequestBody MovieDTO mdto) {

	    return adminservice.updateMovie(movieid,mdto);
	}
	
	
	@GetMapping("/findMovie")
	public ResponseEntity<ResponseStructure<MovieDTO>> findMovie(@RequestParam String title) {
		return adminservice.findMovie(title);
	}
	

}
