package com.sahariar.TripPlanner.Requests;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class RoomRequest {
	
	
	
	@NotNull
	private int fee;
	
	@NotNull
	private String details;
	
    @NotNull
    private long hotel_id;
	
    @NotNull
	private List<Long> category_id;


	
	
	public long getHotel_id() {
		return hotel_id;
	}


	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
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


	public List<Long> getCategory_id() {
		return category_id;
	}


	public void setCategory_id(List<Long> category_id) {
		this.category_id = category_id;
	}
	 
	
 
}
