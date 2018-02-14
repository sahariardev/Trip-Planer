package com.sahariar.TripPlanner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/hotel")
	public void addHotel(@RequestBody HotelRequest request)
	{
		hs.addHotelFromRequest(request);
	}
	@GetMapping("/hotel")
	public List<Hotel> getHotel()
	{
		return hs.getAll();
	}
	
}
