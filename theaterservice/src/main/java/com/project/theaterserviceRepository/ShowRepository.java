package com.project.theaterserviceRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.theaterserviceEntity.Movie;
import com.project.theaterserviceEntity.Show;
import com.project.theaterserviceEntity.Theater;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
	
	

	Optional<Show> findByMovie(Movie movie);


	Optional<Show> findByDateAndTimeAndScreennoAndTheater(String date, String time, int screenno, Theater th);


	List<Show> findByTheaterId(long theaterid);


	List<Theater> findByTitleAndLanguageAndDate(String title, String language,String date);




	Show getByTitle(String title);


	
	
}
