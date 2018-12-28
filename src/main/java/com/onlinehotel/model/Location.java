package com.onlinehotel.model;

public class Location {
	private int locationId;
	private String locationName;


	public Location() {
		// TODO Auto-generated constructor stub
	}

	
	public Location(int locationId, String locationName) {
		this.locationId = locationId;
		this.locationName = locationName;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", locationName=" + locationName + "]";
	}
	
}
