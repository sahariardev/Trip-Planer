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

import com.sahariar.TripPlanner.Model.Hotel;
import com.sahariar.TripPlanner.Model.Location;
import com.sahariar.TripPlanner.Requests.HotelRequest;
import com.sahariar.TripPlanner.Service.HotelService;
import com.sahariar.TripPlanner.Service.LocationService;

@RestController
public class AddController {

	@Autowired
	LocationService ls;
	@Autowired
	HotelService hs;
	
	@PostMapping("/location")
	public void addLocation(@RequestBody Location location)
	{
		ls.add(location);
		
	}
	@GetMapping("/location/{id}")
	public Location getLocation(@PathVariable long id)
	{
		
		return ls.getOne(id);
	}
	@PostMapping("/hotel")
	public ResponseEntity<Object> addHotel(@Valid @RequestBody HotelRequest request)
	{
		Hotel h=hs.addHotelFromRequest(request);
		
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(h.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	@GetMapping("/hotel")
	public List<Hotel> getHotel()
	{
		return hs.getAll();
	}
	@GetMapping("/hotel/{id}")
	public Hotel gethotelOne(@PathVariable long id)
	{
		
		
		return hs.getOne(id);
	}
	
}
