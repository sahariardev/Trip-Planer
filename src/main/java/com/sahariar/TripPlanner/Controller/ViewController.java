package com.sahariar.TripPlanner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.TripPlanner.Model.Location;
import com.sahariar.TripPlanner.Service.LocationService;


@RestController
public class ViewController {

	@Autowired
	LocationService ls;
	@GetMapping("/locations")
	public List<Location> locations()
	{
		return ls.getAll();
	}
}
