package com.airlinereservationsystem.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.airlinereservationsystem.entity.Admin;
import com.airlinereservationsystem.entity.Airlines;
import com.airlinereservationsystem.entity.Classes;
import com.airlinereservationsystem.entity.Flights;
import com.airlinereservationsystem.entity.Payments;
import com.airlinereservationsystem.entity.Tickets;
import com.airlinereservationsystem.entity.Users;
import com.airlinereservationsystem.serviceImpl.AdminServiceImpl;
import com.airlinereservationsystem.serviceImpl.AirlineServiceImpl;
import com.airlinereservationsystem.serviceImpl.ClassServiceImpl;
import com.airlinereservationsystem.serviceImpl.FlightServiceImpl;
import com.airlinereservationsystem.serviceImpl.PaymentServiceImpl;
import com.airlinereservationsystem.serviceImpl.TicketServiceImpl;
import com.airlinereservationsystem.serviceImpl.UserServiceImpl;
import com.airlinereservationsystem.utility.ConfiguarationUtility;

public class AirlineReservationSystem {

	public static void main(String[] args) {
		SessionFactory getsFactory = ConfiguarationUtility.getsFactory();
		Session openSession = getsFactory.openSession();
		Transaction transaction = openSession.beginTransaction();

		Scanner sc = new Scanner(System.in);
		FlightServiceImpl flightservice = new FlightServiceImpl();
		AirlineServiceImpl airlineservice = new AirlineServiceImpl();
		ClassServiceImpl classservice = new ClassServiceImpl();
		PaymentServiceImpl paymentservice = new PaymentServiceImpl();
		TicketServiceImpl ticketservice = new TicketServiceImpl();
		UserServiceImpl userservice = new UserServiceImpl();
		AdminServiceImpl adminservice = new AdminServiceImpl();
		try {
			while (true) {
				System.out.println("Welcome to Airlines ");
				System.out.println("Login as:\n1. Admin\n2. User\n3. Exit");
				int option = sc.nextInt();

				/*if (option == 3) {
					System.out.println("Exiting the system. Goodbye!");
					break;
				}*/

				switch (option) {
				case 1:
					System.out.println("Enter Admin Id:");
					int adminId = sc.nextInt();
					try (Session session = getsFactory.openSession()) {
						Admin admin = session.get(Admin.class, adminId);
						if (admin != null) {
							System.out.println("Admin logged in successfully.");

							while (true) {
								System.out.println(
										"Choose category:\n1. Flight\n2. Airline\n3. Class\n4. Payment\n5. Ticket\n6. User\n7. Admin\n8. Exit");
								int mainOption = sc.nextInt();

								switch (mainOption) {
								case 1: // Flight options
									while (true) {
										System.out.println(
												"Flight Options:\n1. Insert Flight\n2. Update Flight\n3. Delete Flight\n4. Get All Flights\n5. Get Flight\n6. Return to Main Menu");
										int flightOption = sc.nextInt();
										switch (flightOption) {
										case 1:
											flightservice.insertFlight(getsFactory);
											break;
										case 2:
											flightservice.updateFlight(getsFactory);
											break;
										case 3:
											flightservice.deleteFlight(getsFactory);
											break;
										case 4:
											flightservice.getAllFlights(getsFactory);
											break;
										case 5:
											flightservice.getFlight(getsFactory);
											break;
										case 6:
											System.out.println("Returning to Main Menu");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (flightOption == 6)
											break; // Exit to main menu if selected
									}
									break;

								case 2: // Airline options
									while (true) {
										System.out.println(
												"Airline Options:\n1. Insert Airline\n2. Update Airline\n3. Delete Airline\n4. Get All Airlines\n5. Get Airline\n6. Return to Main Menu");
										int airlineOption = sc.nextInt();
										switch (airlineOption) {
										case 1:
											airlineservice.insertAirline(getsFactory);
											break;
										case 2:
											airlineservice.updateAirline(getsFactory);
											break;
										case 3:
											airlineservice.deleteAirline(getsFactory);
											break;
										case 4:
											airlineservice.getAllAirlines(getsFactory);
											break;
										case 5:
											airlineservice.getAirline(getsFactory);
											break;
										case 6:
											System.out.println("Returning to Main Menu");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (airlineOption == 6)
											break; // Exit to main menu if selected
									}
									break;

								case 3: // Class options
									while (true) {
										System.out.println(
												"Class Options:\n1. Insert Class\n2. Update Class\n3. Delete Class\n4. Get All Classes\n5. Get Class\n6. Return to Main Menu");
										int classOption = sc.nextInt();
										switch (classOption) {
										case 1:
											classservice.insertClass(getsFactory);
											break;
										case 2:
											classservice.updateClass(getsFactory);
											break;
										case 3:
											classservice.deleteClass(getsFactory);
											break;
										case 4:
											classservice.getAllClasses(getsFactory);
											break;
										case 5:
											classservice.getClass(getsFactory);
											break;
										case 6:
											System.out.println("Returning to Main Menu");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (classOption == 6)
											break; // Exit to main menu if selected
									}
									break;

								case 4: // Payment options
									while (true) {
										System.out.println(
												"Payment Options:\n1. Insert Payment\n2. Update Payment\n3. Delete Payment\n4. Get All Payments\n5. Get Payment\n6. Return to Main Menu");
										int paymentOption = sc.nextInt();
										switch (paymentOption) {
										case 1:
											paymentservice.insertPayment(getsFactory);
											break;
										case 2:
											paymentservice.updatePayment(getsFactory);
											break;
										case 3:
											paymentservice.deletePayment(getsFactory);
											break;
										case 4:
											paymentservice.getAllPayments(getsFactory);
											break;
										case 5:
											paymentservice.getPayment(getsFactory);
											break;
										case 6:
											System.out.println("Returning to Main Menu");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (paymentOption == 6)
											break; // Exit to main menu if selected
									}
									break;

								case 5: // Ticket options
									while (true) {
										System.out.println(
												"Ticket Options:\n1. Insert Ticket\n2. Update Ticket\n3. Delete Ticket\n4. Get All Tickets\n5. Get Ticket\n6. Return to Main Menu");
										int ticketOption = sc.nextInt();
										switch (ticketOption) {
										case 1:
											ticketservice.insertTicket(getsFactory);
											break;
										case 2:
											ticketservice.updateTicket(getsFactory);
											break;
										case 3:
											ticketservice.deleteTicket(getsFactory);
											break;
										case 4:
											ticketservice.getAllTickets(getsFactory);
											break;
										case 5:
											ticketservice.getTicket(getsFactory);
											break;
										case 6:
											System.out.println("Returning to Main Menu");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (ticketOption == 6)
											break; // Exit to main menu if selected
									}
									break;

								case 6: // User options
									while (true) {
										System.out.println(
												"User Options:\n1. Insert User\n2. Update User\n3. Delete User\n4. Get All Users\n5. Get User\n6. Return to Main Menu");
										int userOption = sc.nextInt();
										switch (userOption) {
										case 1:
											userservice.insertUser(getsFactory);
											break;
										case 2:
											userservice.updateUser(getsFactory);
											break;
										case 3:
											userservice.deleteUser(getsFactory);
											break;
										case 4:
											userservice.getAllUsers(getsFactory);
											break;
										case 5:
											userservice.getUser(getsFactory);
											break;
										case 6:
											System.out.println("Returning to Main Menu");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (userOption == 6)
											break; // Exit to main menu if selected
									}
									break;
								case 7: // Admin options
									while (true) {
										System.out.println(
												"Admin Options:\n1. Add Admin\n2. Update Admin\n3. Remove Admin\n4. Get All Admins\n5. Get Admin\n6. Return to Main Menu");
										int adminOption = sc.nextInt();
										switch (adminOption) {
										case 1:
											adminservice.insertAdmin(getsFactory);
											break;
										case 2:
											adminservice.updateAdmin(getsFactory);
											break;
										case 3:
											adminservice.deleteAdmin(getsFactory);
											break;
										case 4:
											adminservice.getAllAdmins(getsFactory);
											break;
										case 5:
											adminservice.getAdmin(getsFactory);
											break;
										case 6:
											System.out.println("Returning to Main Menu");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (adminOption == 6)
											break; // Exit to main menu if selected
									}
									break;

								case 8:
									System.out.println("Exiting from admin access");

									break;

								default:
									System.out.println("Invalid category. Please try again.");
									break;
								}
								if (mainOption == 8)
									break;
							}

						} else {
							System.out.println("Invalid Admin ID. Please try again.");
						}
					}
					break;

				case 2:
					System.out.println("Enter User Id:");
					int userId = sc.nextInt();
					try (Session session = getsFactory.openSession()) {
						Users user = session.get(Users.class, userId);
						if (user != null) {
							System.out.println("User logged in successfully.");

							while (true) {
								System.out.println(
										"How can we help you:\n1. Flight\n2. Airline\n3. Class\n4. Payment\n5. Ticket\n6. User\n7. Exit");
								int mainOption = sc.nextInt();

								switch (mainOption) {
								case 1: // Flight options
									while (true) {
										System.out.println(
												"Flight Options:\n1. Get All Flights\n2. Get Flight\n3. Exit from flights Menu");
										int flightOption = sc.nextInt();
										switch (flightOption) {

										case 1:
											flightservice.getAllFlights(getsFactory);
											break;
										case 2:
											flightservice.getFlight(getsFactory);
											break;
										case 3:
											System.out.println("Exiting from flight services");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (flightOption == 3)
											break; // Exit to main menu if selected
									}
									break;

								case 2: // Airline options
									while (true) {
										System.out.println(
												"Airline Options:\n1. Get All Airlines\n2. Get Airline\n3. Exit from Airlines Menu");
										int airlineOption = sc.nextInt();
										switch (airlineOption) {

										case 1:
											airlineservice.getAllAirlines(getsFactory);
											break;
										case 2:
											airlineservice.getAirline(getsFactory);
											break;
										case 3:
											System.out.println("Exiting from Airline services");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (airlineOption == 3)
											break; // Exit to main menu if selected
									}
									break;

								case 3: // Class options
									while (true) {
										System.out.println(
												"Class Options:\n1. Get All Classes\n2. Get Class\n3. Exit from Class Menu");
										int classOption = sc.nextInt();
										switch (classOption) {

										case 1:
											classservice.getAllClasses(getsFactory);
											break;
										case 2:
											classservice.getClass(getsFactory);
											break;
										case 3:
											System.out.println("Exiting from Class services");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (classOption == 3)
											break; // Exit to main menu if selected
									}
									break;

								case 4: // Payment options
									while (true) {
										System.out.println(
												"Payment Options:\n1. Pay Payment\n2. Cancel Payment\n3. Get Payment\n4. Exit from payments Menu");
										int paymentOption = sc.nextInt();
										switch (paymentOption) {
										case 1:
											paymentservice.insertPayment(getsFactory);
											break;

										case 2:
											paymentservice.deletePayment(getsFactory);
											break;

										case 3:
											paymentservice.getPayment(getsFactory);
											break;
										case 4:
											System.out.println("Exiting from payment services");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (paymentOption == 4)
											break; // Exit to main menu if selected
									}
									break;

								case 5: // Ticket options
									while (true) {
										System.out.println(
												"Ticket Options:\n1. Book Ticket\n2. Cancel Ticket\n3. Get Ticket\n4. Return to Main Menu");
										int ticketOption = sc.nextInt();
										switch (ticketOption) {
										case 1:
											ticketservice.insertTicket(getsFactory);
											break;

										case 2:
											ticketservice.deleteTicket(getsFactory);
											break;

										case 3:
											ticketservice.getTicket(getsFactory);
											break;
										case 4:
											System.out.println("Exiting from Ticket services");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (ticketOption == 4)
											break; // Exit to main menu if selected
									}
									break;

								case 6: // User options
									while (true) {
										System.out.println(
												"User Options:\n1. Create User\n2. Update User\n3. Delete User\n4. Get User\n5. Exit from User Menu");
										int userOption = sc.nextInt();
										switch (userOption) {
										case 1:
											userservice.insertUser(getsFactory);
											break;
										case 2:
											userservice.updateUser(getsFactory);
											break;
										case 3:
											userservice.deleteUser(getsFactory);
											break;

										case 4:
											userservice.getUser(getsFactory);
											break;
										case 5:
											System.out.println("Exiting from User services");
											break;
										default:
											System.out.println("Invalid option. Please try again.");
											break;
										}
										if (userOption == 5)
											break; // Exit to main menu if selected
									}
									break;

								case 7:
									System.out.println("Exiting from user access");

									break;

								default:
									System.out.println("Invalid category. Please try again.");
									break;
								}
								if (mainOption == 7)
									break;
							}

						} else {
							System.out.println("Invalid User ID. Please try again.");
						}
					}
					break;
				case 3:
					System.out.println("Thank you for using Airline Reservation App.Bye Bye! ");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option. Please try again.");
					break;

				}
			}
		} finally {
			// Ensure resources are closed
			if (getsFactory != null) {
				getsFactory.close();

			}

			openSession.close();
			sc.close();
		}

		/*
		 * try { while (true) { System.out.println(
		 * "How can we help you:\n1. Flight\n2. Airline\n3. Class\n4. Payment\n5. Ticket\n6. User\n7. Admin\n8. Exit"
		 * ); int mainOption = sc.nextInt();
		 * 
		 * switch (mainOption) { case 1: // Flight options while (true) {
		 * System.out.println(
		 * "Flight Options:\n1. Insert Flight\n2. Update Flight\n3. Delete Flight\n4. Get All Flights\n5. Get Flight\n6. Return to Main Menu"
		 * ); int flightOption = sc.nextInt(); switch (flightOption) { case 1:
		 * flightservice.insertFlight(getsFactory); break; case 2:
		 * flightservice.updateFlight(getsFactory); break; case 3:
		 * flightservice.deleteFlight(getsFactory); break; case 4:
		 * flightservice.getAllFlights(getsFactory); break; case 5:
		 * flightservice.getFlight(getsFactory); break; case 6:
		 * System.out.println("Returning to Main Menu"); break; default:
		 * System.out.println("Invalid option. Please try again."); break; } if
		 * (flightOption == 6) break; // Exit to main menu if selected } break;
		 * 
		 * case 2: // Airline options while (true) { System.out.println(
		 * "Airline Options:\n1. Insert Airline\n2. Update Airline\n3. Delete Airline\n4. Get All Airlines\n5. Get Airline\n6. Return to Main Menu"
		 * ); int airlineOption = sc.nextInt(); switch (airlineOption) { case 1:
		 * airlineservice.insertAirline(getsFactory); break; case 2:
		 * airlineservice.updateAirline(getsFactory); break; case 3:
		 * airlineservice.deleteAirline(getsFactory); break; case 4:
		 * airlineservice.getAllAirlines(getsFactory); break; case 5:
		 * airlineservice.getAirline(getsFactory); break; case 6:
		 * System.out.println("Returning to Main Menu"); break; default:
		 * System.out.println("Invalid option. Please try again."); break; } if
		 * (airlineOption == 6) break; // Exit to main menu if selected } break;
		 * 
		 * case 3: // Class options while (true) { System.out.println(
		 * "Class Options:\n1. Insert Class\n2. Update Class\n3. Delete Class\n4. Get All Classes\n5. Get Class\n6. Return to Main Menu"
		 * ); int classOption = sc.nextInt(); switch (classOption) { case 1:
		 * classservice.insertClass(getsFactory); break; case 2:
		 * classservice.updateClass(getsFactory); break; case 3:
		 * classservice.deleteClass(getsFactory); break; case 4:
		 * classservice.getAllClasses(getsFactory); break; case 5:
		 * classservice.getClass(getsFactory); break; case 6:
		 * System.out.println("Returning to Main Menu"); break; default:
		 * System.out.println("Invalid option. Please try again."); break; } if
		 * (classOption == 6) break; // Exit to main menu if selected } break;
		 * 
		 * case 4: // Payment options while (true) { System.out.println(
		 * "Payment Options:\n1. Insert Payment\n2. Update Payment\n3. Delete Payment\n4. Get All Payments\n5. Get Payment\n6. Return to Main Menu"
		 * ); int paymentOption = sc.nextInt(); switch (paymentOption) { case 1:
		 * paymentservice.insertPayment(getsFactory); break; case 2:
		 * paymentservice.updatePayment(getsFactory); break; case 3:
		 * paymentservice.deletePayment(getsFactory); break; case 4:
		 * paymentservice.getAllPayments(getsFactory); break; case 5:
		 * paymentservice.getPayment(getsFactory); break; case 6:
		 * System.out.println("Returning to Main Menu"); break; default:
		 * System.out.println("Invalid option. Please try again."); break; } if
		 * (paymentOption == 6) break; // Exit to main menu if selected } break;
		 * 
		 * case 5: // Ticket options while (true) { System.out.println(
		 * "Ticket Options:\n1. Insert Ticket\n2. Update Ticket\n3. Delete Ticket\n4. Get All Tickets\n5. Get Ticket\n6. Return to Main Menu"
		 * ); int ticketOption = sc.nextInt(); switch (ticketOption) { case 1:
		 * ticketservice.insertTicket(getsFactory); break; case 2:
		 * ticketservice.updateTicket(getsFactory); break; case 3:
		 * ticketservice.deleteTicket(getsFactory); break; case 4:
		 * ticketservice.getAllTickets(getsFactory); break; case 5:
		 * ticketservice.getTicket(getsFactory); break; case 6:
		 * System.out.println("Returning to Main Menu"); break; default:
		 * System.out.println("Invalid option. Please try again."); break; } if
		 * (ticketOption == 6) break; // Exit to main menu if selected } break;
		 * 
		 * case 6: // User options while (true) { System.out.println(
		 * "User Options:\n1. Insert User\n2. Update User\n3. Delete User\n4. Get All Users\n5. Get User\n6. Return to Main Menu"
		 * ); int userOption = sc.nextInt(); switch (userOption) { case 1:
		 * userservice.insertUser(getsFactory); break; case 2:
		 * userservice.updateUser(getsFactory); break; case 3:
		 * userservice.deleteUser(getsFactory); break; case 4:
		 * userservice.getAllUsers(getsFactory); break; case 5:
		 * userservice.getUser(getsFactory); break; case 6:
		 * System.out.println("Returning to Main Menu"); break; default:
		 * System.out.println("Invalid option. Please try again."); break; } if
		 * (userOption == 6) break; // Exit to main menu if selected } break; case 7: //
		 * Admin options while (true) { System.out.println(
		 * "Admin Options:\n1. Add Admin\n2. Update Admin\n3. Remove Admin\n4. Get All Admins\n5. Get Admin\n6. Return to Main Menu"
		 * ); int adminOption = sc.nextInt(); switch (adminOption) { case 1:
		 * adminservice.insertAdmin(getsFactory); break; case 2:
		 * adminservice.updateAdmin(getsFactory); break; case 3:
		 * adminservice.deleteAdmin(getsFactory); break; case 4:
		 * adminservice.getAllAdmins(getsFactory); break; case 5:
		 * adminservice.getAdmin(getsFactory); break; case 6:
		 * System.out.println("Returning to Main Menu"); break; default:
		 * System.out.println("Invalid option. Please try again."); break; } if
		 * (adminOption == 6) break; // Exit to main menu if selected } break;
		 * 
		 * case 8: System.out.println("Exiting"); System.exit(0); break;
		 * 
		 * default: System.out.println("Invalid category. Please try again."); break; }
		 * } } finally { // Ensure resources are closed if (getsFactory != null) {
		 * getsFactory.close(); } sc.close(); }
		 */
	}
}