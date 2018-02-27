package com.sahariar.TripPlanner.Service;

import java.util.Date;
import java.util.List;

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
		if(!(isRoomAvailable(b.getRoom().getId(),b.getStartdate(),b.getEnddate())))
		{
			throw new BadRequest("Room is already booked");
		}
		b=br.save(b);
		return b;
	}
	public List<Bookings> findaAll()
	{
		return br.findAll();
	}
	
	public Bookings getOne(long id)
	{
		Bookings bookings=br.getOne(id);
		
		try
		{
			bookings.getUser();
		}
		catch(Exception e)
		{
			bookings=null;
		}
		return bookings;
	}
	
	
	public boolean isRoomAvailable(long id,Date startdate,Date enddate)
	{
		
		List<Bookings> roomBookings=br.findByRoomId(id);
		
		for(Bookings book:roomBookings)
		{
			
			if( !((startdate.compareTo(book.getStartdate())<0 && startdate.compareTo(book.getEnddate())<0) || startdate.compareTo(book.getEnddate())>0))
			   {
				  return false;
				}
			
			
		}
		return true;
	}
	
}
