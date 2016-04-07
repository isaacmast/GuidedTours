package com.example.isaacarondavid.guidedtours;

public class Tour {

	private int id;
	private String name;
	private String description;
	private float primaryLat;
	private float primaryLong;

	public Tour(int id, String name, String description, float lat, float lon){
		this.id = id;
		this.name = name;
		this.description = description;
		this.primaryLat = lat;
		this.primaryLong = lon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public float getPrimaryLat() {
		return primaryLat;
	}

	public void setPrimaryLat(float lat) {
		this.primaryLat = lat;
	}

	public float getPrimaryLong() {
		return primaryLong;
	}

	public void setPrimaryLong(float lon) {
		this.primaryLong = lon;
	}

	@Override
	public String toString() {
		return name;
	}

}
