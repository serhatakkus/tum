package tumtraining.exam;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Exam3 {

	public static void main(String[] args) {
		divideByZero();
		
	}
	
	private static void divideByZero() {
	    int numerator = 1;
	    int denominator = 0;
	    int result = numerator / denominator;
	    
	    System.out.println(result);
	    
	}

	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void foobar() throws FileNotFoundException {

		FileReader fr = new FileReader(" C: \\ PinguDirectory \\ fish.txt ");

	}
	
	public void foobar2() {
		try {
			FileReader fr = new FileReader(" C: \\ PinguDirectory \\ fish.txt ");
		} catch(FileNotFoundException e) {
			System.out.println("Ups!");
		} catch (Exception ex) {
			System.out.println();
		}
	}
}
