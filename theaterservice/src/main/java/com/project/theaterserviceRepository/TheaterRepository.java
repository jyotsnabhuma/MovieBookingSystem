package com.project.theaterserviceRepository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.project.theaterserviceEntity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long> {

	Optional<Theater> findByContactnumber(Long contactnumber);






	


	List<Theater> findDistinctByAllplayingshows_Movie_TitleAndAllplayingshows_LanguageAndAllplayingshows_Date(
			String title, String language, String date);



}
