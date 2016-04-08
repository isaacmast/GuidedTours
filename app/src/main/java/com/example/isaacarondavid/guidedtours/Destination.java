package com.example.isaacarondavid.guidedtours;

/**
 * Created by davidnester on 4/1/16.
 */
public class Destination {
	private int tourId;
    private int destinationId;
    private String name;
	private String description;
    private float latitude;
    private float longitude;

    public Destination(int tourId, int destinationId, String name, String description, float latitude, float longitude) {
        this.tourId = tourId;
        this.destinationId = destinationId;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
	}

	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

    public int getDestinationId() {
        return this.destinationId;
    }

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
    
    public String getName(){
        return this.name;
    }

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public float getLatitude() {
        return this.latitude;
    }

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

    public float getLongitude() {
        return this.longitude;
    }

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
    
    @Override
    public String toString(){
        return this.name;
    }
}
