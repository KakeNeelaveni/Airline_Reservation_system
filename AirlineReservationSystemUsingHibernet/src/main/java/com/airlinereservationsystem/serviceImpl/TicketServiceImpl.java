package com.airlinereservationsystem.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlinereservationsystem.entity.Airlines;
import com.airlinereservationsystem.entity.Classes;
import com.airlinereservationsystem.entity.Flights;
import com.airlinereservationsystem.entity.Payments;
import com.airlinereservationsystem.entity.Tickets;
import com.airlinereservationsystem.entity.Users;
import com.airlinereservationsystem.service.TicketService;

public class TicketServiceImpl implements TicketService {

	Scanner sc = new Scanner(System.in);
	Session session;

	@Override
	public void insertTicket(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			System.out.println("Enter details of Ticket");

			System.out.println("Enter Id for Ticket:");
			int id = sc.nextInt();

			// Check if ticket with the given ID already exists
			Tickets existingTicket = session.get(Tickets.class, id);
			if (existingTicket != null) {
				System.out.println("Ticket with ID " + id + " already exists.");
				return;
			}

			Transaction transaction = session.beginTransaction();
			try {
				Tickets ticket = new Tickets();
				ticket.setTicketId(id);

				System.out.println("Enter PNR number for Ticket:");
				String pnrNo = sc.next();
				ticket.setPnrNo(pnrNo);

				System.out.println("Enter date and time (yyyy-MM-dd HH:mm) for Ticket:");
				String dateTimeStr = sc.next() + " " + sc.next();
				LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr,
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
				ticket.setDateTime(dateTime);

				System.out.print("Enter User ID: "); // Prompt for userId
				int userId = sc.nextInt();
				Users user = session.get(Users.class, userId);
				if (user != null) {
					ticket.setUser(user);
				} else {
					System.out.println("User with ID " + userId + " not found.");
					return;
				}

				System.out.print("Enter Flight ID: ");
				int flightId = sc.nextInt();
				Flights flight = session.get(Flights.class, flightId);
				if (flight != null) {
					ticket.setFlight(flight);
				} else {
					System.out.println("Flight with ID " + flightId + " not found.");
					return;
				}

				System.out.print("Enter Class ID: ");
				int classId = sc.nextInt();
				Classes ticketClass = session.get(Classes.class, classId);
				if (ticketClass != null) {
					ticket.setTicketClass(ticketClass);
				} else {
					System.out.println("Class with ID " + classId + " not found.");
					return;
				}

				System.out.print("Enter Transaction ID: ");
				String transactionId = sc.next();
				Payments payment = session.get(Payments.class, transactionId);
				if (payment != null) {
					ticket.setPayment(payment);
				} else {
					System.out.println("Payment with Transaction ID " + transactionId + " not found.");
					return;
				}

				session.persist(ticket);
				transaction.commit();
				System.out.println("Ticket details inserted successfully!");

			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
				System.out.println("An error occurred while inserting Ticket details.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while establishing the session.");
		}
	}

	@Override
	public void updateTicket(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			System.out.println("Enter Ticket ID to update:");
			int ticketId = sc.nextInt();
			Tickets ticket = session.get(Tickets.class, ticketId);

			if (ticket == null) {
				System.out.println("Ticket with ID " + ticketId + " not found.");
				return;
			}

			Transaction transaction = session.beginTransaction();
			try {
				while (true) {
					System.out.println(
							"Choose option for update:\n1. Update PNR Number\n2. Update Date and Time\n3. Exit");
					int option = sc.nextInt();

					if (option == 3) {
						System.out.println("Exiting update menu.");
						break;
					}

					switch (option) {
					case 1:
						System.out.println("Enter New PNR Number:");
						ticket.setPnrNo(sc.next());
						System.out.println("PNR Number updated successfully.");
						break;

					case 2:
						System.out.println("Enter New Date and Time (yyyy-MM-dd HH:mm) for Ticket:");
						String dateTimeStr = sc.next() + " " + sc.next();
						LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr,
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
						ticket.setDateTime(dateTime);
						System.out.println("Date and Time updated successfully.");
						break;

					default:
						System.out.println("Invalid option. Please choose again.");
						continue;
					}

					session.saveOrUpdate(ticket);
					transaction.commit();
					System.out.println("Ticket updated successfully!");
				}
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
				System.out.println("Update failed, transaction rolled back.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred during the update operation.");
		}
	}

	@Override
	public void deleteTicket(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Ticket Id:");
		Tickets ticket = session.get(Tickets.class, sc.nextInt());
		if (ticket != null)
			session.delete(ticket);
		else
			System.out.println("Enter valid ticket Id");
		transaction.commit();
		session.close();
	}

	@Override
	public void getAllTickets(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("from Tickets");
		List<Tickets> resultList = query.getResultList();

		for (Tickets t : resultList)
			System.out.println(t);
		session.close();

	}

	@Override
	public void getTicket(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter ticket Id:");
		Tickets ticket = session.get(Tickets.class, sc.nextInt());
		System.out.println(ticket);
		session.close();

	}
	
	public boolean checkTicketExists(SessionFactory sf, int ticketId) {
	    try (Session session = sf.openSession()) {
	        Tickets ticket = session.get(Tickets.class, ticketId);
	        return ticket != null; // Return true if the ticket exists, false otherwise
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
