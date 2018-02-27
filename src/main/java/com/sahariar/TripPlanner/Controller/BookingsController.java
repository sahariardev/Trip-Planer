package com.sahariar.TripPlanner.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sahariar.TripPlanner.Exceptions.NotFound;
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
	
	@GetMapping("bookings")
	public List<Bookings> showall()
	{
		return bs.findaAll();
	}
	
	@GetMapping("booking/{id}")
	public Bookings getOne(@PathVariable long id)
	{
		Bookings bookings=bs.getOne(id);
		
		if(bookings==null)
		{
			throw new NotFound("Booking not found with id "+id);
		}
		
		return bookings;
	}
}
