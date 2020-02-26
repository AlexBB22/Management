
public class Admin extends Person {
	
	public Admin(String name, String password) {
		super(name, password);
		this.rank="admin";
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Admin && super.equals(obj);
	}
}
