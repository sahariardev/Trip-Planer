package com.sahariar.TripPlanner.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFound extends RuntimeException {

	public HotelNotFound(String message)
	{
		super(message);
	}
}
