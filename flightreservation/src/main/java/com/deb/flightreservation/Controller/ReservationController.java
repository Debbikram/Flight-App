package com.deb.flightreservation.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deb.flightreservation.dto.ReservationRequest;
import com.deb.flightreservation.entities.Flight;
import com.deb.flightreservation.entities.Reservation;
import com.deb.flightreservation.repos.FlightRepository;
import com.deb.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	private Logger LOGGER=LoggerFactory.getLogger(ReservationController.class);
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId")Long flightId,ModelMap modelMap)
	{
		LOGGER.info("Inside showCompleteReservation()"+"FlightIF"+flightId);
		Flight flight=flightRepository.findById(flightId).get();
		System.out.println("Entered123");
		modelMap.addAttribute("flight",flight);
		LOGGER.info("Flight is :"+flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request,ModelMap modelMap)
	{
		LOGGER.info("Inside Complete reservation() :"+request);
		System.out.println("Entered1234");
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg","Reservatin created successfully ans the id is"+reservation.getId());
		System.out.println("Entered12345");
		return "reservationConfirmation";
	}
	
}
