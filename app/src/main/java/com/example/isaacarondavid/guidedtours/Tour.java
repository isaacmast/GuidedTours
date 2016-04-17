package com.example.isaacarondavid.guidedtours;

/**
 * Class to set and retrieve info about a Tour stored in a DB
 * @author Isaac Mast
 */
public class Tour {

	// declare instance variables
	private int id;
	private String name;
	private String description;

	/**
	 * Constructs a new Tour object with all of its values
	 * @param id - the id number of the Tour
	 * @param name - the name of the Tour
	 * @param description - detailed information about the Tour
	 */
	public Tour(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * Retrieves the id number of the Tour
	 * @return id - the Tour id number
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id number of the Tour
	 * @param id - the new Tour id number
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the Tour
	 * @return name - the Tour name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Tour
	 * @param name - the new Tour name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the description of the Tour
	 * @return description - the Tour description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the Tour
	 * @param description - the new Tour description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return name;
	}

}
