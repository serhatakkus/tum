package tumtraining;

public class Exam {

	public static void main(String[] args) throws InterruptedException {

		int[] arr = new int[3];
		arr[0] = 3;
		arr[1] = 5;
		arr[2] = 5;
		
		// System.out.println(Example.sum(arr));
		//System.out.println(Example.sum(5/2, 2.1));
		
		String animal = getAnimalType();
		if (animal == "penguin")
			cuddleAnimal(animal);
		else
			System.out.println("no cuddle :(");
	}

	
	
	
	public static void question2() {
		// initial value. will be used to store the current numeric value of all digits
		int number = 0;
		String s = "wer45h67";

		/*- method 1 */
		for (int i = s.length(); i > 0; i--) {
			char c = s.charAt(i - 1);
			if (Character.isDigit(c)) {
				int ch = c - '0';

				number = /* number */ +c - '0';

				System.out.println("" + number);
			}
		}

		/*- method 2 */
		// iterate the characters of the current string from left to right.
		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			if (Character.isDigit(c)) {
				// increase the power (10^1, 10^2, etc) of the current number
				// and add the new digit as the least powerfull digit (10^0 birler basamaðý)
				number = number * 10 + c;
			}
		}
	}

	private static String getAnimalType() {
		String animalType = "penguin";
		return animalType;
	}
	
	private static String getAnimalType2() {
		String animalType = "penguin";
		return animalType + "";
	}

	private static void cuddleAnimal(String anm) {
		System.out.println("cuddle " + anm);
	}

	private static void example() {
		System.out.println("started...");

		String animal = "penguin";// getAnimalType();
		String animal2 = new String("owl");// getAnimalType();

		if (animal == animal2)
			cuddleAnimal(animal);
		else
			System.out.println("no cuddle :(");

		animal2 = new String("penguin");

		if (animal.equals(animal2))
			cuddleAnimal(animal);
		else
			System.out.println(" no cuddle again :'( ");

		System.out.println("finished.");
	}

}

class Example {
	public int length = 5;

	public long length(int newValue) {
		int x = 2;
		System.out.println("ben biþeyler yapýyorum...");
		this.length = this.length + newValue;
		return this.length * 3;
	}
	
	public static double sum(double var1, double var2) {
		return var1 + var2;
	}
	
	public static double sum(int var1, int var2) {
		return var1 + var2 + 1;
	}
	
	public static double sum(int var1, int var2, int var3) {
		return var1 + var2 + var3;
	}
	
	public static int sum(int[] args) {
		int result = 0;
		
		for (int i = 0; i < args.length; i++) {
			result = result + args[i];
		}
		
		System.out.println("ben çalýþtým");
		return result;
	}
}
