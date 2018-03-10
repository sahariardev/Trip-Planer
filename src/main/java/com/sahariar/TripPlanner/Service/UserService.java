package com.sahariar.TripPlanner.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Model.User;
import com.sahariar.TripPlanner.Repositories.UserRepository;
import com.sahariar.TripPlanner.Requests.UserRequest;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	public User findUserByEmail(String s)
	{
		return ur.findByEmail(s);
	}
	public User saveUser(UserRequest request)
	{
		User u=new User();
		
		u.setName(request.getName());
		u.setContact(request.getContact());
		u.setEmail(request.getEmail());
		u.setRole(request.getRole());
		u.setPassword(request.getPassword());
		u=ur.save(u);
		
		
		return u;
	}
	
	public List<User> getAll()
	{
		return ur.findAll();
	}
	public User getOne(long id)
	{
		User u=ur.getOne(id);
		try
		{
			String s=u.getName();
		}
		catch(Exception e)
		{
			u=null;
		}
		return u;
	}
	
}
