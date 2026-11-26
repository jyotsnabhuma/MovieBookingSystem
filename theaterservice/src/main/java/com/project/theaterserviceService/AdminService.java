package com.project.theaterserviceService;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.theaterservice.dto.CreateMovieDTO;
import com.project.theaterservice.dto.ResponseStructure;
import com.project.theaterservice.dto.UpdateTheaterDTO;
import com.project.theaterserviceEntity.Movie;
import com.project.theaterserviceEntity.Theater;
import com.project.theaterserviceException.MovieAlreadyCreatedException;
import com.project.theaterserviceException.MovieNotFoundException;
import com.project.theaterserviceException.TheaterNotFoundException;
import com.project.theaterserviceRepository.MovieRepository;

@Service
public class AdminService {
	@Autowired
	private MovieRepository mr;
	public ResponseEntity<ResponseStructure<Movie>> createMovie(CreateMovieDTO mdto) {
		
			Optional<Movie> movieoptn=mr.findByTitle(mdto.getTitle());
			ResponseStructure<Movie> mrs=new ResponseStructure<Movie>();
			   if(movieoptn.isPresent()) {
				   throw new MovieAlreadyCreatedException();
			   }
			   
			     Movie newmovie=new Movie();
			     newmovie.setTitle(mdto.getTitle());
			     newmovie.setDiscription(mdto.getDiscription());
			     newmovie.setDuration(mdto.getDuration());
			     newmovie.setCast(mdto.getCast());
			     newmovie.setLanguages(mdto.getLanguages());
			     Movie savemovie=mr.save(newmovie);
			     
			        	mrs.setStatuscode(HttpStatus.CREATED.value());
						mrs.setMesaage("movie deets created");
						mrs.setData(savemovie);
						return new ResponseEntity<ResponseStructure<Movie>>(mrs,HttpStatus.CREATED);
			        	 		
					

		
	}
	public ResponseEntity<ResponseStructure<Movie>> findMovie(long id) {
		//Optional<Movie> movieoptn=tr.findById(id);
		Movie mov=mr.findById(id).orElseThrow(()->new MovieNotFoundException());

		ResponseStructure<Movie> mrs=new ResponseStructure<Movie>();
//       if(movieoptn.isEmpty()) {
//    	   throw new MovieNotFoundException();
//       }
              // Movie  mov=movieoptn.get();
          
          mrs.setStatuscode(HttpStatus.CREATED.value());
			mrs.setMesaage("movie with id"+mov.getId()+ "is present");
			mrs.setData(mov);
			return new ResponseEntity<ResponseStructure<Movie>>(mrs,HttpStatus.FOUND);
		

	}
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(Long id) {
	Optional<Movie> movieoptn=mr.findById(id);
		//Movie mov=tr.findById(id).orElseThrow(()->new MovieNotFoundException());
		ResponseStructure<Movie> mrs=new ResponseStructure<Movie>();
     if(movieoptn.isPresent()) {
    	  mr.delete(movieoptn.get());
    	 mrs.setStatuscode(HttpStatus.CREATED.value());
    		mrs.setMesaage("movie with id "+id+" is deleted");
    		mrs.setData(null);
    		return new ResponseEntity<ResponseStructure<Movie>>(mrs,HttpStatus.FOUND); 
    	  
       }	

	  throw new MovieNotFoundException();	

	}

	public ResponseEntity<ResponseStructure<List<Movie>>> getAll() {
		
		List<Movie> movies=mr.findAll();
		ResponseStructure<List<Movie>> mrs=new ResponseStructure<List<Movie>>();
		//Movie mov=new Movie();
		System.out.println("movie:"+movies);
			  mrs.setStatuscode(HttpStatus.CREATED.value());
				mrs.setMesaage("movies are all these");
				mrs.setData(movies);			
		return new ResponseEntity<ResponseStructure<List<Movie>>>(mrs,HttpStatus.OK);
		
		
	}
	
	public ResponseEntity<ResponseStructure<Movie>> UpdateMoviebyid(Long movieid, CreateMovieDTO upmdto) {
		Movie movie = mr.findById(movieid) .orElseThrow(() -> new MovieNotFoundException());  


	     movie.setTitle(upmdto.getTitle());
	     movie.setDiscription(upmdto.getDiscription());
	     movie.setDuration(upmdto.getDuration());
	     movie.setCast(upmdto.getCast());
	     movie.setLanguages(upmdto.getLanguages());
	     Movie savemovie=mr.save(movie);
	     ResponseStructure<Movie> mrs=new ResponseStructure<Movie>();
	        	mrs.setStatuscode(HttpStatus.CREATED.value());
				mrs.setMesaage("movie deets updated succesfully");
				mrs.setData(savemovie);
				System.out.println(upmdto.getDiscription());
				System.out.println(movie.getDiscription());
				return new ResponseEntity<ResponseStructure<Movie>>(mrs,HttpStatus.CREATED);
		
	}
	
	
	
	

	
	

}
