
public abstract class Person {
	
	public String name;
	public String password;
	public String rank;
	
	public Person(String name, String password) {
		this.name=name;
	}
		
	public String getName() {
		return this.name;
	}
	public String getRank() {
		return this.rank;
	}
	
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setRank(String rank) {
		this.rank=rank;
	}
	
	public boolean equals(Object obj) {
		if(this.equals(obj))
			return true;
		if(obj instanceof Person) {
			Person that = (Person) obj;
			if(this.name.equals(that.name) && this.rank.equals(that.rank))
				return true;
		}
		return false;
	}

}
