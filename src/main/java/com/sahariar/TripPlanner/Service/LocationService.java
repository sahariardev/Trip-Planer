package com.sahariar.TripPlanner.Service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Model.Location;
import com.sahariar.TripPlanner.Repositories.LocationRepository;

@Service
public class LocationService {

	@Autowired
	LocationRepository lr;
	
	public List<Location> getAll()
	{
		return lr.findAll();
	}
	public Location add(Location location)
	{
		Location l=lr.save(location);
		return l;
	}

	public Location getOne(long id)  {
		
		Location l;
		try 
		{
		  l=lr.getOne(id);
		 String s=l.getName();
		}
		catch(EntityNotFoundException e)
		{
			l=null;
		}
		
		
		
		return l;
		
		
		
	}
}
