package com.sahariar.TripPlanner.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Exceptions.BadRequest;
import com.sahariar.TripPlanner.Model.Bookings;
import com.sahariar.TripPlanner.Model.Room;
import com.sahariar.TripPlanner.Model.User;
import com.sahariar.TripPlanner.Repositories.BookingRepository;
import com.sahariar.TripPlanner.Requests.BookingRequest;

@Service
public class BookingService {

	
	@Autowired
	BookingRepository br;
	@Autowired
	RoomService rs;
	
	@Autowired
	UserService us;
	
	
	public Bookings book(BookingRequest request)
	{
		
		//object b is for bookings
		// object u is for user
		// object r is for room
		
		User u=us.getOne(request.getUser_id());
		if(u==null)
		{
			throw new BadRequest("User not valid with id "+request.getUser_id());
		}
		
		Room r=rs.getOne(request.getRoom_id());
		if(r==null)
		{
			throw new BadRequest("Room is not valid with id "+request.getRoom_id());
			
		}
		
		Bookings b=new Bookings();
		b.setUser(u);
		b.setRoom(r);
		b.setEnddate(request.getEnd_date());
		b.setStartdate(request.getStart_date());
		
		b=br.save(b);
		return b;
	}
	
	
}
