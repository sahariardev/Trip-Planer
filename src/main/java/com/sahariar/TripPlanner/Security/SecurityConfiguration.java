package com.sahariar.TripPlanner.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sahariar.TripPlanner.RestAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("Select email,password,true from user where email = ?")
		.authoritiesByUsernameQuery("Select email, role from user where email = ?");
		
		
	}
   
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST).access("hasRole('ROLE_ADMIN')").anyRequest().permitAll()
		.and().csrf().disable()
		.httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint);
		
}
	
	
	
}
