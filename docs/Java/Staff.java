
public class Staff extends Person {
	
	public Staff(String name, String password) {
		super(name, password);
		this.rank="staff";
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Staff && super.equals(obj);
	}

}
