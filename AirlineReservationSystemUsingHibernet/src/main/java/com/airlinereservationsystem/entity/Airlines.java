package com.airlinereservationsystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Airlines {
	@Id
	private Integer airlineId;
	private String airlineName;
	private Long contactNo;
	
	@OneToMany(mappedBy="airline",cascade=CascadeType.ALL)
	List<Flights> flight=new ArrayList<Flights>() ;
	
	public Airlines() {
		
	}
	

	public List<Flights> getFlight() {
		return flight;
	}


	public void setFlight(List<Flights> flight) {
		this.flight = flight;
	}


	public Integer getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Integer airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}


	@Override
	public String toString() {
		return "Airlines [airlineId=" + airlineId + ", airlineName=" + airlineName + ", contactNo=" + contactNo + "]";
	}
	
	

}
