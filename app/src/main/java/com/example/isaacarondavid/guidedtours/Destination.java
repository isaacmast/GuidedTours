package com.example.isaacarondavid.guidedtours;

/**
 * Created by davidnester on 4/1/16.
 */
public class Destination {
    private float latitude;
    private float longitude;
    private int id;
    private String name;

    public Destination(int id, String name,float latitude, float longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
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
    public String getName(){
        return this.name;
    }
    //String is id,name,latitude,longitude
    @Override
    public String toString(){
        return Integer.toString(this.getId()) + "," + this.getName() + "," + Float.toString(this.getLatitude()) + "," + Float.toString(this.getLongitude());
    }
}
