//NOTE: When we talk about abstract classes we are defining characteristics of an object type; specifying what an object is.

//NOTE: When we talk about an interface and define capabilities that we promise to provide, we are talking about establishing 
//a contract about what the object can do.

// The Country, State, and City class all fall under the category of a GeographicObject. They all should have an area and
// a boundary length

public interface GeographicObject {
	
	public double area();
	public double boundaryLength();
}