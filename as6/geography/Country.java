//USAGE: easiest usage method is to construct all cities, construct all States with city data, construct Country with State Data.

// Country class is the broadest geographic object that contains all the data within it's states and cities. 

import java.util.*;
import java.lang.Math;

public class Country extends State implements GeographicObject { // Because properties of states can be found in it's country, Country extends state.
	
	private ArrayList<State> states; // Unique in country class
	private ArrayList<BoundarySegment> countrySegments;
	
	private String name; 
	
	public Country(String name) {
		super(name); 
	}
	
	// A constructor to be used after establishing all elements of the country.
	public Country(String name, ArrayList<City> cities, ArrayList<River> rivers, ArrayList<State> states) {
		super(name, cities, rivers);
		this.states = states;
	}
	
	/////// Getters and Setters
	public ArrayList<State> getStates() {
		return states;
	}
	
	public void setStates(ArrayList<State> states) {
		this.states = states;
	}
	
	public ArrayList<BoundarySegment> getBoundarySegments() {
		return countrySegments;
	}
	
	public void setBoundarySegments(ArrayList<BoundarySegment> countrySegments) {
		this.countrySegments = countrySegments;
	}
	////////////////
	
	// returns State if found by name
	public State searchState(String StateName) { 
		return null;
	}
	
	public void addState(State state) {
		state.setCountry(this);
		return; // Adds state to the country
	}
	
	public void removeState(String stateName) {
		return;// removes State by name
	}
	
	public void addCity(City city, State state) {
		;// adds a city to the city List and assigns a state
	}
	
	// finds the capital of the country and returns name
	public String capital() {
		return null;
	}
	
	public void setCapital(City city) {
		city.setCapital(true);
	}
	
	// Goes through countrySegments and prints names of all neighboring countries
	@Override
	public void neighbors() {
		return;
	}
	
	// Methods of any geographic object
	@Override
	public double area() { // returns area of country
		return 0.0;
	}
	
	@Override
	public double boundaryLength() {
		double total = 0;
		for (int i = 0; i < countrySegments.size(); i++) {
			total += countrySegments.get(i).getBoundaryLength();
		}
		return total;
	}
}