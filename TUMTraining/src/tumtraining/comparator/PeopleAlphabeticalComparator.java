package tumtraining.comparator;

import java.util.Comparator;

public class PeopleAlphabeticalComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		if (o1.name.compareTo(o2.name) < 0) {
			return -1;
		} else if (o1.name.compareTo(o2.name) > 0) {
			return 1;
		} else if (o1.age > o2.age) {
			return -1;
		} else if (o1.age < o2.age) {
			return 1;
		}
		return 0;
	}

}
