package com.airlinereservationsystem.service;

import org.hibernate.SessionFactory;

public interface PaymentService {
	
	void insertPayment(SessionFactory sf);
    void updatePayment(SessionFactory sf);
    void deletePayment(SessionFactory sf);
    void getAllPayments(SessionFactory sf);
    void getPayment(SessionFactory sf);

}
