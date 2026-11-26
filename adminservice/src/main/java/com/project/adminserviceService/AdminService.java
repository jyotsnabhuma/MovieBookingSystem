package com.project.adminserviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.adminservice.dto.MovieDTO;
import com.project.adminservice.dto.ResponseStructure;


@Service
public class AdminService {
	
	@Autowired
	private  RestTemplate restemplete;

	public Object findShowsInTheater(long theaterid) {
		
		String url="http://localhost:8080/theater/findallplayingshows/"+theaterid;
		Object obj= restemplete.getForObject(url,Object.class);
	    return obj;
		
	}

	public MovieDTO updateMovie(long movieid,MovieDTO mdto) 
	{
		String url="http://localhost:8080/theater/updateMovie?movieid="+movieid;
		
ResponseEntity<ResponseStructure<MovieDTO>> response = restemplete.exchange(url, HttpMethod.PUT,new HttpEntity<>(mdto), new ParameterizedTypeReference<ResponseStructure<MovieDTO>>() {} );

		    return response.getBody().getData();
	}

	public ResponseEntity<ResponseStructure<MovieDTO>> findMovie(String title) {
		String url="http://localhost:8080/theater/findMovie?title="+title;
		//MovieDTO movie=restemplete.getForObject(url,MovieDTO.class);
	//Object movie=restemplete.getForObject(url,Object.class);
		
		ResponseEntity<ResponseStructure<MovieDTO>> movie = restemplete.exchange( url,HttpMethod.GET, null,new ParameterizedTypeReference<ResponseStructure<MovieDTO>>() {});

		
		
		return movie;
	}
	
	
	

	
	
	
}
