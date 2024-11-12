package com.airlinereservationsystem.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
	@Id
	private Integer userId;
	private String userName;
	private LocalDate dob;
	private Long phoneNumber;
	private String gender;
	private String address;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	List<Tickets> ticket=new ArrayList<Tickets>() ;
	
	
	
	public Users() {
		
	}

	public Users(Integer userId, String userName, LocalDate dob, Long phoneNumber, String gender, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.address = address;
	}
    
	public List<Tickets> getTicket() {
		return ticket;
	}

	public void setTicket(List<Tickets> ticket) {
		this.ticket = ticket;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", dob=" + dob + ", phoneNumber=" + phoneNumber
				+ ", gender=" + gender + ", address=" + address + "]";
	}
	
	
	
	

}
