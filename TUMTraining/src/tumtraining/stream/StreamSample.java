package tumtraining.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tumtraining.poly.Animal;
import tumtraining.poly.Cat;

public class StreamSample {

	public static void main(String[] args) {
		/*-
		filter();
		match();
		map();
		reduce();
		foreach();
		lambdaExp();
		distinct();
		*/
		sorted();
	}

	public static void filter() {
		List<String> lst = new ArrayList<String>();
		lst.add("hello");
		lst.add("this");
		lst.add("is");
		lst.add("my");
		lst.add("stream");
		lst.add("example");

		Stream<String> stream = lst.stream();
		// System.out.println("count: " + stream.count());

		Stream<String> filtered = stream.filter(word -> word.contains("i"));
		// List<String> filtered = stream.filter(word ->
		// word.contains("i")).collect(Collectors.toList());
		System.out.println(filtered.toList());

		// System.out.println("count: " + filtered.count());
	}

	public static void match() {

		List<String> lst = new ArrayList<String>();
		lst.add("hello");
		lst.add("this");
		lst.add("is");
		lst.add("my");
		lst.add("stream");
		lst.add("example");

		// Stream<String> stream = lst.stream();
		boolean result = lst.stream().noneMatch(word -> word.length() < 2);

		System.out.println(result);
	}

	public static void map() {

		List<String> lst = new ArrayList<String>();
		lst.add("hel.lo");
		lst.add("t.his");
		lst.add("is.");
		lst.add("my");
		lst.add("st.ream");
		lst.add("example");

		Stream<Integer> resultStream = lst.stream().map(word -> word.indexOf("."));
		System.out.println(Arrays.toString(resultStream.toArray()));
		Integer result = lst.stream().map(word -> word.indexOf(".")).filter(idx -> idx >= 0).reduce(0,
				(w1, w2) -> w1 + w2);
		System.out.println(result);
	}

	public static void reduce() {

		List<String> lst = new ArrayList<String>();
		lst.add("hello");
		lst.add("this");
		lst.add("is");
		lst.add("my");
		lst.add("stream");
		lst.add("example");

		String sentence = lst.stream().reduce("", (word1, word2) -> word1 + " " + word2);
		System.out.println(sentence.trim());

		List<Integer> lst2 = new ArrayList<Integer>();
		lst2.add(4);
		lst2.add(6);
		lst2.add(3);
		lst2.add(123);
		lst2.add(65);
		lst2.add(87);

		Integer total = lst2.stream().reduce(1, (word1, word2) -> word1 + word2);
		System.out.println(total);

		/*-
		String newStr = null;
		for (String str : lst) {
			newStr = newStr + " " + str;
		}
		
		System.out.println(newStr.trim());
		*/
	}

	public static void foreach() {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(6);
		lst.add(3);
		lst.add(12);
		lst.add(0);

		for (Integer i : lst)
			System.out.println("i in for: " + i);

		lst.forEach(i -> System.out.println("i is " + i));
	}
	
	public static void lambdaTrial() {
		WriteSomething mywriter = (str) -> System.out.println("my string: " + str);
		mywriter.write(53);
		
		
		Writer wrt = () -> System.out.println("i don't write anyting...");
		wrt.writeConsole();
		
		Runnable run1 = () -> System.out.println("runnable is running");
		Thread thread1 = new Thread(run1);
		
		Thread thread2 = new Thread(() -> System.out.println("runnable is running"));
		
		Thread thread3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		
		thread3.start();
		
		
		//--------------------
		
		
		Multiplier multi = (x, y) -> {
			int x1 = Math.abs(x);
			int y1 = Math.abs(y);
			return x1 * y1;
		};
		
		System.out.println(multi.mult(-3, 21));
	}

	public static void lambdaExp() {
		Writer helloWriter = new Writer() {

			@Override
			public void writeConsole() {
				System.out.println("non-lambda example !!");
			}
		};

		helloWriter.writeConsole();

		// () -> {}

		Writer lambdaWriter = () -> {
			System.out.println("this is real lambda expression example !");
		};

		lambdaWriter.writeConsole();

		WriteSomething somethingWriter = x -> System.out.println("I am " + x);
		somethingWriter.write(56);

		Multiplier multi = (x, y) -> (x * y);

		System.out.println(multi.mult(4, 6));
	}

	public static void distinct() {

		List<Integer> lst = new ArrayList<Integer>();
		lst.add(6);
		lst.add(3);
		lst.add(12);
		lst.add(0);
		lst.add(6);
		lst.add(3);
		lst.add(12);
		lst.add(0);

		lst.forEach(i -> System.out.print("  i= " + i));

		List<Integer> unique = lst.stream().distinct().collect(Collectors.toList());
		System.out.println("");
		unique.forEach(i -> System.out.print("  i= " + i));

		Set<Integer> set = lst.stream().collect(Collectors.toSet());
		System.out.println("");
		set.forEach(i -> System.out.print("  i= " + i));

	}
	
	public static void sorted() {

		List<Integer> lst = new ArrayList<Integer>();
		lst.add(6);
		lst.add(3);
		lst.add(12);
		lst.add(0);
		lst.add(6);
		lst.add(3);
		lst.add(12);
		lst.add(0);

		/*-
		lst.sort((x1, x2) -> x1.compareTo(x2));
		lst.forEach(i -> System.out.print(" " + i));
		*/
		/*-
		List<Integer> sortedList = lst.stream().sorted().collect(Collectors.toList());
		sortedList.forEach(i -> System.out.print(" " + i));
		*/
		
		List<Person> people = new ArrayList<Person>();
		people.add(new Person(13, "john"));
		people.add(new Person(39, "mike"));
		people.add(new Person(39, "steve"));
		people.add(new Person(21, "john"));
		people.add(new Person(13, "al"));
		
		people.forEach(person -> System.out.print(person.toString()));
		people.sort(Person::comparePeople);
		System.out.println("");
		people.forEach(person -> System.out.print(person.toString()));

	}
}

class Person {
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

	public static int comparePeople(Person p1, Person p2) {
		if (p1.age < p2.age) {
			return -1;
		} else if (p1.age > p2.age) {
			return 1;
		} else {
			return p1.name.compareTo(p2.name);
		}
	}
}

interface Writer {
	void writeConsole();
}

@FunctionalInterface
interface WriteSomething {
	void write(int something);
}

interface Multiplier {
	int mult(int x, int y);
}
