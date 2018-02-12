package com.sahariar.TripPlanner.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.TripPlanner.Model.Location;
import com.sahariar.TripPlanner.Service.LocationService;

@RestController
public class AddController {

	@Autowired
	LocationService ls;
	
	@PostMapping("/location")
	public void addLocation(@RequestBody Location location)
	{
		ls.add(location);
		
	}
}
