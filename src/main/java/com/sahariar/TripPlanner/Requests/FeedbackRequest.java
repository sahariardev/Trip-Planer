package com.sahariar.TripPlanner.Requests;

import javax.validation.constraints.NotNull;

public class FeedbackRequest {

	@NotNull
	private long hotel_id;
	@NotNull
	private String feedback;
	@NotNull
	private int points;
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
	}
	
	
	
	
	
}
