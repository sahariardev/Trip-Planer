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

import com.sahariar.TripPlanner.Model.User;
import com.sahariar.TripPlanner.Requests.UserRequest;
import com.sahariar.TripPlanner.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService us;

	@PostMapping("user")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserRequest request)
	{
		User u=us.saveUser(request);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("users")
	public List<User> getAlluser()
	{
		return us.getAll();
	}
	@GetMapping("user/{id}")
	public User getuser(@PathVariable long id)
	{
		return us.getOne(id);
	}
	
}
