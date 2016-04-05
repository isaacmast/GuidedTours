package com.example.isaacarondavid.guidedtours;

/**
 * Created by davidnester on 4/1/16.
 */
public class Destination {
    private float latitude;
    private float longitude;
    private int id;
    private String name;
    private int tourID;
    private String description;

    public Destination(int id, String name,float latitude, float longitude, String description, int tourID) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.tourID = tourID;
        this.description = description;
    }

    public float getLatitude() {
        return this.latitude;
    }
    public float getLongitude() {
        return this.longitude;
    }
    public int getTourID(){
        return this.tourID;
    }
    public String getDescription() {
        return this.description;
    }
    public int getId() {
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
