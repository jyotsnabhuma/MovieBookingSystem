package com.project.theaterserviceService;

import java.time.Duration;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.theaterservice.dto.BookSeatDTO;
import com.project.theaterservice.dto.CreateShowDTO;
import com.project.theaterservice.dto.ResponseStructure;
import com.project.theaterservice.dto.ShowDeetsDTO;
import com.project.theaterservice.dto.TheaterDTO;
import com.project.theaterservice.dto.UpdateTheaterDTO;
import com.project.theaterserviceEntity.Movie;
import com.project.theaterserviceEntity.Seat;
import com.project.theaterserviceEntity.Show;
import com.project.theaterserviceEntity.Theater;
import com.project.theaterserviceException.MovieNotFoundException;
import com.project.theaterserviceException.SeatsNotFoundException;
import com.project.theaterserviceException.ShowAlreadyExistbyMovieException;
import com.project.theaterserviceException.ShowNotFoundException;
import com.project.theaterserviceException.TheaterAlreadyExistContactException;
import com.project.theaterserviceException.TheaterNotFoundException;
import com.project.theaterserviceRepository.MovieRepository;
import com.project.theaterserviceRepository.SeatRepository;
import com.project.theaterserviceRepository.ShowRepository;
import com.project.theaterserviceRepository.TheaterRepository;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository tr;

	@Autowired
	private ShowRepository showrepo;

	@Autowired
	private MovieRepository movierepo;

	@Autowired
	private StringRedisTemplate redisTemplate;

	public ResponseEntity<ResponseStructure<Theater>> createTheater(TheaterDTO dto) {
//	t.setName(t.getName());
//	t.setDescription(t.getDescription());
//	t.setContactnumber(t.getContactnumber());
//	t.setMailid(t.getMailid());
//	t.setTotalscreens(t.getTotalscreens());

//		Theater savetheater=tr.save(t);
//		return savetheater;

		Optional<Theater> theateroptn = tr.findByContactnumber(dto.getContactnum());
		ResponseStructure<Theater> rt = new ResponseStructure<Theater>();
		if (theateroptn.isPresent()) {
			throw new TheaterAlreadyExistContactException();
		}

		Theater newtheater = new Theater();
		newtheater.setName(dto.getName());
		newtheater.setDescription(dto.getDescription());
		newtheater.setContactnumber(dto.getContactnum());
		newtheater.setMailid(dto.getMailid());
		newtheater.setCity(dto.getCity());
		newtheater.setTotalscreens(dto.getTotalscreens());
		newtheater.setStatus("open");

//        if(tr.findById(t.getId()).isEmpty())	
//        {
//        	Theater theatersaved=tr.save(t);
//        	rt.setStatuscode(HttpStatus.CREATED.value());
//			rt.setMesaage("theater account created succfully");
//			rt.setData(theatersaved);
//			return new ResponseEntity<ResponseStructure<Theater>>(rt,HttpStatus.CREATED);
//		}
		Theater savetheater = tr.save(newtheater);

//      
		rt.setStatuscode(HttpStatus.CREATED.value());
		rt.setMesaage("theater account already created");
		rt.setData(savetheater);
		return new ResponseEntity<ResponseStructure<Theater>>(rt, HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<Theater>> findTheater(Long id) {
		// public ResponseEntity<ResponseStructure<Theater>> findTheater(Theater dto) {
		// Optional<Theater> theater=tr.findByMail(dto.getMailid());

		Optional<Theater> theater = tr.findById(id);
		ResponseStructure<Theater> rt = new ResponseStructure<Theater>();

		if (theater.isPresent()) {
			Theater thr = theater.get();
			rt.setStatuscode(HttpStatus.FOUND.value());
			rt.setMesaage("Theater accout is found");
			rt.setData(thr);
			return new ResponseEntity<ResponseStructure<Theater>>(rt, HttpStatus.FOUND);
		} else {
			System.out.println("theater account not created");
			rt.setStatuscode(HttpStatus.NOT_FOUND.value());
			rt.setMesaage("theater account not found");
			rt.setData(null);
			return new ResponseEntity<ResponseStructure<Theater>>(rt, HttpStatus.NOT_FOUND);

		}

	}

	public ResponseEntity<ResponseStructure<Theater>> deleteTheater(Long id) {
		Optional<Theater> theateroptn = tr.findById(id);
		ResponseStructure<Theater> trs = new ResponseStructure<Theater>();
		if (theateroptn.isPresent()) {
			tr.delete(theateroptn.get());
			trs.setStatuscode(HttpStatus.CREATED.value());
			trs.setMesaage("movie with id" + id + "is deleted");
			trs.setData(null);
			return new ResponseEntity<ResponseStructure<Theater>>(trs, HttpStatus.FOUND);

		}

		throw new MovieNotFoundException();

	}

	public ResponseEntity<ResponseStructure<List<Movie>>> getAll() {

		List<Movie> movies = movierepo.findAll();
		ResponseStructure<List<Movie>> mrs = new ResponseStructure<List<Movie>>();
		// Movie mov=new Movie();
		System.out.println("movie:" + movies);
		mrs.setStatuscode(HttpStatus.CREATED.value());
		mrs.setMesaage("movies are all these");
		mrs.setData(movies);
		return new ResponseEntity<ResponseStructure<List<Movie>>>(mrs, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Show>> createNewShow(CreateShowDTO sdto) {

		Optional<Show> showoptn = showrepo.findByMovie(sdto.getMovie());
		ResponseStructure<Show> rt = new ResponseStructure<Show>();
		if (showoptn.isPresent()) {
			throw new ShowAlreadyExistbyMovieException();
		}

		Show newshow = new Show();
		newshow.setTitle(sdto.getTitle());
		newshow.setDescription(sdto.getDescription());
		newshow.setMovie(sdto.getMovie());
		newshow.setDuration(sdto.getDuration());
		newshow.setDate(sdto.getDate());
		newshow.setTime(sdto.getTime());

		Show saveshow = showrepo.save(newshow);

		rt.setStatuscode(HttpStatus.CREATED.value());
		rt.setMesaage("show account  created");
		rt.setData(saveshow);
		return new ResponseEntity<ResponseStructure<Show>>(rt, HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<Show>> createNewShow(long theaterid, CreateShowDTO cdto) {

		Theater th = tr.findById(theaterid).orElseThrow(() -> new TheaterNotFoundException());

		Movie movie = movierepo.findById(cdto.getMovieid()).orElseThrow(() -> new MovieNotFoundException());

		Optional<Show> showopt = showrepo.findByDateAndTimeAndScreennoAndTheater(cdto.getDate(), cdto.getTime(),
				cdto.getScreenno(), th);

		if (showopt.isPresent()) {

			throw new ShowAlreadyExistbyMovieException();
		}

		Show show = new Show();

		show.setTitle(cdto.getTitle());
		show.setDescription(cdto.getDescription());
		show.setDate(cdto.getDate());
		show.setTime(cdto.getTime());
		show.setLanguage(cdto.getLanguage());
		show.setDuration(cdto.getDuration());
		show.setScreenno(cdto.getScreenno());
		show.setMovie(cdto.getMovie());
		show.setSeatcapacity(cdto.getSeatcapacity());
		show.setPrice(cdto.getPrice());

		List<Seat> seats = new ArrayList<>();
		int seatcapcity = cdto.getSeatcapacity();
		int catagorycapacity = seatcapcity / 3;
		int rows = catagorycapacity / 10;

		char row = 'A';

		for (int catagory = 1; catagory <= 3; catagory++) {

			String catagoryName;
			int catagoryprice;

			if (catagory == 1) {
				catagoryName = "SILVER";
				catagoryprice = cdto.getPrice();

			} else if (catagory == 2) {
				catagoryName = "GOLD";
				catagoryprice = 2 * (cdto.getPrice());
			} else {
				catagoryName = "PLATINUM";
				catagoryprice = 3 * (cdto.getPrice());
			}

			for (int i = 1; i <= rows; i++) {

				for (int j = 1; j <= 10; j++) {

					Seat seat = new Seat();

					seat.setSeatno(row + String.valueOf(j));
					seat.setCatogory(catagoryName);
					seat.setStatus("AVAILABLE");
					seat.setCatagoryprice(catagoryprice);
					seat.setShow(show);
					seats.add(seat);
				}

				row++;
			}
		}

		show.setSeats(seats);

		// showrepo.save(show);

		show.setMovie(movie);
		show.setTheater(th);

		th.getAllplayingshows().add(show);

		showrepo.save(show);

		ResponseStructure<Show> rs = new ResponseStructure<>();

		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMesaage("Show created successfully");
		rs.setData(show);

		return new ResponseEntity<>(rs, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Show>>> getAllShows() {

		List<Show> shows = showrepo.findAll();
		ResponseStructure<List<Show>> srs = new ResponseStructure<List<Show>>();
		System.out.println("shows:" + shows);
		srs.setStatuscode(HttpStatus.CREATED.value());
		srs.setMesaage("shows are all these");
		srs.setData(shows);
		return new ResponseEntity<ResponseStructure<List<Show>>>(srs, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<List<Show>>> findShowsInTheater(long theaterid) {

		// Show sh = showrepo.findBy(theaterid).orElseThrow(() -> new
		// ShowNotFoundException());
		// Optional<Show> showoptn=showrepo.findById(theaterid);
		List<Show> shows = showrepo.findByTheaterId(theaterid);
		// List<Show> show=showrepo.findAll();
		ResponseStructure<List<Show>> srs = new ResponseStructure<List<Show>>();
		System.out.println("shows:" + shows);
		srs.setStatuscode(HttpStatus.CREATED.value());
		srs.setMesaage("shows are all these");
		srs.setData(shows);
		return new ResponseEntity<ResponseStructure<List<Show>>>(srs, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<Show>> findShow(long id) {

		Show show = showrepo.findById(id).orElseThrow(() -> new ShowNotFoundException());
		ResponseStructure<Show> rst = new ResponseStructure<Show>();

		// if(showoptn.isPresent()) {
		// Show thr=show.get();

		rst.setStatuscode(HttpStatus.FOUND.value());
		rst.setMesaage("Theater accout is found");
		rst.setData(show);
		return new ResponseEntity<ResponseStructure<Show>>(rst, HttpStatus.FOUND);
		// }
//       else
//       {
//		System.out.println("theater account not created");
//		rt.setStatuscode(HttpStatus.NOT_FOUND.value());
//		rt.setMesaage("theater account not found");
//		rt.setData(null);
//		return new ResponseEntity<ResponseStructure<Show>>(rt,HttpStatus.NOT_FOUND);
//	
	}

	public ResponseEntity<ResponseStructure<Show>> updateShow(long id, CreateShowDTO cdto) {

		Show show = showrepo.findById(id).orElseThrow(() -> new ShowNotFoundException());

		show.setTitle(cdto.getTitle());
		// show.setDescription(cdto.getDescription());
		show.setDate(cdto.getDate());
		show.setTime(cdto.getTime());
		show.setLanguage(cdto.getLanguage());
		// show.setDuration(cdto.getDuration());
		show.setScreenno(cdto.getScreenno());

//    Movie movie = movierepo.findById(cdto.getMovieid()).orElseThrow(() -> new MovieNotFoundException());
//
//    show.setMovie(movie);

		showrepo.save(show);

		ResponseStructure<Show> rst = new ResponseStructure<>();

		rst.setStatuscode(HttpStatus.OK.value());
		rst.setMesaage("Show updated successfully");
		rst.setData(show);
		return new ResponseEntity<>(rst, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Movie>> findMovie(String title) {
		Movie movies = movierepo.findByTitle(title).orElseThrow(() -> new MovieNotFoundException());
		ResponseStructure<Movie> rst = new ResponseStructure<Movie>();

		rst.setStatuscode(HttpStatus.FOUND.value());
		rst.setMesaage("Movies is found");
		rst.setData(movies);
		return new ResponseEntity<ResponseStructure<Movie>>(rst, HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<List<Theater>>> findMoviesInTheater(String title, String language,
			String date) {

		// List<Theater> theaters =tr.findByTitleAndLanguageAndDate(title, language,
		// date);
		List<Theater> theaters = tr
				.findDistinctByAllplayingshows_Movie_TitleAndAllplayingshows_LanguageAndAllplayingshows_Date(title,
						language, date);
		if (theaters.isEmpty()) {

			throw new TheaterNotFoundException();
		}

		ResponseStructure<List<Theater>> trs = new ResponseStructure<>();

		trs.setStatuscode(HttpStatus.OK.value());
		trs.setMesaage("Theaters found successfully");
		trs.setData(theaters);
		return new ResponseEntity<ResponseStructure<List<Theater>>>(trs, HttpStatus.OK);
	}

	@Autowired
	private SeatRepository seatrepo;
//public ResponseEntity<ResponseStructure<List<Seat>>> selectSeatbyshowid(int showid) {
//	List<Seat> seats=seatrepo.findByShow_IdAndStatus(showid, "AVAILABLE");
//	List<Seat> seats1=seatrepo.findByShow_IdAndStatus(showid, "freez");
//	List<Seat> seats2=seatrepo.findByShow_IdAndStatus(showid, "booked");
//	if(seats.isEmpty()) {
//		throw new SeatsNotFoundException();
//	}
//	 ResponseStructure<List<Seat>> srs = new ResponseStructure<>();
//
//	    srs.setStatuscode(HttpStatus.OK.value());
//	    srs.setMesaage("Theaters found successfully");
//	    srs.setData(seats);
//	    srs.setData(seats1);
//	    srs.setData(seats2);
//	    return new ResponseEntity<ResponseStructure<List<Seat>>>(srs, HttpStatus.OK);
//	}

//public ResponseEntity<ResponseStructure<List<Seat>>> 
//selectSeatbyshowid(int showid) {
//
//    List<Seat> seats = seatrepo.findByShow_Id(showid);
//
//    if (seats.isEmpty()) {
//        throw new SeatsNotFoundException();
//    }
//
//    ResponseStructure<List<Seat>> srs =
//        new ResponseStructure<>();
//
//    srs.setStatuscode(HttpStatus.OK.value());
//    srs.setMesaage("All seats fetched successfully");
//    srs.setData(seats);
//
//    return new ResponseEntity<>(
//        srs, HttpStatus.OK
//    );
//}

	public ResponseEntity<ResponseStructure<List<Seat>>> selectSeatbyshowid(int showid) {

		List<Seat> seats = seatrepo.findByShow_Id(showid);

		System.out.println("Total seats: " + seats.size());

		for (Seat seat : seats) {
			System.out.println(seat.getSeatno() + " : " + seat.getStatus());
		}

		if (seats.isEmpty()) {
			throw new SeatsNotFoundException();
		}

		ResponseStructure<List<Seat>> srs = new ResponseStructure<>();

		srs.setStatuscode(HttpStatus.OK.value());
		srs.setMesaage("All seats fetched successfully");
		srs.setData(seats);

		return new ResponseEntity<ResponseStructure<List<Seat>>>(srs, HttpStatus.OK);
	}

//public ResponseEntity<ResponseStructure<Seat>> bookSeatbyselectingit(String title,String seatno) {
//	
//	//Seat seat=seatrepo.getBySeatno(seatno);
//	Show shw=showrepo.getByTitle(title);
//	 if (shw == null) {
//	        throw new ShowNotFoundException();
//	    }
//	Seat seet = seatrepo.findBySeatnoAndShow(seatno, shw);
//
//	if(seet==null) {
//		throw new SeatsNotFoundException();	
//	}
//	ResponseStructure<Seat> srs = new ResponseStructure<>();
//
//    srs.setStatuscode(HttpStatus.OK.value());
//    srs.setMesaage("Theaters found successfully");
//    srs.setData(seet);
//    return new ResponseEntity<ResponseStructure<Seat>>(srs, HttpStatus.OK);
//	
//	
//}

	public ResponseEntity<ResponseStructure<Seat>> bookSeatbyselectingit(long showid, String title, String seatno) {

		Show shw = showrepo.findById(showid).orElseThrow(() -> new ShowNotFoundException());

		Seat seat = seatrepo.findByShow_IdAndShowTitleAndSeatno(showid, title, seatno);

		if (seat == null) {
			throw new SeatsNotFoundException();
		}

		ResponseStructure<Seat> srs = new ResponseStructure<>();

		srs.setStatuscode(HttpStatus.OK.value());
		srs.setMesaage("Seat found successfully");
		srs.setData(seat);

		return new ResponseEntity<>(srs, HttpStatus.OK);
	}

//@Transactional
//public String bookSeats( BookSeatDTO bsdto) {
//	    List<Seat> seats =seatrepo.findByShow_IdAndSeatnoIn(bsdto.getShowid(),bsdto.getSeats() );
//
//	    if (seats.size() != bsdto.getSeats().size()) {
//	        throw new SeatsNotFoundException();
//	    }
//
//	    for (Seat seat : seats) {
//
//	        if (!seat.getStatus().equals("AVAILABLE")) {
//	            throw new RuntimeException("Seat " + seat.getSeatno() + " is already booked");
//	        }
//	    }
//	    for (Seat seat : seats) {
//	        seat.setStatus("freez");
//	    }
//	    seatrepo.saveAll(seats);
//	    
//	    
//	    return "Seats booked successfully";
//
//}

//@Transactional
//public BookSeatDTO bookSeats(BookSeatDTO bsdto) {
//
//    List<Seat> seats = seatrepo.findByShow_IdAndSeatnoIn(
//        bsdto.getShowid(),
//        bsdto.getSeats()
//    );
//
//    if (seats.size() != bsdto.getSeats().size()) {
//        throw new SeatsNotFoundException();
//    }
//
//    int totalprice = 0;
//
//    // Validate seats and calculate total price
//    for (Seat seat : seats) {
//
//        if (!seat.getStatus().equals("AVAILABLE")) {
//            throw new RuntimeException(
//                "Seat " + seat.getSeatno() + " is already booked"
//            );
//        }
//
//        totalprice += seat.getCatagoryprice();
//    }
//
//    // Freeze selected seats
//    for (Seat seat : seats) {
//        seat.setStatus("freez");
//    }
//
//    seatrepo.saveAll(seats);
//
//    // Set calculated price in DTO
//    bsdto.setTotalprice(totalprice);
//
//    return bsdto;
//}

	public BookSeatDTO bookSeats(BookSeatDTO bsdto) {

		List<Seat> seats = seatrepo.findByShow_IdAndSeatnoIn(bsdto.getShowid(), bsdto.getSeats());

		if (seats.size() != bsdto.getSeats().size()) {
			throw new SeatsNotFoundException();
		}

		int totalprice = 0;

		// 1. Validate permanent seat status
		for (Seat seat : seats) {

			if (!"AVAILABLE".equals(seat.getStatus())) {
				throw new RuntimeException("Seat " + seat.getSeatno() + " is already booked");
			}

			totalprice += seat.getCatagoryprice();
		}

		// 2. Acquire temporary Redis FREZES
		List<String> acquiredFreezs = new ArrayList<>();

		try {

			for (Seat seat : seats) {

				String key = "seatFreez:" + bsdto.getShowid() + ":" + seat.getSeatno();

				Boolean locked = redisTemplate.opsForValue().setIfAbsent(key, "customer:" + bsdto.getCustomerid(),
						Duration.ofMinutes(1));
				System.out.println("Redis key: " + key);
				System.out.println("Lock result: " + locked);

				if (!Boolean.TRUE.equals(locked)) {
					throw new RuntimeException("Seat " + seat.getSeatno() + " is already selected by another customer");
				}

				acquiredFreezs.add(key);
			}

			// 3. Set total price
			bsdto.setTotalprice(totalprice);

			return bsdto;
		} catch (RuntimeException e) {

			// 4. Release locks acquired during this attempt
			for (String key : acquiredFreezs) {
				redisTemplate.delete(key);
			}

			throw e;
		}
	}

	public ShowDeetsDTO findShowDetails(long showid) {

		Show show = showrepo.findById(showid).orElseThrow(() -> new ShowNotFoundException());

		ShowDeetsDTO dto = new ShowDeetsDTO();

		dto.setShowid(show.getId());
		dto.setShowTime(show.getTime());
		dto.setMovieName(show.getMovie().getTitle());
		dto.setScreenno((show.getScreenno()));
		dto.setDate(show.getDate());
		dto.setLanguage(show.getLanguage());
		dto.setTheatreName(show.getTheater().getName());
		dto.setThearterid((int) show.getTheater().getId());

		return dto;
	}

	public Theater UpdateTheaterbyid(Long theaterid, UpdateTheaterDTO uptdto) {
		Theater theater = tr.findById(theaterid).orElseThrow(() -> new TheaterNotFoundException());
//	 Optional<Theater> thr=tr.findById(theaterid);
//	if(thr.isEmpty()) {
//		throw new TheaterNotFoundException();
//	}
//	 Theater theater = thr.get();

		theater.setName(uptdto.getName());
		theater.setMailid(uptdto.getMailid());
		theater.setContactnumber(uptdto.getContactnum());
		theater.setTotalscreens(uptdto.getTotalscreens());

		return tr.save(theater);

	}

	public ResponseEntity<ResponseStructure<Show>> DeleteShowinTheater(long theaterid, long showid) {
		Theater theater = tr.findById(theaterid).orElseThrow(() -> new TheaterNotFoundException());
		Show showToDelete = null;
		for (Show show : theater.getAllplayingshows()) {

			if (show.getId() == showid) {
				showToDelete = show;
				break;
			}
		}
		if (showToDelete == null) {
			throw new ShowNotFoundException();
		}
		theater.getAllplayingshows().remove(showToDelete);
		tr.save(theater);
		showrepo.delete(showToDelete);
		ResponseStructure<Show> rst = new ResponseStructure<>();

		rst.setStatuscode(HttpStatus.OK.value());
		rst.setMesaage("Show deleted successfully");
		rst.setData(showToDelete);
		return new ResponseEntity<ResponseStructure<Show>>(rst, HttpStatus.OK);
	}

	public List<Show> ShowofMovieinTheater(long theaterid, String movieTitle) {
		Theater theater = tr.findById(theaterid).orElseThrow(() -> new TheaterNotFoundException());

		List<Show> result = new ArrayList<>();

		for (Show show : theater.getAllplayingshows()) {

			if (show.getMovie() != null && show.getMovie().getTitle() != null
					&& show.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
				result.add(show);
			}
		}

		return result;
	}

	public ResponseEntity<ResponseStructure<List<Show>>> ShowofDateinTheater(long theaterid, String date) {
		Theater theater = tr.findById(theaterid).orElseThrow(() -> new TheaterNotFoundException());

		List<Show> result = new ArrayList<>();
		for (Show show : theater.getAllplayingshows()) {

			if (show.getDate() != null && show.getDate().equals(date)) {
				result.add(show);
			}
		}
		if (result.isEmpty()) {
			throw new ShowNotFoundException();
		}

		ResponseStructure<List<Show>> rst = new ResponseStructure<>();

		rst.setStatuscode(HttpStatus.OK.value());
		rst.setMesaage("Shows found on date successfully");
		rst.setData(result);

		return new ResponseEntity<ResponseStructure<List<Show>>>(rst, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Show>>> ShowofScreennoinTheater(long theaterid, int screenno) {

		Theater theater = tr.findById(theaterid).orElseThrow(() -> new TheaterNotFoundException());

		List<Show> result = new ArrayList<>();

		for (Show show : theater.getAllplayingshows()) {

			if (show.getScreenno() == screenno) {
				result.add(show);
			}
		}

		if (result.isEmpty()) {
			throw new ShowNotFoundException();
		}

		ResponseStructure<List<Show>> rst = new ResponseStructure<>();

		rst.setStatuscode(HttpStatus.OK.value());
		rst.setMesaage("Shows found for screen successfully");
		rst.setData(result);

		return new ResponseEntity<ResponseStructure<List<Show>>>(rst, HttpStatus.OK);
	}

}
