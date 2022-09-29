package tumtraining.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import tumtraining.poly.Animal;
import tumtraining.poly.Bird;
import tumtraining.poly.Cat;
import tumtraining.poly.Dog;
import tumtraining.poly.Parrot;

public class GenericClass {

	public static void main(String[] args) {

		List<Object> lst = new ArrayList<Object>();
		lst.add(new Object());
		lst.add(new Integer(3));
		lst.add(Integer.valueOf(17));
		lst.add("asd");
		// int l = (Integer)(lst.get(3));

		List<Integer> lstInt = new ArrayList<Integer>();
		lstInt.add(1);
		lstInt.add(1);
		lstInt.add(3);
		lstInt.add(4);
		int k = 56;
		lstInt.add(k);

		lst.iterator().next();
		lstInt.iterator().next();

		List<Animal> animals = new ArrayList<Animal>();
		Cat cat = new Cat("my cat");
		animals.add(cat);

		animals.iterator().next();

		// primitive type: type arguments primitive olamaz !
		List<Integer> primitives = new ArrayList<Integer>();

		// generic casting
		// List<Object> objs = (List<Object>)lstInt; // Compiler error
		
		// List of Lists
		// List<List<Object>> allLists = new ArrayList<List<Object>>();
		List<List<? extends Object>> allLists = new ArrayList<List<? extends Object>>();
		allLists.add(lst);
		allLists.add(lstInt);
	}

	public static void genericNeed() {
		List<Integer> lst = new LinkedList<Integer>();

		lst.add(3);

		int first = lst.iterator().next();

	}

	/**
	 * generic method kullanýmý
	 * 
	 * @param <T> bu parametre, methodun generic method olduðunu belirtir. bu
	 *            paramtreyi silince methodun aldýðý parametreler generic iken hata
	 *            verdiðini görebiliriz.
	 * @param arr
	 * @return
	 */
	public static <T extends Number> List<T> toList(T[] arr) {
		List<T> lst = Arrays.stream(arr).collect(Collectors.toList());
		return lst;
	}

	/*-
	public static String toList(Object arr) {
		return arr.toString();
	}
	*/

	/*-
	public static <T> List<T> toList(T[] arr) {
		List<T> lst = Arrays.stream(arr).collect(Collectors.toList());
		return lst;
	}
	
	public static <Object> List<Object> toList(Object[] arr) {
		List<Object> lst = Arrays.stream(arr).collect(Collectors.toList());
		return lst;
	}
	
	*/

	/**
	 * multiple bounding Classes must be given first (before interfaces) !!!
	 * 
	 * @param <T>
	 * @param arr
	 * @return
	 */
	public static <T extends Number & Comparable<T> & Iterable<T>> List<T> toListCompare(T[] arr) {
		List<T> lst = Arrays.stream(arr).collect(Collectors.toList());
		return lst;
	}

	public static void CallGenericMethod() {
		Integer[] arrInt = new Integer[] { 2, 5, 6 };
		toList(arrInt);
		String[] arrStr = new String[] { "1", "a", "df" };
		System.out.println(arrStr);
		// toList(arrStr); // --> Bounded Generics
	}

	// wild cards
	public static void workForAnimals(List<Animal> animals) {
		// do something...
	}

	public static void workForAnimalTypes(List<? extends Animal> animals) {
		// do something...
	}

	public static <T extends Animal> void workForAnimal(T animal) {

	}

	public static void callAnimals() {
		List<Animal> lst = new ArrayList<Animal>();
		Cat cat = new Cat("a");
		Dog dog = new Dog();
		Cat cat2 = new Cat("b");
		lst.add(cat2);
		lst.add(dog);
		lst.add(cat);

		workForAnimals(lst);

		List<Bird> birds = new ArrayList<Bird>();
		Bird b1 = new Bird("b1");
		Bird b2 = new Bird("b2");
		Parrot p1 = new Parrot("little parrot");
		birds.add(p1);
		birds.add(b1);
		birds.add(b2);

		List<Parrot> parrots = new ArrayList<Parrot>();
		parrots.add(p1);

		// workForAnimals(birds); // compile error
		workForAnimalTypes(birds);
		// workForAnimals(parrots); // compile error
		workForAnimalTypes(parrots);
	}

}
