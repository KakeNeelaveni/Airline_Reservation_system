package com.airlinereservationsystem.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.airlinereservationsystem.entity.Tickets;
import com.airlinereservationsystem.serviceImpl.TicketServiceImpl;

public class TicketServiceImplTest {

    private TicketServiceImpl ticketService;
    private SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    public void setUp() {
        // Initialize the TicketServiceImpl instance
        ticketService = new TicketServiceImpl();
        
        // Mock the SessionFactory and Session
        sessionFactory = Mockito.mock(SessionFactory.class);
        session = Mockito.mock(Session.class);
        
        // When the sessionFactory opens a session, return the mocked session
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    public void testCheckTicketExists_ValidTicketId() {
        // Arrange
        int ticketId = 1; // Example of a valid ticket ID
        Tickets ticket = new Tickets();
        ticket.setTicketId(ticketId); // Setting the ticket ID in the mock
        
        // Mock the behavior of the session to return a ticket when queried with the valid ID
        when(session.get(Tickets.class, ticketId)).thenReturn(ticket);

        // Act
        boolean exists = ticketService.checkTicketExists(sessionFactory, ticketId);

        // Assert
        assertTrue(exists, "Ticket should exist but was not found.");
    }

    @Test
    public void testCheckTicketExists_InvalidTicketId() {
        // Arrange
        int ticketId = 2; // Example of an invalid ticket ID
        
        // Mock the behavior of the session to return null when queried with the invalid ID
        when(session.get(Tickets.class, ticketId)).thenReturn(null);

        // Act
        boolean exists = ticketService.checkTicketExists(sessionFactory, ticketId);

        // Assert
        assertFalse(exists, "Ticket should not exist but was found.");
    }
}
