package tumtraining.loop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Loop {

	public static void main(String[] args) {

		/*-
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			i++;
		}
		
		for (int i = 0; i < 10; i = i + 2) {
			System.out.println(i);
		}
		*/

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

		/*-
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i) % 2 == 0) {
				lst.remove(i);
				i--;
			}
		}

		for (int i : lst) {
			System.out.print(" " + i);
		}
		*/

		/*- ConcurrentModificationException
		for (int number : lst) {
			if (number % 3 == 0) {
				lst.remove(Integer.valueOf(number));
			}
		}
		
		*/

		/*-
		List<Integer> newList = new ArrayList<Integer>();
		
		for(int number : lst) {
			if (number % 3 != 0) {
				newList.add(number);
			}
		}
		for (int number : newList) {
			System.out.print(" " + number);
		}
		*/


		/*-
		for (int i = 0; i < lst.size(); i++) {
			System.out.print(" i: " + i + " value: " + lst.get(i) + " size: " + lst.size());
			if (lst.get(i) % 2 == 0) {
				lst.remove(lst.get(i));
				System.out.println(" removed");
			} else {
				System.out.println(" stays");
			}
		}
		*/
		

		/*-
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i) % 2 == 0) {
				lst.remove(i);
				i--;
			}
		}
		
		
		
		
		int i = 0;
		
		while(i < lst.size()) {
			if (lst.get(i) % 2 == 0) {
				lst.remove(i);
			} else {
				i++;
			}
		}

		for (int number : lst) {
			System.out.print(" " + number);
		}
		*/
		
		List<Integer> odds = removeEven(lst);

		for (int number : odds) {
			System.out.print(" " + number);
		}
		

	}
	
	public static List<Integer> removeEven(List<Integer> lst) {

		/*-
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i) % 2 == 0) {
				lst.remove(i);
				i--;
			}
		}
		*/
		
		List<Integer> odds = lst;
		
		int i = 0;
		
		while(i < odds.size()) {
			if (odds.get(i) % 2 == 0) {
				odds.remove(i);
			} else {
				i++;
			}
		}
		return odds;

	}
	
	public int search(List<Integer> lst, int number) {
		int i = 0;
		
		
		while(i < lst.size() && number != lst.get(i)) {
			i++;
		}
		
		for (i = 0; i < lst.size(); i++) {
			if (number == lst.get(i)) {
				return i;
			}
		}
		
		return -1;
	}

}
