package tumtraining.exam;

import java.io.FileNotFoundException;

public class Operators {

	public static void main(String[] args) {
		//compare();
		int x = (-8)%3;
		int y = 8%(-3);
		int z = (-8)%(-3);
		System.out.println("" + false + x + " " + y + " " + z);
	}
	
	public static void compare() throws Exception, FileNotFoundException{
		boolean b1, b2, b3, b4, b5;
		b1 = 4 == 4.0;
		b2 = 7L == (short)5;
		// b3 = true == 1;
		b4 = 'd' == 100;
		b5 = false == b4;
		
		double d;
		long l;
		int i;
		short s;
		
		b3 = 7L == 6D;
		
		d = 4;
		i = (int)4.0;
		s = (short)d;
		
		System.out.println(b1);
		System.out.println(b2);
		// System.out.println(b3);
		System.out.println(b4);
		System.out.println(b5);
	}

}
