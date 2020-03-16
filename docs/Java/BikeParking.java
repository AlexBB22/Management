import java.util.ArrayList;
import java.util.List;

//will contain a list of all bikes that can be reserved at a certain location
public class BikeParking {
	List<Bike> bikes;
	String location;
	int numberOfBikes;
	int reservedBikes;
	int availableBikes;
	
	public BikeParking(String location, int numberOfBikes) {
		this.numberOfBikes=numberOfBikes;
		this.location=location;
		this.reservedBikes=0;
		this.availableBikes=numberOfBikes;
		this.bikes=new ArrayList<Bike>();
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumberOfBikes() {
		return numberOfBikes;
	}

	public void setNumberOfBikes(int numberOfBikes) {
		this.numberOfBikes = numberOfBikes;
	}

	public int getReservedBikes() {
		return reservedBikes;
	}

	public void setReservedBikes(int reservedBikes) {
		this.reservedBikes = reservedBikes;
	}

	public int getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(int availableBikes) {
		this.availableBikes = availableBikes;
	}
	
}
