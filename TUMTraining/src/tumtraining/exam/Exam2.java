package tumtraining.exam;

import java.util.Arrays;

public class Exam2 {

	public static void main(String[] args) {
		int [] array = new int[] {1, 2, 3, 4};
		int [] array2 = new int[] {1, 2, 3, 4};
		int number = 1234;

		inc1(array);
		inc2(array2);
		inc3(number);
		number = inc4(number);
		
		System.out.println(Arrays.toString(array)); // 1234
		System.out.println(Arrays.toString(array2)); // 2345
		System.out.println(number);					 // 2345
		
		Error err  =new Error();
		Exception ex = new Exception();
		
		long x = 5;
		double d = (double)x;
	}
	
	static void inc1(int[] array) {
		array = new int[] {2,3,4,5};
	}
	
	static void inc2(int[] qwerty) {
		qwerty[0] += 1;
		qwerty[1] += 1;
		qwerty[2] += 1;
		qwerty[3] += 1;
	}
	
	static void inc3(int number) {
		number = number + 1111;
	}
	
	static int inc4(int number) {
		return number + 1111;
	}

}
