package com.example.isaacarondavid.guidedtours;

/**
 * Created by davidnester on 4/1/16.
 */
public class Destination {
    private int destinationId;
	private int tourId;
    private String name;
	private String description;
    private float latitude;
    private float longitude;

    public Destination(int destinationId, String tourId, String name, String description, float latitude, float longitude) {
        this.destinationId = destinationid;
		this.tourId = tourId;
        this.name = name;
		this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getDestinationId() {
        return this.id;
    }

	public void setDestinationId(int id) {
		this.id = id;
	}

	public int getTourId() {
		return this.tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
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

    //String is id,name,latitude,longitude
    @Override
    public String toString(){
        return Integer.toString(this.getId()) + "," + this.getTourId() + "," + this.getName() + "," + Float.toString(this.getLatitude()) + "," + Float.toString(this.getLongitude());
    }
}
