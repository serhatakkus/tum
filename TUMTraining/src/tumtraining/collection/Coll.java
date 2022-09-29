package tumtraining.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Coll {

	public static void main(String[] args) {

		Integer[] arr = new Integer[] { 1, 2, 3 };
		List<Integer> lst = new ArrayList<Integer>();
		lst.addAll(Arrays.asList(arr));
		System.out.println(lst.size());
		lst.add(4);
		System.out.println(Arrays.toString(lst.toArray()));
		lst.add(2, 67);
		lst.add(0, 99);
		System.out.println(Arrays.toString(lst.toArray()));
		lst.set(0, 0);
		System.out.println(lst.get(0));
		System.out.println(Arrays.toString(lst.toArray()));
		lst.remove((Object)3);
		System.out.println(Arrays.toString(lst.toArray()));
		
		//
		
		List<Integer> lstUnique = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		
		lstUnique.add(3);
		set.add(3);

		System.out.println(Arrays.toString(lstUnique.toArray()));
		System.out.println(Arrays.toString(set.toArray()));
		System.out.println();
		
		lstUnique.add(4);
		set.add(4);

		System.out.println(Arrays.toString(lstUnique.toArray()));
		System.out.println(Arrays.toString(set.toArray()));
		System.out.println();
		
		lstUnique.add(8);
		set.add(8);

		System.out.println(Arrays.toString(lstUnique.toArray()));
		System.out.println(Arrays.toString(set.toArray()));
		System.out.println();
		
		lstUnique.add(3);
		set.add(3);

		System.out.println(Arrays.toString(lstUnique.toArray()));
		System.out.println(Arrays.toString(set.toArray()));
		System.out.println();
		lstUnique.add(4);
		set.add(4);

		System.out.println(Arrays.toString(lstUnique.toArray()));
		System.out.println(Arrays.toString(set.toArray()));
		System.out.println();
		
	}
	
	public static void lists() {
		LinkedList<Integer> linked = new LinkedList<Integer>();
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		Queue<Integer> q = new Queue<Integer>() {
			
			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Iterator<Integer> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean addAll(Collection<? extends Integer> c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Integer remove() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer poll() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer peek() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean offer(Integer e) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Integer element() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean add(Integer e) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
