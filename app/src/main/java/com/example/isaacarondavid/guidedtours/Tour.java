package com.example.isaacarondavid.guidedtours;

import java.util.ArrayList;

public class Tour {

	private int id;
	private String name;
	private ArrayList<Destination> destinations;
	private Destination primaryDestination;

	public Tour(int id, String destinations){
		this.id = id;
		this.destinations = parse(destinations);
		//name and primary destination assigned in parse() method
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

	public Destination getPrimaryDestination() {
		return this.primaryDestination;
	}

	public void setPrimaryDestination(Destination primary) {
		this.primaryDestination = primary;
	}

	public void addDestination(Destination newDest){
		this.destinations.add(this.destinations.size(),newDest);
	}

	public ArrayList<Destination> getDestinations() {
		return this.destinations;
	}

	@Override
	public String toString() {
		return name;
	}
	/*
		Given a string that is stored in the DB containing the tour info.
		 this method parses it and assigns the Tours name and primary destination as well
	 */
	public ArrayList<Destination> parse(String destList){
		String latitude;
		String longitude;
		int id;
		ArrayList<Destination> result = new ArrayList<Destination>();
		int index = 0;
		String name;
		this.name = destList.substring(0,destList.indexOf("$"));
		destList = destList.substring(destList.indexOf("$")+1);
		this.primaryDestination = parseDestination(destList.substring(0, destList.indexOf("#")));
		destList = destList.substring(destList.indexOf("#")+1);
		while (destList.contains("|")){
			result.add(index,parseDestination(destList.substring(0,destList.indexOf("|"))));
			destList.substring(destList.indexOf("|"));
		}
		return result;
	}
	/*
		used to parse out a string containing a destination.
		Used by the parse() method
	 */
	public Destination parseDestination(String dest){
		int id = Integer.parseInt(dest.substring(0, dest.indexOf(",")));
		dest.replace(Integer.toString(id)+",","");
		String name = dest.substring(0, dest.indexOf(","));
		dest.replace(name+",","");
		float latitude = Float.parseFloat(dest.substring(0, dest.indexOf(",")));
		dest.replace(Float.toString(latitude)+",", "");
		float longitude = Float.parseFloat(dest.substring(0,dest.length()-1));
		return new Destination(id,name,latitude,longitude);
	}

	/*
	Store tour in format: name + "$" + primaryDestination+ "#" + Destinations separated by "|" + "&"
	*/
	public String toStore(){
		String result = this.getName() + "$" + primaryDestination.toString() + "#";

		for (int i = 0; i<this.destinations.size(); i++){
			result += this.destinations.get(i).toString() + "|";
		}
		result = result.substring(0,result.length()-1); //remove last bar
		result += "&";
		return result;
	}
}
