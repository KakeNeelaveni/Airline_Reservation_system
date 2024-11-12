package com.airlinereservationsystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Classes {
	@Id
	private Integer classId;
	private String classType;
	private Float fare;
	
	@OneToMany(mappedBy="flightClass",cascade=CascadeType.ALL)
	List<Flights> flight=new ArrayList<Flights>() ;
	
	@OneToMany(mappedBy="ticketClass",cascade=CascadeType.ALL)
	List<Tickets> ticket=new ArrayList<Tickets>() ;
	
	public Classes() {
		
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Float getFare() {
		return fare;
	}

	public void setFare(Float fare) {
		this.fare = fare;
	}

	public List<Flights> getFlight() {
		return flight;
	}

	public void setFlight(List<Flights> flight) {
		this.flight = flight;
	}

	public List<Tickets> getTicket() {
		return ticket;
	}

	public void setTicket(List<Tickets> ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Classes [classId=" + classId + ", classType=" + classType + ", fare=" + fare + "]";
	}
	
	

}
