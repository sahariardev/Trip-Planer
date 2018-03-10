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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sahariar.TripPlanner.Exceptions.CategoryNotFound;
import com.sahariar.TripPlanner.Model.Category;
import com.sahariar.TripPlanner.Requests.CategoryRequest;
import com.sahariar.TripPlanner.Service.CategoryService;

@RestController
@RequestMapping("/rest")
public class CategoryController {

	
	@Autowired
	CategoryService cs;
	
	@PostMapping("category")
	public ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryRequest request)
	{
	  Category c=cs.Save(request);
	  URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(c.getId()).toUri();
	  return ResponseEntity.created(location).build();
	  
	}
	@GetMapping("categories")
	public MappingJacksonValue showall()
	{
		List<Category> categories=cs.findAll();
		

		
		
		FilterProvider filters=filters(false);
		MappingJacksonValue mapping=new MappingJacksonValue(categories);
		mapping.setFilters(filters);
		
		
		
		return mapping;
		
	}
	
	@GetMapping("category/{id}")
	public MappingJacksonValue getOne(@PathVariable long id)
	{
	  Category category=cs.getOne(id);
	  
	  if(category==null)
	  {
		  throw new CategoryNotFound("Category not found with id "+id);
	  }
	  
	  FilterProvider filters=filters(true);
		MappingJacksonValue mapping=new MappingJacksonValue(category);
		mapping.setFilters(filters);
	  
	  return mapping;
	}
	
	
	public SimpleFilterProvider filters(boolean singleItem)
	{
		SimpleBeanPropertyFilter categoryFilter;
		if(singleItem)
		{
			categoryFilter=SimpleBeanPropertyFilter.serializeAllExcept("hibernateLazyInitializer","handler");
		}
		else
		{
			categoryFilter=SimpleBeanPropertyFilter.serializeAll();
		}
		
		SimpleBeanPropertyFilter roomsFilter=SimpleBeanPropertyFilter.serializeAllExcept("categories","bookings");
		SimpleBeanPropertyFilter hotelFilter=SimpleBeanPropertyFilter.serializeAllExcept("rooms","feedbacks","location");
		return new SimpleFilterProvider().addFilter("categoryFilter", categoryFilter).addFilter("roomFilter", roomsFilter).addFilter("hotelFilter", hotelFilter);
	}
}
