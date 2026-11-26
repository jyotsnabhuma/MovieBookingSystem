package com.project.theaterserviceRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.project.theaterserviceEntity.Movie;
import com.project.theaterserviceEntity.Theater;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{
	
	Optional<Movie> findByTitle(String title);

	//List<Theater> findByTitleAndLanguageAndDate(String title, String languages,String date);

}
