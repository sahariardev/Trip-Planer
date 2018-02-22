package com.sahariar.TripPlanner.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Model.Hotel;
import com.sahariar.TripPlanner.Model.Location;
import com.sahariar.TripPlanner.Repositories.HotelRepository;
import com.sahariar.TripPlanner.Repositories.LocationRepository;
import com.sahariar.TripPlanner.Requests.HotelRequest;

@Service
public class HotelService {

	@Autowired
	HotelRepository hr;
	
	@Autowired
	LocationRepository lr;
	
	public List<Hotel> getAll()
	{
	  	
		return hr.findAll();
	}
	public Hotel getOne(long id)
	{
		Hotel hotel= hr.getOne(id);
		
		try
		{
			String s=hotel.getName();
		}
		catch(Exception e)
		{
			hotel=null;
		}
		
		return hotel;
	}
	public Hotel addHotelFromRequest(HotelRequest request)
	{
		Hotel hotel=new Hotel();
		
	    hotel.setName(request.getName());
	    hotel.setAddress(request.getName());
	    hotel.setContact(request.getContact());
	    hotel.setDescription(request.getDescription());
	    hotel.setEmail(request.getEmail());
	    Location location=lr.getOne(request.getLocation_id());
	    hotel.setLocation(location);
        System.out.println(hotel.getName());
	    Hotel h=hr.save(hotel);
		return h;
		
	}
}
