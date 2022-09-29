package tumtraining.loop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LoopTest {

	@Test
	public void testRemoveEven0() {
		
		List<Integer> testList = new ArrayList<Integer>();
		testList.add(1);
		testList.add(2);
		
		List<Integer> result = Loop.removeEven(testList);
		
		assertEquals(1, result.size(), "List size is different than expected");
		assertEquals(1, result.get(0), "wrong value removed !!");
	}
	
	@Test
	public void testRemoveEven1() {
		List<Integer> testList = new ArrayList<Integer>();
		for (int i = 0; i < 100000; i++) {
			testList.add(i*4-1);
		}
		
		List<Integer> result = Loop.removeEven(testList);
		assertEquals(100000, result.size(), "List size is different than expected");
		assertEquals(-1, result.get(0), "wrong value removed !!");
		assertEquals((100000-1)*4-1, result.get(result.size()-1), "wrong value removed !!");
	}
	
	@Test
	public void test2() {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(0);
		lst.add(3);
		lst.add(1);
		lst.add(11);
		lst.add(15);
		lst.add(9);
		lst.add(2);
		lst.add(16);
		lst.add(18);
		lst.add(14);
		lst.add(20);
		lst.add(10);
		lst.add(7);
		lst.add(8);
		lst.add(12);
		lst.add(5);
		lst.add(19);
		lst.add(17);
		lst.add(6);
		lst.add(13);
		lst.add(4);

		List<Integer> result = Loop.removeEven(lst);
		// 3 1 11 15 9 7 5 19 17 13
		assertEquals(3, result.get(0), "wrong value removed !!");
		assertEquals(1, result.get(1), "wrong value removed !!");
		assertEquals(11, result.get(2), "wrong value removed !!");
		assertEquals(15, result.get(3), "wrong value removed !!");
		assertEquals(9, result.get(4), "wrong value removed !!");
		assertEquals(7, result.get(5), "wrong value removed !!");
		assertEquals(5, result.get(6), "wrong value removed !!");
		assertEquals(19, result.get(7), "wrong value removed !!");
		assertEquals(17, result.get(8), "wrong value removed !!");
		assertEquals(13, result.get(9), "wrong value removed !!");


	}
	
	@Test
	public void testStr() {

		String str = "teststring";
		String testString = str + "";
		if (testString == str) {
			System.out.println("same...");
		} else {
			System.out.println("not same");
		}
		if (testString.equals(str)) {
			System.out.println("equal...");
		} else {
			System.out.println("not equal");
		}
		assertEquals(testString, str, "string deðerleri ayný deðil !");
	}
}
