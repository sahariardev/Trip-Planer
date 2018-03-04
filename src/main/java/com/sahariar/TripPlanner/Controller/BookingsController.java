package com.sahariar.TripPlanner.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
	public MappingJacksonValue showall()
	{
		List<Bookings> bookings= bs.findaAll();
		FilterProvider filters=filters(false);
		
		MappingJacksonValue mapping=new MappingJacksonValue(bookings);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("booking/{id}")
	public MappingJacksonValue getOne(@PathVariable long id)
	{
		Bookings bookings=bs.getOne(id);
		
		if(bookings==null)
		{
			throw new NotFound("Booking not found with id "+id);
		}
		FilterProvider filters=filters(true);
		MappingJacksonValue mapping=new MappingJacksonValue(bookings);
		mapping.setFilters(filters);
		return mapping;
	}
	
	public SimpleFilterProvider filters(boolean isSingleItem)
	{
		
		SimpleBeanPropertyFilter bookingsFilter;
		if(isSingleItem)
		{
			bookingsFilter=SimpleBeanPropertyFilter.serializeAllExcept("hibernateLazyInitializer","handler");
		}
		else
		{
			bookingsFilter=SimpleBeanPropertyFilter.serializeAll();	
		}
		
		SimpleBeanPropertyFilter roomFilter=SimpleBeanPropertyFilter.serializeAllExcept("hotel","bookings","categories");
		SimpleBeanPropertyFilter userFilter=SimpleBeanPropertyFilter.serializeAllExcept("bookings");
	
		return new SimpleFilterProvider().addFilter("BookingsFilter", bookingsFilter).addFilter("roomFilter", roomFilter).addFilter("userFilter",userFilter).addFilter("userFilter", userFilter);
		
	}
}
