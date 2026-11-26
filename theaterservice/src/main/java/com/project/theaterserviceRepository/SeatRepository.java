package com.project.theaterserviceRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.theaterserviceEntity.Seat;
import com.project.theaterserviceEntity.Show;

public interface SeatRepository extends JpaRepository<Seat,Integer> {

	
	
	List<Seat> findByShow_IdAndStatus(int id, String status);
	Seat getBySeatno(String seatno);
	Seat findBySeatnoAndShow(String seatno, Show shw);
	Seat findByShow_IdAndShowTitleAndSeatno(long showid, String title, String seatno);
	List<Seat> findByShow_IdAndSeatnoIn(long showid, List<String> seats);
	List<Seat> findByShow_Id(int showid);

}
