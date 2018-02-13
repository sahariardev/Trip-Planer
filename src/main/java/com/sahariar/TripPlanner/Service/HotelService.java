package com.sahariar.TripPlanner.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Model.Hotel;
import com.sahariar.TripPlanner.Repositories.HotelRepository;

@Service
public class HotelService {

	@Autowired
	HotelRepository hr;
	
	public List<Hotel> getAll()
	{
	  	
		return hr.findAll();
	}
}
