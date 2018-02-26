package com.sahariar.TripPlanner.Controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sahariar.TripPlanner.Model.Bookings;
import com.sahariar.TripPlanner.Requests.BookingRequest;
import com.sahariar.TripPlanner.Service.BookingService;

@RestController
public class BookingsController {

	@Autowired
	BookingService bs;
	
	
	@PostMapping("book")
	public ResponseEntity<Object> book(@Valid @RequestBody BookingRequest request)
	{
		Bookings b=bs.book(request);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(b.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
