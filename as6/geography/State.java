// The State class holds all data regarding a particular state within a country.

import java.util.*; 
 
public class State implements GeographicObject {
	
	private ArrayList<City> cities;
	private ArrayList<River> rivers;
	private ArrayList<BoundarySegment> stateSegments;
	
	private Country country;
	
	private String name;
	
	public State(String name) {
		this.name = name;
	}
	
	// A constructor to be used after establishing all elements of the state.
	public State(String name, ArrayList<City> cities, ArrayList<River> rivers) {
		this.name = name;
		this.cities = cities;
		this.rivers = rivers;
	}
	
	/////////////////// Getters and Setters
	public ArrayList<City> getCities() {
		return cities;
	}
	
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
	
	public ArrayList<River> getRivers() {
		return rivers;
	}
	
	public void setRivers(ArrayList<River> rivers) {
		this.rivers = rivers;
	}
	
	public ArrayList<BoundarySegment> getStateSegments() {
		return stateSegments;
	}
	
	public void setStateSegments(ArrayList<BoundarySegment> stateSegments) {
		this.stateSegments = stateSegments;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	///////////////////////////
	
	// Goes through stateSegments and prints names of all neighboring states
	public void neighbors() {
		return;
	}
	
	// Goes through cities and prints names of all cities within state
	public void cities() {
		return;
	}
	
	// Goes through rivers and prints names of all rivers within state
	public void rivers() {
		return;
	}
	
	public void searchCity(String cityName) {
		;// searches city within state by name
	}
	
	public void searchRiver(String riverName) {
		;// searches river within state by name
	}
	public void addCity(City city) {
		;// adds a city to the city List
	}
	
	public void removeCity(String CityName) {
		; // removes a city by name;
	}
	
	// Methods of any geographic object
	@Override
	public double area() { // returns area of state
		return 0.0;
	}
	
	@Override
	public double boundaryLength() {
		double total = 0;
		for (int i = 0; i < stateSegments.size(); i++) {
			total += stateSegments.get(i).getBoundaryLength();
		}
		return total;
	}
}