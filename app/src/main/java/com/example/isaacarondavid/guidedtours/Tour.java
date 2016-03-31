package com.example.isaacarondavid.guidedtours;

public class Tour {

	private int id;
	private String name;

	public Tour() {
	
	}

	public Tour(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	//I will make an object for a stop on the tour and then this object can contain
	//an arraylist of stop objects. Then we can make methods that convert the list of
	//stops to one string and then a method that can parse out that string.
}
