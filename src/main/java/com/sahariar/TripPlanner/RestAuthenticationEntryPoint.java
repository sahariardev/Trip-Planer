package com.sahariar.TripPlanner;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component( "restAuthenticationEntryPoint" )
public class RestAuthenticationEntryPoint
  implements AuthenticationEntryPoint{
 
   @Override
   public void commence(
     HttpServletRequest request,
     HttpServletResponse response, 
     AuthenticationException authException) throws IOException {
     
	 //  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode("rifat1234");
		//System.out.println(hashedPassword);
	   
	    
      response.sendError( HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage() );
   }
}