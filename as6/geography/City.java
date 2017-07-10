// The data within contains coordinates for a distance equation,
// capital status, and possibly boundary segments.

import java.util.*;

public class City implements GeographicObject {
	
	private ArrayList<BoundarySegment> citySegments;
	private Country country;
	
	private int xCoord;
	private int yCoord;
	private boolean isCapital;
	
	private String name;
	
	public City(String name) {
		this.name = name;
		isCapital = false;
	}
	
	// A constructor to be used after establishing all elements of the city.
	public City(String name, int xCoord,int yCoord,boolean isCapital) {
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.isCapital = isCapital;
	}
	
	/////// Getters and Setters
	public ArrayList<BoundarySegment> getCitySegments() {
		return citySegments;
	}
	
	public void setStateSegments(ArrayList<BoundarySegment> citySegments) {
		this.citySegments = citySegments;
	}
	
	public boolean getIsCapital() {
		return isCapital;
	}
	
	public void setCapital(boolean isCapital) {
		this.isCapital = isCapital;
	}
	
	public void setXcoord(int x) {
		xCoord = x;
	}
	
	public void setYcoord(int y) {
		yCoord = y;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	///////////////////////////////////////
	
	// Uses coordinates to calculate distance between cities
	public double distance(City two) { 
		return 0.0; 
	}
	
	// Goes through citySegments and returns ArrayList of all neighboring cities
	public ArrayList<City> neighbors() {
		return null;
	}
	
	// Methods of any geographic object
	@Override
	public double area() { // returns area of city
		return 0.0;
	}
	
	@Override
	public double boundaryLength() {
		double total = 0;
		for (int i = 0; i < citySegments.size(); i++) {
			total += citySegments.get(i).getBoundaryLength();
		}
		return total;
	}
}