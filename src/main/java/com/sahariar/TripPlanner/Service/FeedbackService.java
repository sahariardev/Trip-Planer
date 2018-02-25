package com.sahariar.TripPlanner.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Exceptions.BadRequest;
import com.sahariar.TripPlanner.Model.Feedback;
import com.sahariar.TripPlanner.Model.Hotel;
import com.sahariar.TripPlanner.Repositories.FeedbackRepository;
import com.sahariar.TripPlanner.Requests.FeedbackRequest;

@Service
public class FeedbackService {

	@Autowired
	FeedbackRepository fr;
	
	@Autowired 
	HotelService hs;
	
	public Feedback save(FeedbackRequest feedback)
	{
		Feedback f=new Feedback();
		f.setFeedback(feedback.getFeedback());
		f.setPoints(feedback.getPoints());
		Hotel h=hs.getOne(feedback.getHotel_id());
		
		if(h==null)
		{
			throw new BadRequest("Hotel not found");
		}
		f.setHotel(h);
		
		f=fr.save(f);
		
		return f;
	} 
	
	public List<Feedback> findAll()
	{
		return fr.findAll();
	}
	
	public Feedback getOne(long id)
	{
		Feedback f=fr.getOne(id);
		
		try{
			String s=f.getFeedback();
		}
		catch(Exception e)
		{
			f=null;
		}
		return f;
	}
	
	
}
