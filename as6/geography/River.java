// The River class holds information about it's length and name.
// Information about a where a certain river flows through can be accessed in the State or Country class

public class River {
	
	String name;
	double lengthInMeters; 
	
	public River(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLengthInMeters() {
		return lengthInMeters;
	}
	
	public void setLengthInMeters(double lengthInMeters) {
		this.lengthInMeters = lengthInMeters;
	}
}