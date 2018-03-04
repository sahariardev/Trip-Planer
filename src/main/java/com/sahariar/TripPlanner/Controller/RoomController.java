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
import com.sahariar.TripPlanner.Model.Hotel;
import com.sahariar.TripPlanner.Model.Room;
import com.sahariar.TripPlanner.Requests.RoomRequest;
import com.sahariar.TripPlanner.Service.RoomService;

@RestController
public class RoomController {

	@Autowired
	RoomService rs;
	
	//add room
	@PostMapping("room")
	public ResponseEntity<Object> addRoom(@Valid @RequestBody RoomRequest request)
	{
		Room r=rs.save(request);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(r.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("rooms")
	public MappingJacksonValue getAll()
	{
		
		List<Room> rooms=rs.findAll();
		for(Room room:rooms)
		{
			Hotel h=room.getHotel();
			System.out.println(h.getName());
		}
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("name","address","description","contact","email");
		SimpleBeanPropertyFilter filter2=SimpleBeanPropertyFilter.filterOutAllExcept("name","contact","email"); 
		SimpleBeanPropertyFilter roomFilter=SimpleBeanPropertyFilter.serializeAllExcept("bookings","categories");
		
		FilterProvider filters= new SimpleFilterProvider().addFilter("hotelFilter", filter).addFilter("userFilter", filter2).addFilter("roomFilter", roomFilter);

			
			MappingJacksonValue mapping=new MappingJacksonValue(rooms);
	
			mapping.setFilters(filters);
			
		 
		
		return mapping;
	}
	
	
	@GetMapping("room/{id}")
	public MappingJacksonValue getRoom(@PathVariable long id)
	{
		Room r=rs.getOne(id);
		
		if(r==null)
		{
			throw new NotFound("Room not found "+id);
		}
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("name","address","description","contact","email");
		SimpleBeanPropertyFilter filter2=SimpleBeanPropertyFilter.filterOutAllExcept("name","contact","email"); 
		SimpleBeanPropertyFilter roomFilter=SimpleBeanPropertyFilter.serializeAllExcept("bookings","hibernateLazyInitializer","handler");
		SimpleBeanPropertyFilter categoryFilter=SimpleBeanPropertyFilter.serializeAllExcept("rooms");
		
		
		FilterProvider filters= new SimpleFilterProvider().addFilter("hotelFilter", filter).addFilter("userFilter", filter2)
				.addFilter("roomFilter", roomFilter).addFilter("categoryFilter", categoryFilter);
               
			
			MappingJacksonValue mapping=new MappingJacksonValue(r);
	
			mapping.setFilters(filters);
			
		 
		
		return mapping;

		
	}
	
	
}
