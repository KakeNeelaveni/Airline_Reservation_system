package com.airlinereservationsystem.entity;

import java.time.LocalDateTime;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class Tickets {
	@Id
	private Integer ticketId;
	private String pnrNo;
	private LocalDateTime dateTime;
	
	
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;
	
	@ManyToOne
    @JoinColumn(name = "flightId")
    private Flights flight;
	
    @ManyToOne
	@JoinColumn(name = "classId")
	private Classes ticketClass;
	 
	 
	 @ManyToOne
	 @JoinColumn(name = "transactionId")
	 private Payments payment; 
	 
	
	public Tickets() {
		
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}


	public Flights getFlight() {
		return flight;
	}



	public void setFlight(Flights flight) {
		this.flight = flight;
	}



	public Classes getTicketClass() {
		return ticketClass;
	}



	public void setTicketClass(Classes ticketClass) {
		this.ticketClass = ticketClass;
	}



	public Payments getPayment() {
		return payment;
	}



	public void setPayment(Payments payment) {
		this.payment = payment;
	}



	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}



	@Override
	public String toString() {
		return "Tickets [ticketId=" + ticketId + ", pnrNo=" + pnrNo + ", dateTime=" + dateTime + "]";
	}
	
	
}
