
public class Bike {
	
	String id;
	boolean available;
	
	public Bike(String id) {
		this.id=id;
		this.available=true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void changeAvailability() {
		if(this.available==true) {
			this.available=false;
		}
		else 
			this.available=true;
	}
	
	
}