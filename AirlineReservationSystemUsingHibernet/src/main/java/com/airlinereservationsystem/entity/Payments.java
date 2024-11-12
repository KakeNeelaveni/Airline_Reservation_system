package com.airlinereservationsystem.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Payments {
	

	@Id
	private Integer paymentId;
	private String transactionId;
	private Float amount;
	private String paymentMode;
	
	@OneToMany(mappedBy="payment",cascade=CascadeType.ALL)
	List<Tickets> ticket=new ArrayList<Tickets>() ;
	
	
	public Payments(Integer paymentId, String transactionId, Float amount, String paymentMode) {
		super();
		this.paymentId = paymentId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.paymentMode = paymentMode;
	}

	public List<Tickets> getTicket() {
		return ticket;
	}

	public void setTicket(List<Tickets> ticket) {
		this.ticket = ticket;
	}

	public Payments() {
		
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() {
		return "Payments [paymentId=" + paymentId + ", transactionId=" + transactionId + ", amount=" + amount
				+ ", paymentMode=" + paymentMode + "]";
	}
	
	

}
