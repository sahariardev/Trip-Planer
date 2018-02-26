package com.sahariar.TripPlanner.Requests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class BookingRequest {

	
	
	@NotNull
	private long user_id;
	@NotNull
	private long room_id;
	@NotNull
	private Date start_date;
	@NotNull
	private Date end_date;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getRoom_id() {
		return room_id;
	}

	public void setRoom_id(long room_id) {
		this.room_id = room_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
   
}
