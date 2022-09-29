package tumtraining.recursion;

import java.util.List;

public class Recursion {

	public static void main(String[] args) {

		int[] arr = new int[] {1,2,3,1};
		
		for(int i = 0; i < 4; i++) {
			System.out.println(arr[i]);
		}
		
		/*-
		for (int a : arr) {
			System.out.println(a);
		}
		*/
		
		/*-
		writeToConsole("123456789");
		writeRecursive("123456789");
		*/
		//factor5();
		factor(5);
	}

	public static int method() {
		return method();
	}

	public static void writeToConsole(String text) {
		System.out.println(text);
	}

	public static void writeRecursive(String text) {
		if (text == null || text.length() == 0) {
			System.out.print("\n");
		} else {
			System.out.print(text.charAt(0));
			String newText = text.substring(1);
			writeRecursive(newText);
		}
	}

	// n! = n * (n-1)!
	// 4! = 4 * 3!
	public static int factor(int prm) {
		System.out.println(prm + " için çalýþýyorum");
		if (prm == 0 || prm == 1) {
			System.out.println("return " + 1);
			return 1;
		}
		System.out.println(prm + " x factor(" + (prm - 1) + ")");
		int result = prm * factor(prm - 1);
		System.out.println("return " + result);
		return result;
	}
	
	

	
	public static int factor5() {
		int result = 5 * factor4();
		return result;
	}
	
	public static int factor4() {
		int result = 4 * factor3();
		return result;
	}
	
	public static int factor3() {
		int result = 3 * factor2();
		return result;
	}
	
	public static int factor2() {
		int result = 2 * factor1();
		return result;
	}
	
	public static int factor1() {
		return 1;
	}

}
