package com.airlinereservationsystem.service;

import org.hibernate.SessionFactory;

public interface AirlineService {
	
	void insertAirline(SessionFactory sf);
    void updateAirline(SessionFactory sf);
    void deleteAirline(SessionFactory sf);
    void getAllAirlines(SessionFactory sf);
    void getAirline(SessionFactory sf);
    

}
