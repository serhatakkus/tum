package tumtraining.exam;

public class NumberCompare {

	public static void main(String[] args) {
		compareString();
	}

	
	public static void findMax() {
		// 2 5 8 4 9 4 3 9 5 9 4 4 2
		
		System.out.println("9: 3 adet");
		System.out.println("2: 2 adet, 5: 2 adet, 9: 3 adet");
	}
	
	public static void compareString() {
		String str = "Exam";
		String str2 = "Exam";
		String str3 = new String("Exam");
		String str4 = "Test";
		
		System.out.println(str == "Exam");		// 
		System.out.println(str.equals("Exam")); // 		
		System.out.println("");					//
		System.out.println(str == str2);		// 
		System.out.println(str.equals(str2));	// 
		System.out.println("");					//
		System.out.println(str == str3);		// 
		System.out.println(str.equals(str3));	// 
		System.out.println("");					// 
		System.out.println(str == str4);		// 
		System.out.println(str.equals(str4));	// 
		System.out.println("");					//
		
		str4 = "Exam" + "";

		System.out.println(str == str4);		// 
		System.out.println(str.equals(str4));	// 
		
		str = "exam";
		str2 = "test";
		//??
		str2 = "exam";
		// equals ?? true
	}
	
	static void compareNumbers() {
		X x1 = new X(5);
		X x2 = new X(5);
		X x3 = x1;
		X x4 = new X(x1.x);
		
		double d1 = Math.sqrt(5.0) * Math.sqrt(5.0);
		double d2 = 5.0;
		double d3 = Math.sqrt(6.25) * Math.sqrt(6.25);
		double d4 = 6.25;
		
		System.out.println("text" + 2 + 3);
		System.out.println(2 + 3 + "text");
		System.out.println(5 / 2 + 2.5);
		
		System.out.println(x1 == x2);
		System.out.println(x1.equals(x2));	
		System.out.println(x1 == x3);		
		System.out.println(x1 == x4);		
		System.out.println();
		System.out.println("sqrt5: " + Math.sqrt(5.0));
		System.out.println("d1: " + d1 + "  d2: " + d2);
		System.out.println(d1 == d2);
		System.out.println(d3 == d4);
	}
}

class X {
	int x;
	
	public X (int x) {
		this.x = x;
	}
	
}

