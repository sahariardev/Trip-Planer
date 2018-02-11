package com.sahariar.TripPlanner.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bookings {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Date startdate;
	private Date enddate;
	
	//relationships
}
