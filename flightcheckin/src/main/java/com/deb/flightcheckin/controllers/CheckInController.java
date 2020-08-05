package com.deb.flightcheckin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deb.flightcheckin.integration.ReservationRestClient;
import com.deb.flightcheckin.integration.dto.Reservation;
import com.deb.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController 
{
	
	@Autowired
	ReservationRestClient restClient;

	
	@RequestMapping("/showStartCheckIn")
	public String showStartCheckIn()
	{
		return "startCheckIn";
	}
	
	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) 
	{
		System.out.println("Here1");
		Reservation reservation = restClient.findReservation(reservationId);
		System.out.println("Here");
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,@RequestParam("numberOfBags") int numberOfBags)
	{
		ReservationUpdateRequest reservationUpdateRequest=new ReservationUpdateRequest();
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setCheckedIn(true);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);
		restClient.updateReservation(reservationUpdateRequest);
		return "checkInConfirmation";
		
	}

}
