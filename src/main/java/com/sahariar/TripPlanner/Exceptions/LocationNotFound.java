package com.sahariar.TripPlanner.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFound extends RuntimeException {

	public LocationNotFound(String message)
	{
		super(message);
	}
}
