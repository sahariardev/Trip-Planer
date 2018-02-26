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
	public List<Room> getAll()
	{
		return rs.findAll();
	}
	
	
	@GetMapping("room/{id}")
	public Room getRoom(@PathVariable long id)
	{
		Room r=rs.getOne(id);
		if(r==null)
		{
			throw new NotFound("Room not found "+id);
		}
		
		return r;
		
	}
	
	
}
