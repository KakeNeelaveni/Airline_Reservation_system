package com.airlinereservationsystem.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlinereservationsystem.entity.Airlines;
import com.airlinereservationsystem.entity.Users;
import com.airlinereservationsystem.service.UserService;



public class UserServiceImpl implements UserService {
	
	Scanner sc = new Scanner(System.in);
	Session session;


	@Override
	public void insertUser(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		 
		        
		        try {
		            Users user = new Users();

		            System.out.println("Enter details of User:");

		            System.out.print("Enter User ID: ");
		            int userId = sc.nextInt();
		            user.setUserId(userId);

		            System.out.print("Enter User Name: ");
		            sc.nextLine(); // consume newline
		            String userName = sc.nextLine();
		            user.setUserName(userName);

		            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
		            String dobStr = sc.nextLine();
		            LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		            user.setDob(dob);

		            System.out.print("Enter Phone Number: ");
		            Long phoneNumber = sc.nextLong();
		            user.setPhoneNumber(phoneNumber);

		            System.out.print("Enter Gender: ");
		            sc.nextLine(); // consume newline
		            String gender = sc.nextLine();
		            user.setGender(gender);

		            System.out.print("Enter Address: ");
		            String address = sc.nextLine();
		            user.setAddress(address);

		            // Persist user data
		            session.persist(user);
		            transaction.commit();
		            System.out.println("User details inserted successfully!");

		        } catch (Exception e) {
		            transaction.rollback();
		            e.printStackTrace();
		            System.out.println("An error occurred while inserting User details.");
		        } finally {
		            session.close();
		        }
		    }
		
		
	

	@Override
	public void updateUser(SessionFactory sf) {
	    try (Session session = sf.openSession()) { // try-with-resources to ensure session is closed
	        Transaction transaction = session.beginTransaction();

	        try {
	            System.out.print("Enter User ID to update: ");
	            int userId = sc.nextInt();
	            Users user = session.get(Users.class, userId);

	            if (user == null) {
	                System.out.println("User with ID " + userId + " not found.");
	                return;
	            }

	            while (true) {
	                System.out.println("Choose option to update:\n1. Update Name\n2. Update Date of Birth\n3. Update Phone Number\n4. Update Gender\n5. Update Address\n6. Exit");
	                int option = sc.nextInt();
	                sc.nextLine(); // consume newline

	                if (option == 6) {
	                    System.out.println("Exiting update menu.");
	                    break;
	                }

	                switch (option) {
	                    case 1:
	                        System.out.print("Enter New Name: ");
	                        String newName = sc.nextLine();
	                        user.setUserName(newName);
	                        System.out.println("User name updated successfully.");
	                        break;

	                    case 2:
	                        System.out.print("Enter New Date of Birth (yyyy-MM-dd): ");
	                        String dobStr = sc.nextLine();
	                        LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	                        user.setDob(dob);
	                        System.out.println("Date of Birth updated successfully.");
	                        break;

	                    case 3:
	                        System.out.print("Enter New Phone Number: ");
	                        Long newPhoneNumber = sc.nextLong();
	                        user.setPhoneNumber(newPhoneNumber);
	                        System.out.println("Phone number updated successfully.");
	                        break;

	                    case 4:
	                        System.out.print("Enter New Gender: ");
	                        String newGender = sc.nextLine();
	                        user.setGender(newGender);
	                        System.out.println("Gender updated successfully.");
	                        break;

	                    case 5:
	                        System.out.print("Enter New Address: ");
	                        String newAddress = sc.nextLine();
	                        user.setAddress(newAddress);
	                        System.out.println("Address updated successfully.");
	                        break;

	                    default:
	                        System.out.println("Invalid option. Please choose again.");
	                        continue;
	                }

	                // Save the updated user
	                session.saveOrUpdate(user);
	                transaction.commit();
	                System.out.println("User updated successfully!");

	                // Start a new transaction for further updates if needed
	                transaction = session.beginTransaction();
	            }
	        } catch (Exception e) {
	            if (transaction != null && transaction.isActive()) {
	                transaction.rollback();
	                System.out.println("Update failed, transaction rolled back.");
	            }
	            e.printStackTrace();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("An error occurred during the update operation.");
	    }
	}

		
	

	@Override
	public void deleteUser(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter User Id:");
		Users user = session.get(Users.class, sc.nextInt());
		if (user != null)
			session.delete(user);
		else
			System.out.println("Enter valid user Id");
		transaction.commit();
		session.close();

		
	}

	@Override
	public void getAllUsers(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("from Users");
		List<Users> resultList = query.getResultList();

		for (Users u : resultList)
			System.out.println(u);
		session.close();
		
	}

	@Override
	public void getUser(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter User Id:");
		Users user = session.get(Users.class, sc.nextInt());
		System.out.println(user);
		session.close();
		
	}

}
