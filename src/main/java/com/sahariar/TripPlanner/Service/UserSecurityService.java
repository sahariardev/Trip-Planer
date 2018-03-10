package com.sahariar.TripPlanner.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Model.User;
import com.sahariar.TripPlanner.Security.SecurityAuthenticationProvider;


@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	UserService us;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=us.findUserByEmail(email);
		
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found with email "+email);
		}
		SecurityAuthenticationProvider s=new SecurityAuthenticationProvider(user.getEmail(),user.getPassword());
	
		
		return s;
	}

}
