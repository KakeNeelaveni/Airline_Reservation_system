package com.airlinereservationsystem.service;

import org.hibernate.SessionFactory;

public interface UserService {
	
	void insertUser(SessionFactory sf);
    void updateUser(SessionFactory sf);
    void deleteUser(SessionFactory sf);
    void getAllUsers(SessionFactory sf);
    void getUser(SessionFactory sf);

}
