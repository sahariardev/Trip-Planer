package com.sahariar.TripPlanner.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sahariar.TripPlanner.Exceptions.NotFound;
import com.sahariar.TripPlanner.Model.Feedback;
import com.sahariar.TripPlanner.Requests.FeedbackRequest;
import com.sahariar.TripPlanner.Service.FeedbackService;

@RestController
public class FeedBackController {

	
	@Autowired
    FeedbackService fs;
	
	@PostMapping("feedback")
	public ResponseEntity<Object> addOne(@Valid @RequestBody FeedbackRequest request)
	{
		Feedback f=fs.save(request);
	    
		  URI location=ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(f.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("feedbacks")
	public List<Feedback> getAll()
	{
		return fs.findAll();
	}
	
	@GetMapping("feedback/{id}")
	public Feedback getOne(@PathVariable long id)
	{
		Feedback f=fs.getOne(id);
		
		if(f==null)
		{
			throw new NotFound("Feedback not found with id "+id);
			
			
		}
		
		return f;
	}
	
	
}
