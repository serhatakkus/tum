package tumtraining.comparator;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		if (o1.age < o2.age) {
			return -1;
		} else if (o1.age > o2.age) {
			return 1;
		} else {
			return o1.name.compareTo(o2.name);
		}
	}

}
