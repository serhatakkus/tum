package tumtraining.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tumtraining.poly.Animal;
import tumtraining.poly.Bird;

public class Exam {

	public static void main(String[] args) {
		Integer [] array = new Integer[] {1, 5, 7, 4};
		
		int number = 1234;
		Integer integer = 4567;
		
		
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(0);
		lst.add(0);
		lst.add(0);
		lst.add(0);
		System.out.println("");

		Animal myfriend = new Bird("birdie");
		myfriend.age = 3;
		
		
		// array
		System.out.println(Arrays.toString(array));
		inc1(array);
		System.out.println(Arrays.toString(array));
		System.out.println();
		
		// elements of array
		System.out.println(Arrays.toString(array));
		inc2(array);
		System.out.println(Arrays.toString(array));
		System.out.println();
		
		// number
		System.out.println(number);
		inc3(number);
		System.out.println(number);
		System.out.println();
		
		// integer
		System.out.println(integer);
		inc4(integer);
		System.out.println(integer);
		System.out.println();

		

		// integer list
		for (int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i) + " ");
		}
		System.out.println("");
		inc5(lst);
		for (int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i) + " ");
		}
		System.out.println("");
		inc6(lst);

		for (int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i) + " ");
		}
		System.out.println("");
		

		System.out.println(myfriend.age);
		inc7(myfriend);
		System.out.println(myfriend.age);
		System.out.println("");

		System.out.println(myfriend.age);
		inc8(myfriend);
		System.out.println(myfriend.age);
		System.out.println("");
	}
	
	static void inc1(Integer[] array) {
		array = new Integer[] {6,7,8,9};
		System.out.println("inner: " + Arrays.toString(array));
		Arrays.sort(array);
		System.out.println("inner: " + Arrays.toString(array));
	}
	
	static void inc2(Integer[] array) {
		array[0] += 1;
		array[1] += 1;
		array[2] += 1;
		array[3] += 1;
		System.out.println("inner: " + Arrays.toString(array));
		Arrays.sort(array);
		System.out.println("inner: " + Arrays.toString(array));
	}
	
	static void inc3(int number) {
		number += 1111;
		System.out.println("inner: " + number);
	}
	
	static void inc4(Integer integer) {
		integer = integer + 1111;
		System.out.println("inner: " + integer);
	}
	
	static void inc5(List<Integer> lst) {
		lst = new ArrayList<Integer>();
		lst.add(1);
		lst.add(1);
		lst.add(1);
		lst.add(1);

		System.out.print("inner values: ");
		for (int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i) + " ");
		}

		System.out.println("");
	}
	
	static void inc6(List<Integer> lst) {
		lst.set(0, 2);
		lst.set(1, 2);
		lst.set(2, 2);
		lst.set(3, 2);

		System.out.print("inner values: ");
		for (int i = 0; i < lst.size(); i++) {
			System.out.print(lst.get(i) + " ");
		}
		System.out.println("");
	}
	
	static void inc7(Animal animal) {
		animal.age++;
		System.out.println("inner: " + animal.age);
	}
	
	static void inc8(Animal animal) {
		animal = new Bird("birdie");
		animal.age = 5;
		System.out.println("inner: " + animal.age);
	}
	
}

