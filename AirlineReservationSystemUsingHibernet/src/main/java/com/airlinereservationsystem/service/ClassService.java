package com.airlinereservationsystem.service;

import org.hibernate.SessionFactory;

public interface ClassService {
	
	void insertClass(SessionFactory sf);
    void updateClass(SessionFactory sf);
    void deleteClass(SessionFactory sf);
    void getAllClasses(SessionFactory sf);
    void getClass(SessionFactory sf);
    

}
