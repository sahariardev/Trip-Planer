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

import com.sahariar.TripPlanner.Exceptions.CategoryNotFound;
import com.sahariar.TripPlanner.Model.Category;
import com.sahariar.TripPlanner.Requests.CategoryRequest;
import com.sahariar.TripPlanner.Service.CategoryService;

@RestController
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
	public List<Category> showall()
	{
		List<Category> categories=cs.findAll();
		return categories;
		
	}
	
	@GetMapping("category/{id}")
	public Category getOne(@PathVariable long id)
	{
	  Category c=cs.getOne(id);
	  
	  if(c==null)
	  {
		  throw new CategoryNotFound("Category not found with id "+id);
	  }
	  return c;
	}
	
}
