package com.airlinereservationsystem.exception;

public class FlightNotFoundException extends Exception {
	public FlightNotFoundException(String message) {
		super(message);
	}
}