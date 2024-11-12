package com.airlinereservationsystem.service;

import org.hibernate.SessionFactory;

public interface FlightService {
	
	void insertFlight(SessionFactory sf);
    void updateFlight(SessionFactory sf);
    void deleteFlight(SessionFactory sf);
    void getAllFlights(SessionFactory sf);
    void getFlight(SessionFactory sf);

}
