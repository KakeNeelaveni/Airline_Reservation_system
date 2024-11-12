package com.airlinereservationsystem.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.airlinereservationsystem.entity.Classes;
import com.airlinereservationsystem.service.ClassService;

public class ClassServiceImpl implements ClassService {
	Scanner sc = new Scanner(System.in);
	Session session;

	@Override
	public void insertClass(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			System.out.println("Enter details of Class");

			System.out.println("Enter Id for Class:");
			int id = sc.nextInt();

			// Check if a class with the given ID already exists
			Classes existingclass = session.get(Classes.class, id);
			if (existingclass != null) {
				System.out.println("Class with ID " + id + " already exists.");
				return;
			}

			Transaction transaction = session.beginTransaction();
			Classes classt=new Classes();
			classt.setClassId(id);

			System.out.println("Enter Class type:");
			String classtype = sc.next();
			classt.setClassType(classtype);

			System.out.println("Enter fare for Class");
			float fare=sc.nextFloat(); 
			classt.setFare(fare);

			session.persist(classt);
			transaction.commit();

			System.out.println("Class Details inserted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while inserting Class details.");
		}
		
	}

	@Override
	public void updateClass(SessionFactory sf) {
		try (Session session = sf.openSession()) {
            while (true) {
                System.out.println("Choose option for update:\n1. Update Class Type\n2. Update Class Fare\n3. Exit");
                int option = sc.nextInt();
                
                if (option == 3) {
                    System.out.println("Exiting update menu.");
                    break;
                }

                System.out.println("Enter Class ID:");
                int classId = sc.nextInt();
                Classes classt = session.get(Classes.class, classId);

                if (classt == null) {
                    System.out.println("Class with ID " + classId + " not found.");
                    continue;
                }

                Transaction transaction = session.beginTransaction();
                try {
                    switch (option) {
                        case 1:
                            System.out.println("Enter New Class Type:");
                            classt.setClassType(sc.next());
                            session.saveOrUpdate(classt);
                            transaction.commit();
                            System.out.println("Class Type updated successfully.");
                            break;
                        case 2:
                            System.out.println("Enter New Fare for Class:");
                            classt.setFare(sc.nextFloat());
                            session.saveOrUpdate(classt);
                            transaction.commit();
                            System.out.println("Class Price updated successfully.");
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
	public void deleteClass(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Class Id:");
		Classes classt = session.get(Classes.class, sc.nextInt());
		if (classt != null)
			session.delete(classt);
		else
			System.out.println("Enter valid Class Id");
		transaction.commit();
		session.close();
		
	}

	@Override
	public void getAllClasses(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("from Classes");
		List<Classes> resultList = query.getResultList();

		for (Classes c : resultList)
			System.out.println(c);
		session.close();

		
	}

	@Override
	public void getClass(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Class Id:");
		Classes classt = session.get(Classes.class, sc.nextInt());
		System.out.println(classt);
		session.close();
		
	}

}
