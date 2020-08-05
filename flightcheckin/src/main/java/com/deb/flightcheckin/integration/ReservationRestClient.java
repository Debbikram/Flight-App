package com.deb.flightcheckin.integration;

import com.deb.flightcheckin.integration.dto.Reservation;
import com.deb.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);

}
