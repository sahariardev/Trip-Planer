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
import com.sahariar.TripPlanner.Exceptions.UserNotFound;
import com.sahariar.TripPlanner.Model.User;
import com.sahariar.TripPlanner.Requests.UserRequest;
import com.sahariar.TripPlanner.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService us;

	@PostMapping("user")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserRequest request)
	{
		User u=us.saveUser(request);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("users")
	public MappingJacksonValue getAlluser()
	{
		List<User> users= us.getAll();
		FilterProvider filters=filters(false);
		MappingJacksonValue mapping= new MappingJacksonValue(users);
		mapping.setFilters(filters);
		return mapping;
	}
	@GetMapping("user/{id}")
	public MappingJacksonValue getuser(@PathVariable long id)
	{
		User user=us.getOne(id);
		if(user==null)
		{
			throw new UserNotFound("User not found with id "+id);
		}
		FilterProvider filters=filters(true);
		MappingJacksonValue mapping=new MappingJacksonValue(user);
		mapping.setFilters(filters);
		
		return mapping;
		
	}
	
	public SimpleFilterProvider filters(boolean isSingleItem)
	{
		
		SimpleBeanPropertyFilter userFilter;
		if(isSingleItem)
		{
			userFilter =SimpleBeanPropertyFilter.serializeAllExcept("handler","hibernateLazyInitializer");
			
		}
		else
		{
		   userFilter=SimpleBeanPropertyFilter.serializeAll();	
		}
		
		
		SimpleBeanPropertyFilter bookingFilter=SimpleBeanPropertyFilter.serializeAllExcept("user","room");
		
		return new SimpleFilterProvider().addFilter("userFilter", userFilter).addFilter("BookingsFilter", bookingFilter);
	}
	
}
