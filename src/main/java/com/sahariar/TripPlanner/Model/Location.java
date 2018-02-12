package com.sahariar.TripPlanner.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@Entity
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	
	//Relations
	//@JsonBackReference(value="hotel-location")
	
	//@JsonIgnore
	@OneToMany(mappedBy="location")
	private List<Hotel> hotels;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	} 
	
}
