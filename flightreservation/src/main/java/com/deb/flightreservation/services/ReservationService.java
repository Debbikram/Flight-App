package com.deb.flightreservation.services;

import com.deb.flightreservation.dto.ReservationRequest;
import com.deb.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
