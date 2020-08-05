package com.deb.flightreservation.repos;

import org.springframework.data.repository.CrudRepository;

import com.deb.flightreservation.entities.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {

}
