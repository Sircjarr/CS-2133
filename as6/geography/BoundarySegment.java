//This class is meant to be stored in ArrayLists within geographic objects.
// Contains information about the boundary segment's length, aribitrary neighbors, and names of neighbors.

import java.util.*;

public class BoundarySegment<E> {
	
	private double boundaryLength;
	private ArrayList<E> neighbors; // Only one of these ArrayLists will be used for each segment.
	private ArrayList<String> geoNames; // Stores the names of the geoObjects for borderOf() method.
	
	public BoundarySegment(double boundaryLength) {
		this.boundaryLength = boundaryLength;
	}
	
	// Getters and Setters
	public double getBoundaryLength() {
		return boundaryLength;
	}
	
	public void setBoundaryLength(double length) {
		boundaryLength = length;
	}
	
	public ArrayList<E> getNeighbors() {
		return neighbors;
	}
	
	public void setNeighbors(ArrayList<E> neighbors) {
		this.neighbors = neighbors;
		// geoNames are also set
	}
	
	// Returns ArrayList of the names of the geographic objects. 
	public ArrayList<String> borderOf() {
		return geoNames;
	}
}