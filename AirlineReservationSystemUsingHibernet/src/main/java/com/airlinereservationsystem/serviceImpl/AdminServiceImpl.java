package com.airlinereservationsystem.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlinereservationsystem.entity.Admin;
import com.airlinereservationsystem.entity.Users;
import com.airlinereservationsystem.service.AdminService;

public class AdminServiceImpl implements AdminService{

	Scanner sc = new Scanner(System.in);
	Session session;


	@Override
	public void insertAdmin(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		 
		        
		        try {
		            Admin admin=new Admin();
		            System.out.println("Enter details of admin:");

		            System.out.print("Enter admin ID: ");
		            int adnimId = sc.nextInt();
		            admin.setAdminId(adnimId);

		            System.out.print("Enter admin Name: ");
		            sc.nextLine(); // consume newline
		            String adnimName = sc.nextLine();
		            admin.setName(adnimName);

		            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
		            String dobStr = sc.nextLine();
		            LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		            admin.setDob(dob);

		            System.out.print("Enter Phone Number: ");
		            Long phoneNumber = sc.nextLong();
		            admin.setPhoneNumber(phoneNumber);

		            System.out.print("Enter Gender: ");
		            sc.nextLine(); // consume newline
		            String gender = sc.nextLine();
		            admin.setGender(gender);
		            System.out.print("Enter Address: ");
		            String address = sc.nextLine();
		            admin.setAddress(address);
		            // Persist admin data
		            session.persist(admin);
		            transaction.commit();
		            System.out.println("Admin details inserted successfully!");

		        } catch (Exception e) {
		            transaction.rollback();
		            e.printStackTrace();
		            System.out.println("An error occurred while inserting admin details.");
		        } finally {
		            session.close();
		        }
		    }
		
		
	

	@Override
	public void updateAdmin(SessionFactory sf) {
	    try (Session session = sf.openSession()) { // try-with-resources to ensure session is closed
	        Transaction transaction = session.beginTransaction();

	        try {
	            System.out.print("Enter adnim ID to update: ");
	            int adminId = sc.nextInt();
	            Admin admin = session.get(Admin.class, adminId);

	            if (admin == null) {
	                System.out.println("admin with ID " + adminId + " not found.");
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
	                        admin.setName(newName);
	                        System.out.println("admin name updated successfully.");
	                        break;

	                    case 2:
	                        System.out.print("Enter New Date of Birth (yyyy-MM-dd): ");
	                        String dobStr = sc.nextLine();
	                        LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	                        admin.setDob(dob);
	                        System.out.println("Date of Birth updated successfully.");
	                        break;

	                    case 3:
	                        System.out.print("Enter New Phone Number: ");
	                        Long newPhoneNumber = sc.nextLong();
	                        admin.setPhoneNumber(newPhoneNumber);
	                        System.out.println("Phone number updated successfully.");
	                        break;

	                    case 4:
	                        System.out.print("Enter New Gender: ");
	                        String newGender = sc.nextLine();
	                        admin.setGender(newGender);
	                        System.out.println("Gender updated successfully.");
	                        break;

	                    case 5:
	                        System.out.print("Enter New Address: ");
	                        String newAddress = sc.nextLine();
	                        admin.setAddress(newAddress);
	                        System.out.println("Address updated successfully.");
	                        break;

	                    default:
	                        System.out.println("Invalid option. Please choose again.");
	                        continue;
	                }

	                // Save the updated admin
	                session.saveOrUpdate(admin);
	                transaction.commit();
	                System.out.println("admin details updated successfully!");

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
	public void deleteAdmin(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Admin Id:");
		Admin admin = session.get(Admin.class, sc.nextInt());
		if (admin != null)
			session.delete(admin);
		else
			System.out.println("Enter valid Admin Id");
		transaction.commit();
		session.close();

		
	}

	@Override
	public void getAllAdmins(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("from Admin");
		List<Admin> resultList = query.getResultList();

		for (Admin a : resultList)
			System.out.println(a);
		session.close();
		
	}

	@Override
	public void getAdmin(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Admin Id:");
		Admin admin = session.get(Admin.class, sc.nextInt());
		if (admin != null)
			System.out.println(admin);
		else
			System.out.println("Enter valid Admin Id");
		
		session.close();
		
	}

}
