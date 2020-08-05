package com.deb.flightreservation.repos;

import org.springframework.data.repository.CrudRepository;

import com.deb.flightreservation.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
