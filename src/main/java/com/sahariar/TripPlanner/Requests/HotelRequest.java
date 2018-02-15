package com.sahariar.TripPlanner.Requests;

import javax.validation.constraints.NotNull;

public class HotelRequest {

	@NotNull
	private String name;
	@NotNull
	private String address;
	@NotNull
	private String description;
	@NotNull
	private String contact;
	@NotNull
	private String email;
	@NotNull
	private long location_id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	@Override
	public String toString() {
		return "HotelRequest [name=" + name + ", address=" + address + ", description=" + description + ", contact="
				+ contact + ", email=" + email + ", location_id=" + location_id + "]";
	}
	
	
}
