package com.deb.flightreservation.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deb.flightreservation.Controller.FlightController;
import com.deb.flightreservation.dto.ReservationRequest;
import com.deb.flightreservation.entities.Flight;
import com.deb.flightreservation.entities.Passenger;
import com.deb.flightreservation.entities.Reservation;
import com.deb.flightreservation.repos.FlightRepository;
import com.deb.flightreservation.repos.PassengerRepository;
import com.deb.flightreservation.repos.ReservationRepository;
import com.deb.flightreservation.util.EmailUtil;
import com.deb.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("com.deb.flightcheckin.reservation.dirpath")
	private String ITINERARY_DIR;
	private Logger LOGGER=LoggerFactory.getLogger(ReservationServiceImpl.class);
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight()");
		//make Payment
		Long flightId=request.getFlightId();
		LOGGER.info("Fetching flight for flight id:"+flightId);
		Reservation reservation=new Reservation();
		Flight flight=null;
		Optional<Flight> flightOptional=flightRepository.findById(flightId);
		if(flightOptional.isPresent())
		{
			flight=flightOptional.get();
			reservation.setFlight(flight);
			System.out.println("Flight found");
		}

		
		Passenger passenger=new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger"+passenger);
		Passenger savedPassenger =passengerRepository.save(passenger);
		System.out.println("Pass created");
		
		
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving the reservation :"+reservation);
		Reservation savedReservation=reservationRepository.save(reservation);
		//System.out.println("Reservation created");
		LOGGER.info("Generating the itinerary:");
		String filePath = ITINERARY_DIR+savedReservation.getId()+".pdf";
		pdfGenerator.generateItinerary(savedReservation,
				filePath);
		//LOGGER.info("Emailing the itinerary:");
		//emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		
		return savedReservation;
	}

}
