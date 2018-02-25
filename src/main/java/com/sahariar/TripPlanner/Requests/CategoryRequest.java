package com.sahariar.TripPlanner.Requests;

import javax.validation.constraints.NotNull;

public class CategoryRequest {
	
	@NotNull
	public String name;
	@NotNull
    public String description;
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
	
	
	
}
