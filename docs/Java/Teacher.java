
public class Teacher extends Person {

	public Teacher(String name, String password) {
		super(name,password);
		this.rank="teacher";
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getRank() {
		return this.rank;
	}
	
	
	public boolean equals(Object obj) {
		return obj instanceof Teacher && super.equals(obj);

	}
}


