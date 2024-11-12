package com.airlinereservationsystem.serviceImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlinereservationsystem.entity.Payments;
import com.airlinereservationsystem.exception.PaymentNotFoundException;
import com.airlinereservationsystem.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	Scanner sc = new Scanner(System.in);
	Session session;

	@Override
	public void insertPayment(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			System.out.println("Enter details of Payment");

			System.out.println("Enter Id for Payment:");
			int id = sc.nextInt();

			// Check if Payment with the given ID already exists
			Payments existingpayment = session.get(Payments.class, id);
			if (existingpayment != null) {
				System.out.println("Payment with ID " + id + " already exists.");
				return;
			}

			Transaction transaction = session.beginTransaction();
			Payments payment = new Payments();
			payment.setPaymentId(id);

			System.out.println("Enter Transaction Id for Payment:");
			String transactionId = sc.next();
			payment.setTransactionId(transactionId);

			System.out.println("Enter Amount for Payment:");
			float amount = sc.nextFloat();
			payment.setAmount(amount);

			System.out.println("Enter Payment Mode:");
			String paymentmode = sc.next();
			payment.setPaymentMode(paymentmode);

			session.persist(payment);
			transaction.commit();

			System.out.println("Payment details inserted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred while inserting payment details.");
		}

	}

	@Override
	public void updatePayment(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			while (true) {
				System.out.println(
						"Choose option for update:\n1. Update transaction Id\n2. Update amount\n3.Update payment mode\n4.Exit");
				int option = sc.nextInt();

				if (option == 4) {
					System.out.println("Exiting update menu.");
					break;
				}

				System.out.println("Enter Payment ID:");
				int paymentId = sc.nextInt();
				Payments payment = session.get(Payments.class, paymentId);

				if (payment == null) {
					throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found.");
					
				}

				Transaction transaction = session.beginTransaction();
				try {
					switch (option) {
					case 1:
						System.out.println("Enter New transaction Id:");
						payment.setTransactionId(sc.next());
						session.saveOrUpdate(payment);
						transaction.commit();
						System.out.println("Transaction Id updated successfully.");
						break;
					case 2:
						System.out.println("Enter New Amount:");
						payment.setAmount(sc.nextFloat());
						session.saveOrUpdate(payment);
						transaction.commit();
						System.out.println("Amount updated successfully.");
						break;
					case 3:
						System.out.println("Enter New Payment mode:");
						payment.setPaymentMode(sc.next());
						session.saveOrUpdate(payment);
						transaction.commit();
						System.out.println("Payment Mode updated successfully.");
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
	public void deletePayment(SessionFactory sf) {
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("Enter Payment Id:");
		Payments payment = session.get(Payments.class, sc.nextInt());
		if (payment != null)
			session.delete(payment);
		else {
	        try {
	            throw new PaymentNotFoundException("Payment with the given ID does not exist.");
	        } catch (PaymentNotFoundException e) {
	            System.out.println(e.getMessage());
	        }
	    }
		transaction.commit();
		session.close();

	}

	@Override
	public void getAllPayments(SessionFactory sf) {
		session = sf.openSession();

		Query query = session.createQuery("from Payments");
		List<Payments> resultList = query.getResultList();

		for (Payments p : resultList)
			System.out.println(p);
		session.close();

	}

	@Override
	public void getPayment(SessionFactory sf) {
		session = sf.openSession();
		System.out.println("Enter Payment Id:");
		Payments payment = session.get(Payments.class, sc.nextInt());
		if (payment != null) {
	        System.out.println(payment);
	    } else {
	        try {
	            throw new PaymentNotFoundException("Payment with the given ID does not exist.");
	        } catch (PaymentNotFoundException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    session.close();

	}

}
