package com.sahariar.TripPlanner.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Exceptions.BadRequest;
import com.sahariar.TripPlanner.Model.Category;
import com.sahariar.TripPlanner.Model.Hotel;
import com.sahariar.TripPlanner.Model.Room;
import com.sahariar.TripPlanner.Repositories.RoomRepository;
import com.sahariar.TripPlanner.Requests.RoomRequest;

@Service
public class RoomService {

	
	@Autowired
	RoomRepository rs;
	
	@Autowired
	HotelService hs;
	
	@Autowired
	CategoryService cs;
	
	public Room save(RoomRequest request)
	{
		Room r=new Room();
		r.setFee(request.getFee());
		
		
		Set<Category> c=new HashSet();
		
		List<Long> ids=request.getCategory_id();
		for(long id:ids)
		{
			Category category=cs.getOne(id);
			if(category==null)
			{
				throw new BadRequest("Category not exist");
			}
			c.add(category);
		}
		
		r.setCategories(c);
		
		r.setDetails(request.getDetails());
		Hotel h=hs.getOne(request.getHotel_id());
		
		if(h==null)
		{
			throw new BadRequest("Hotel does not exist");
		}
		
		
		r.setHotel(h);
        r=rs.save(r);
		
		return r;
	}
	
	
	public List<Room> findAll()
	{
		return rs.findAll();
	}
	public Room getOne(long id)
	{
		Room r=rs.getOne(id);
		
		try
		{
			String s=r.getDetails();
		}
		catch(Exception e)
		{
			r=null;
		}
		return r;
	}
}
