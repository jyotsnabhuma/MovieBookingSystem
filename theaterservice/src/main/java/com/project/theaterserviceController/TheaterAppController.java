package com.project.theaterserviceController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.theaterservice.dto.BookSeatDTO;
import com.project.theaterservice.dto.CreateMovieDTO;
import com.project.theaterservice.dto.CreateShowDTO;
import com.project.theaterservice.dto.ResponseStructure;
import com.project.theaterservice.dto.ShowDeetsDTO;
import com.project.theaterservice.dto.TheaterDTO;
import com.project.theaterservice.dto.UpdateTheaterDTO;
import com.project.theaterserviceEntity.Movie;
import com.project.theaterserviceEntity.Seat;
import com.project.theaterserviceEntity.Show;
import com.project.theaterserviceEntity.Theater;
import com.project.theaterserviceService.AdminService;
import com.project.theaterserviceService.TheaterService;

@RestController
@RequestMapping("/theater")
public class TheaterAppController {

	@Autowired
	private TheaterService theaterservice;
	@Autowired
	private AdminService adminservice;

	@PostMapping("/createAccount")
	public ResponseEntity<ResponseStructure<Theater>> createAccount(@RequestBody TheaterDTO t) {
		System.out.println("create theater ");
		return theaterservice.createTheater(t);
	}

	@GetMapping("/findtheaterbyid")
	public ResponseEntity<ResponseStructure<Theater>> findTheater(@RequestParam Long id) {
		return theaterservice.findTheater(id);
	}

	@DeleteMapping("/deleteTheater")
	public ResponseEntity<ResponseStructure<Theater>> deleteTheater(@RequestParam long id) {
		return theaterservice.deleteTheater(id);

	}

	
	@GetMapping("/findAllAvailableMovies")
	public ResponseEntity<ResponseStructure<List<Movie>>> getAll() {
		return adminservice.getAll();
	}

	@PostMapping("/createNewShow")
	public ResponseEntity<ResponseStructure<Show>> createNewShow(@RequestBody CreateShowDTO sdto) {
		return theaterservice.createNewShow(sdto);
	}

	@PostMapping("createNewShow/{theaterid}")
	public ResponseEntity<ResponseStructure<Show>> createNewShow(@PathVariable long theaterid,
			@RequestBody CreateShowDTO cdto) {

		return theaterservice.createNewShow(theaterid, cdto);
	}

	@GetMapping("/findallplayingshows")
	public ResponseEntity<ResponseStructure<List<Show>>> getAllShows() {
		return theaterservice.getAllShows();
	}

	@GetMapping("/findallplayingshows/{theaterid}")
	public ResponseEntity<ResponseStructure<List<Show>>> findShowsInTheater(@PathVariable long theaterid) {
		return theaterservice.findShowsInTheater(theaterid);

	}

	@GetMapping("/findShow")
	public ResponseEntity<ResponseStructure<Show>> findShow(@RequestParam long id) {
		return theaterservice.findShow(id);
	}

	@PatchMapping("/updateShow")
	public ResponseEntity<ResponseStructure<Show>> updateShow(@RequestParam long id, @RequestBody CreateShowDTO cdto) {

		return theaterservice.updateShow(id, cdto);
	}

	@GetMapping("/findMovie")
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam String title) {
		return theaterservice.findMovie(title);
	}

	@GetMapping("/buyTicets")
	public ResponseEntity<ResponseStructure<List<Theater>>> findMovie(@RequestParam String title,
			@RequestParam String language, @RequestParam String date) {
		return theaterservice.findMoviesInTheater(title, language, date);
	}

	@GetMapping("/selectSeat")
	public ResponseEntity<ResponseStructure<List<Seat>>> selectSeat(@RequestParam int showid) {
		return theaterservice.selectSeatbyshowid(showid);
	}

	@GetMapping("/checkSeat/showid/{showid}/Title/{title}/seatno/{seatno}")
	public ResponseEntity<ResponseStructure<Seat>> checkSeat(@PathVariable long showid, @PathVariable String title,
			@PathVariable String seatno) {
		return theaterservice.bookSeatbyselectingit(showid, title, seatno);
	}

	@PostMapping("/bookSeats")
	public BookSeatDTO bookSeats(@RequestBody BookSeatDTO bsdto) {

		return theaterservice.bookSeats(bsdto);
	}

	@GetMapping("/showDetails/{showid}")
	public ShowDeetsDTO findShowDetails(@PathVariable long showid) {

		return theaterservice.findShowDetails(showid);
	}

	@PutMapping("/updateMovie")
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestParam Long movieid,
			@RequestBody CreateMovieDTO upmdto) {

		return adminservice.UpdateMoviebyid(movieid, upmdto);
	}

	@PatchMapping("/Updatetheater/theaterid/{theaterid}")
	public Theater UpdateThater(@PathVariable Long theaterid, @RequestBody UpdateTheaterDTO uptdto) {
		return theaterservice.UpdateTheaterbyid(theaterid, uptdto);

	}

	@DeleteMapping("/DeleteShow")
	public ResponseEntity<ResponseStructure<Show>> DeleteShow(@RequestParam long theaterid, @RequestParam long showid) {
		return theaterservice.DeleteShowinTheater(theaterid, showid);

	}

//	
//	@GetMapping("/show/FindbyMovie")
//	public List<Show> getShowofMovie(@RequestParam long theaterid){
//		return theaterservice.ShowofMovieinTheater(theaterid);
//	}
//	

	@GetMapping("/show/FindbyMovie")
	public List<Show> getShowofMovie(@RequestParam long theaterid, @RequestParam String movieTitle) {

		return theaterservice.ShowofMovieinTheater(theaterid, movieTitle);
	}

	@GetMapping("/show/FindbyDate")
	public ResponseEntity<ResponseStructure<List<Show>>> getShowofDate(@RequestParam long theaterid,
			@RequestParam String date) {
		return theaterservice.ShowofDateinTheater(theaterid, date);
	}

	@GetMapping("/show/FindbyScreen")
	public ResponseEntity<ResponseStructure<List<Show>>> getShowScreenno(@RequestParam long theaterid,
			@RequestParam int screenno) {
		return theaterservice.ShowofScreennoinTheater(theaterid, screenno);
	}

//	@GetMapping("Showdetails")
//	public Show getShowScreenno(@RequestParam long theaterid,@RequestParam long showid) {
//		return theaterservice.ShowDetailsinTheater(theaterid,showid);
//	}

}
