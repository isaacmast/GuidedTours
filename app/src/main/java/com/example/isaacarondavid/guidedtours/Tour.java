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
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public float getPrimaryLat() {
		return primaryLat;
	}
	public float getPrimaryLong() {
		return primaryLong;
	}

	@Override
	public String toString() {
		return name;
	}

}
