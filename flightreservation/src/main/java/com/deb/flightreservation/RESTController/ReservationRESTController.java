package com.deb.flightreservation.RESTController;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deb.flightreservation.Controller.FlightController;
import com.deb.flightreservation.dto.ReservationUpdateRequest;
import com.deb.flightreservation.entities.Reservation;
import com.deb.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRESTController {
	private Logger LOGGER=LoggerFactory.getLogger(ReservationRESTController.class);
	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id)
	{
		LOGGER.info("Inside findReservation():"+id);
		Optional<Reservation> reservationOptional=reservationRepository.findById(id);
		if(reservationOptional.isPresent())
			return reservationOptional.get();
		
		return null;
		
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request)
	{
		LOGGER.info("Inside UpdateReservation():"+request);
		Reservation reservation=null;
		Optional<Reservation> reservationOptional=reservationRepository.findById(request.getId());
		if(reservationOptional.isPresent())
			reservation=reservationOptional.get();
		
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		
		LOGGER.info("Saving Reservation");
		Reservation updatedReservation=reservationRepository.save(reservation);
		return updatedReservation;
		
	}

}
