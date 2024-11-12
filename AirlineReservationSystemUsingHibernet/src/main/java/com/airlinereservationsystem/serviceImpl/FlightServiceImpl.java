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
import com.airlinereservationsystem.exception.FlightNotFoundException;
import com.airlinereservationsystem.service.FlightService;



public class FlightServiceImpl implements FlightService {

	Scanner sc = new Scanner(System.in);
	Session session;

	@Override
	public void insertFlight(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Flights flight = new Flights();
		System.out.println("Enter details of Flight");
		System.out.println("Enter Id for Flight:");
		int id = sc.nextInt();
		flight.setFlightId(id);
		System.out.println("Enter source for Flight:");
		String source = sc.next();
		flight.setSource(source);
		System.out.println("Enter destination for Flight:");
		String destination = sc.next();
		flight.setDestination(destination);
		System.out.print("Enter departureTime (yyyy-MM-dd HH:mm): ");
		String depTimeStr = sc.next() + " " + sc.next();
		LocalDateTime depTime = LocalDateTime.parse(depTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		flight.setDepartureTime(depTime);
		System.out.print("Enter arrivalTime (yyyy-MM-dd HH:mm): ");
		String arrTimeStr = sc.next() + " " + sc.next();
		LocalDateTime arrTime = LocalDateTime.parse(arrTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		flight.setArrivalTime(arrTime);
		System.out.print("Enter available seats: ");
		int seats = sc.nextInt();
		flight.setSeatAvailable(seats);

		System.out.print("Enter Airline ID: ");
		int airlineId = sc.nextInt();
		Airlines airline = session.get(Airlines.class, airlineId);
		flight.setAirline(airline);

		System.out.print("Enter Class ID: ");
		int classId = sc.nextInt();
		Classes flightClass = session.get(Classes.class, classId);
		flight.setFlightClass(flightClass);

		session.persist(flight);
		transaction.commit();
		session.close();
		System.out.println("Flight inserted successfully!");

	}

	@Override
	public void updateFlight(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			Transaction transaction = session.beginTransaction();

			while (true) {
				System.out.println("Choose option for update:\n1. Source\n2. Destination\n3. Departure Time"
						+ "\n4. Arrival Time\n5. Seat Availability\n6. Airline Id\n7. Class Id\n8. Exit");
				int option = sc.nextInt();

				if (option == 8) {
					System.out.println("Exiting update process.");
					break;
				}

				System.out.println("Enter Flight Id:");
				Flights flight = session.get(Flights.class, sc.nextInt());

				if (flight == null) {
					System.out.println("Flight not found. Please try again.");
					continue;
				}

				switch (option) {
				case 1:
					System.out.println("Enter new source:");
					flight.setSource(sc.next());
					break;

				case 2:
					System.out.println("Enter new destination:");
					flight.setDestination(sc.next());
					break;

				case 3:
					System.out.println("Enter new Departure Time (yyyy-MM-dd HH:mm):");
					String depTimeStr = sc.next() + " " + sc.next();
					LocalDateTime depTime = LocalDateTime.parse(depTimeStr,
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
					flight.setDepartureTime(depTime);
					break;

				case 4:
					System.out.println("Enter new Arrival Time (yyyy-MM-dd HH:mm):");
					String arrTimeStr = sc.next() + " " + sc.next();
					LocalDateTime arrTime = LocalDateTime.parse(arrTimeStr,
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
					flight.setArrivalTime(arrTime);
					break;

				case 5:
					System.out.println("Enter new Seat Availability:");
					flight.setSeatAvailable(sc.nextInt());
					break;

				case 6:
					System.out.println("Enter new Airline Id:");
					int airlineId = sc.nextInt();
					Airlines airline = session.get(Airlines.class, airlineId);
					if (airline != null) {
						flight.setAirline(airline);
					} else {
						System.out.println("Airline not found.");
					}
					break;

				case 7:
					System.out.println("Enter new Class Id:");
					int classId = sc.nextInt();
					Classes flightClass = session.get(Classes.class, classId);
					if (flightClass != null) {
						flight.setFlightClass(flightClass);
					} else {
						System.out.println("Class not found.");
					}
					break;

				default:
					System.out.println("Invalid option. Please try again.");
					continue;
				}

				session.saveOrUpdate(flight); // Save after each change
			}

			transaction.commit(); // Commit once after all changes are made
			System.out.println("Flight details updated successfully.");
		} 
		catch (Exception e) {
			System.out.println("Error occurred: " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void deleteFlight(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Flight Id:");
		Flights flight = session.get(Flights.class, sc.nextInt());
		if (flight != null)
			session.delete(flight);
		else
			System.out.println("Enter valid flight Id");
		transaction.commit();
		session.close();
	}

	@Override
	public void getAllFlights(SessionFactory sf) {
        session = sf.openSession();
		
		Query query=session.createQuery("from Flights");
        List<Flights>resultList=query.getResultList();
        
        for(Flights f:resultList)
        	System.out.println(f);
        session.close();

	}

	@Override
	public void getFlight(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Flight Id:");
		Flights flight = session.get(Flights.class, sc.nextInt());
		System.out.println(flight);
		session.close();
	}

}
