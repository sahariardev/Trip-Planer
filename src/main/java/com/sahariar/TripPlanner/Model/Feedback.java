package com.sahariar.TripPlanner.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int points;
	private String feedback;
	
}
