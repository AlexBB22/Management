
public class Student extends Person{

	public Student(String name,String password) {
		super(name, password);
		this.rank="student";
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Student && super.equals(obj);
	}
}
