package com.airlinereservationsystem.service;

import org.hibernate.SessionFactory;

public interface TicketService {
	
	void insertTicket(SessionFactory sf);
    void updateTicket(SessionFactory sf);
    void deleteTicket(SessionFactory sf);
    void getAllTickets(SessionFactory sf);
    void getTicket(SessionFactory sf);

}
