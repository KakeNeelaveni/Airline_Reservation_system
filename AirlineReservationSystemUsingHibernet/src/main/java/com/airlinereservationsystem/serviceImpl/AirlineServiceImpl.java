package com.airlinereservationsystem.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlinereservationsystem.entity.Airlines;

import com.airlinereservationsystem.service.AirlineService;

public class AirlineServiceImpl implements AirlineService {

	Scanner sc = new Scanner(System.in);
	Session session;

	@Override
	public void insertAirline(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			System.out.println("Enter details of Airline");

			System.out.println("Enter Id for Airline:");
			int id = sc.nextInt();

			// Check if an airline with the given ID already exists
			Airlines existingAirline = session.get(Airlines.class, id);
			if (existingAirline != null) {
				System.out.println("Airline with ID " + id + " already exists.");
				return;
			}

			Transaction transaction = session.beginTransaction();
			Airlines airline = new Airlines();
			airline.setAirlineId(id);

			System.out.println("Enter name for Airline");
			String airlineName = sc.next();
			airline.setAirlineName(airlineName);

			System.out.println("Enter contact number for Airline");
			long contactNo = sc.nextLong();
			airline.setContactNo(contactNo);

			session.persist(airline);
			transaction.commit();

			System.out.println("Airline inserted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while inserting airline.");
		}
	}

	@Override
	public void updateAirline(SessionFactory sf) {
		try (Session session = sf.openSession()) {
            while (true) {
                System.out.println("Choose option for update:\n1. Update Airline Name\n2. Update Airline Contact Number\n3. Exit");
                int option = sc.nextInt();
                
                if (option == 3) {
                    System.out.println("Exiting update menu.");
                    break;
                }

                System.out.println("Enter Airline ID:");
                int airlineId = sc.nextInt();
                Airlines airline = session.get(Airlines.class, airlineId);

                if (airline == null) {
                    System.out.println("Airline with ID " + airlineId + " not found.");
                    continue;
                }

                Transaction transaction = session.beginTransaction();
                try {
                    switch (option) {
                        case 1:
                            System.out.println("Enter New Airline Name:");
                            airline.setAirlineName(sc.next());
                            session.saveOrUpdate(airline);
                            transaction.commit();
                            System.out.println("Airline name updated successfully.");
                            break;
                        case 2:
                            System.out.println("Enter New Airline Contact Number:");
                            airline.setContactNo(sc.nextLong());
                            session.saveOrUpdate(airline);
                            transaction.commit();
                            System.out.println("Airline contact number updated successfully.");
                            break;
                        default:
                            System.out.println("Invalid option. Please choose again.");
                    }
                } catch (Exception e) {
                    transaction.rollback();
                    e.printStackTrace();
                    System.out.println("Update failed, transaction rolled back.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during the update operation.");
        }
    }
	

	@Override
	public void deleteAirline(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Airline Id:");
		Airlines airline = session.get(Airlines.class, sc.nextInt());
		if (airline != null)
			session.delete(airline);
		else
			System.out.println("Enter valid Airline Id");
		transaction.commit();
		session.close();

	}

	@Override
	public void getAllAirlines(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("from Airlines");
		List<Airlines> resultList = query.getResultList();

		for (Airlines a : resultList)
			System.out.println(a);
		session.close();

	}

	@Override
	public void getAirline(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Airline Id:");
		Airlines airline = session.get(Airlines.class, sc.nextInt());
		System.out.println(airline);
		session.close();

	}

}
