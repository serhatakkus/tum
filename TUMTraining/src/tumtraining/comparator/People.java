package tumtraining.comparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class People {

	public static void main(String[] args) {
		
		List<Person> people = new ArrayList<Person>();
		people.add(new Person(13, "john"));
		people.add(new Person(39, "mike"));
		people.add(new Person(9, "steve"));
		people.add(new Person(21, "john"));
		people.add(new Person(13, "al"));
		
		/*-
		people.sort(null);
		people.forEach(person -> System.out.print(person.toString()));
		*/
		/*- */
		people.sort(new PersonComparator());
		
		people.forEach(person -> System.out.print(person.toString()));
		
		System.out.println();

		people.sort(new PeopleAlphabeticalComparator());
		
		people.forEach(person -> System.out.print(person.toString()));
		System.out.println();

		people.sort(new PeopleAlphabeticalComparator().reversed());
		
		people.forEach(person -> System.out.print(person.toString()));
		/* */
		
		/*-
		 null, null, 4, null, 13
		 */
	}

}
