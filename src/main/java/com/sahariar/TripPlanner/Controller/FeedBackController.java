package com.sahariar.TripPlanner.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
	public MappingJacksonValue getAll()
	{
		
		
		List<Feedback> feedbacks= fs.findAll();
		
		
		SimpleBeanPropertyFilter filter1=SimpleBeanPropertyFilter.serializeAll();
		SimpleBeanPropertyFilter hotelFilter=SimpleBeanPropertyFilter.serializeAllExcept("feedbacks","rooms","location");
		FilterProvider filters=new SimpleFilterProvider().addFilter("FeedbackFilter", filter1).addFilter("hotelFilter", hotelFilter);
		
		MappingJacksonValue mapping=new MappingJacksonValue(feedbacks);
		mapping.setFilters(filters);
		
		return mapping;
		
		
		
		
	}
	
	@GetMapping("feedback/{id}")
	public MappingJacksonValue getOne(@PathVariable long id)
	{
		Feedback feedback=fs.getOne(id);
		
		if(feedback==null)
		{
			throw new NotFound("Feedback not found with id "+id);
			
			
		}
		
		SimpleBeanPropertyFilter filter1=SimpleBeanPropertyFilter.serializeAllExcept("hibernateLazyInitializer","handler");
		SimpleBeanPropertyFilter hotelFilter=SimpleBeanPropertyFilter.serializeAllExcept("feedbacks","rooms","location");
		FilterProvider filters=new SimpleFilterProvider().addFilter("FeedbackFilter", filter1).addFilter("hotelFilter", hotelFilter);
		MappingJacksonValue mapping=new MappingJacksonValue(feedback);
		
        mapping.setFilters(filters);
		
		return mapping;
		
	}
	
	
}
