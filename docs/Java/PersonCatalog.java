import java.util.ArrayList;
import java.util.List;

public class PersonCatalog {
	
	private List<Person> catalog;
	
	
	/**
	 * @return the persons
	 */
	public List<Person> getPrinters() {
		return catalog;
	}

	
	/**
	 * Constructor for the person catalog
	 */
	public PersonCatalog() {
		this.catalog = new ArrayList <Person>();
	}
	
	/**
	 * Adds a new teacher to the catalog
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher) {
		this.catalog.add(teacher);
		
	}
	
	/**
	 * Adds a new student to the catalog
	 * @param student
	 */
	public  void addStudent(Student student) {
		this.catalog.add(student);
		
	}
	
	/**
	 * Adds a new staff member to the catalog
	 * @param staff
	 */
	public void addStaff(Staff staff) {
		this.catalog.add(staff);
	}
	
	/**
	 * Adds a new admin to the catalog
	 * @param admin
	 */
	public void addAdmin(Admin admin) {
		this.catalog.add(admin);
	}

	
}
