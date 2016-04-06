package com.example.isaacarondavid.guidedtours;

/**
 * Created by davidnester on 4/1/16.
 */
public class Destination {
	private int tourId;
    private String name;
	private String description;
    private float latitude;
    private float longitude;
    private int id;

    public Destination(int id, String name,float latitude, float longitude, String description, int tourID) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.tourID = tourID;
        this.description = description;
	}

	public int getTourId() {
		return this.tourId;
	}

    public String getName(){
        return this.name;
    }

	public String getDescription() {
		return this.description;
	}

    public float getLatitude() {
        return this.latitude;
    }


    public float getLongitude() {
        return this.longitude;
    }
    
    public int getId() {
        return this.id;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
