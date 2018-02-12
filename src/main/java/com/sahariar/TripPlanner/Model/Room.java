package com.sahariar.TripPlanner.Model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room {

	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private long id;
	private int fee;
	private String details;
	
	//Relations
	@ManyToOne
	private Hotel hotel;
	
	@OneToMany(mappedBy="room")
	private List<Bookings> bookings;
	
	@ManyToMany
	@JoinTable(name="room_category",joinColumns=@JoinColumn(name="room_id"),
	inverseJoinColumns
	=@JoinColumn(name="category_id"))
	private Set<Category> categories;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(List<Bookings> bookings) {
		this.bookings = bookings;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	
}