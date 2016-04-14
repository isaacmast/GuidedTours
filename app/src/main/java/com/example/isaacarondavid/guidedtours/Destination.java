package com.example.isaacarondavid.guidedtours;

/**
 * Class to set and retrieve info about a Destination stored in a DB.
 * Created by davidnester on 4/1/16.
 */
public class Destination {
	private int tourId;
	private int destinationId;
	private String name;
	private String description;
	private float latitude;
	private float longitude;

	/**
	 * Constructs a new Destination object with all of its values
	 * @param tourId - the id number of the Tour that it's associated with
	 * @param destinationId - the Destination object's id number
	 * @param name - the name of the Destination
	 * @param description - a detailed information about the Destination
	 * @param latitude - the latitude coordinate of the Destination
	 * @param longitude - the longitude coordinate of the Destination
	 */
	public Destination(int tourId, int destinationId, String name, String description, float latitude, float longitude) {
		this.tourId = tourId;
		this.destinationId = destinationId;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Retrieves the id number of the Tour it is associated with
	 * @return tourId - the Tour id number
	 */
	public int getTourId() {
		return this.tourId;
	}

	/**
	 * Sets the id number of the Tour it is associated with
	 * @param tourId - the new Tour id number
	 */
	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	/**
	 * Retrieves the id number of the Destination
	 * @return destinationId - the Destination id number
	 */
	public int getDestinationId() {
		return this.destinationId;
	}

	/**
	 * Sets the id number of the Destination
	 * @param destinationId - the new Destination id number
	 */
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
    
	/**
	 * Retrieves the name of the Destination
	 * @return name - the Destination name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Sets the name of the Destination
	 * @param name - the new name of the Destination
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the description of the Destination
	 * @return description - the Destination description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the Destination
	 * @param description - the new Destination description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the latitude coordinate of the Destination
	 * @return latitude - the Destination latitude coordinate
	 */
	public float getLatitude() {
		return this.latitude;
	}

	/**
	 * Sets the latitude coordinate of the Destination
	 * @param latitude - the new Destination latitude coordinate
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Retrieves the longitude coordinate of the Destination
	 * @return longitude - the Destination longitude coordinate
	 */
	public float getLongitude() {
		return this.longitude;
	}

	/**
	 * Sets the longitude coordinate of the Destination
	 * @param longitude - the new Destination longitude coordinate
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return this.name;
	}
}
