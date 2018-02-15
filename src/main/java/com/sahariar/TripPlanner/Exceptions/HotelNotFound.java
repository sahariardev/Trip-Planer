package com.sahariar.TripPlanner.Exceptions;

public class HotelNotFound extends RuntimeException {

	public HotelNotFound(String message)
	{
		super(message);
	}
}
