package com.airlinereservationsystem.service;

import org.hibernate.SessionFactory;

public interface AdminService {
	void insertAdmin(SessionFactory sf);
    void updateAdmin(SessionFactory sf);
    void deleteAdmin(SessionFactory sf);
    void getAllAdmins(SessionFactory sf);
    void getAdmin(SessionFactory sf);

}
