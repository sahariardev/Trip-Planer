package com.sahariar.TripPlanner.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityAuthenticationProvider implements UserDetails {

	
	private String password;
	private String Username;
	private List<roles> role;
	
	
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		Username = username;
	}
    public SecurityAuthenticationProvider(String username,String password)
    {
    	System.err.println("User name "+username);
    	this.password=password;
    	this.Username=username;
    	role.add(new roles());
    }
	
	@Override
	public Collection<roles> getAuthorities() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return Username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
class roles implements GrantedAuthority
{

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return "Admin";
	}
	}
