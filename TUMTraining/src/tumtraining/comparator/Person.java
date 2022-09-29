package tumtraining.comparator;

public class Person /*implements Comparable<Person>*/{
	int age;
	String name;

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	
	
	@Override
	public String toString() {
		return "  age: " + age + "  name: " + name;
	}



	/*-
	@Override
	public int compareTo(Person o) {
		if (this.age < o.age) {
			return -1;
		} else if (this.age > o.age) {
			return 1;
		} else {
			return this.name.compareTo(o.name);
		}
	}
	*/
	
}
