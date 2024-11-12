package com.airlinereservationsystem.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Flights {
	@Id
	private Integer flightId;
	private String source;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private Integer seatAvailable;
	
	@ManyToOne
	@JoinColumn(name="airlineId")
	private Airlines airline;
	
	@ManyToOne
    @JoinColumn(name = "classId")
    private Classes flightClass;
	
	@OneToMany(mappedBy="flight",cascade=CascadeType.ALL)
	List<Tickets> ticket=new ArrayList<Tickets>() ;
	
	public Flights() {
		
	}
    
	public Airlines getAirline() {
		return airline;
	}

	public void setAirline(Airlines airline) {
		this.airline = airline;
	}

	public Classes getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(Classes flightClass) {
		this.flightClass = flightClass;
	}

	public List<Tickets> getTicket() {
		return ticket;
	}

	public void setTicket(List<Tickets> ticket) {
		this.ticket = ticket;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getSeatAvailable() {
		return seatAvailable;
	}

	public void setSeatAvailable(Integer seatAvailable) {
		this.seatAvailable = seatAvailable;
	}

	@Override
	public String toString() {
		return "Flights [flightId=" + flightId + ", source=" + source + ", destination=" + destination
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", seatAvailable="
				+ seatAvailable + "]";
	}

	
	
	
	

}
