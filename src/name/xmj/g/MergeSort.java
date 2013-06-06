package name.xmj.g;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSort {

	final int n;
	PriorityQueue<ScannedList<Integer>> q;
	public MergeSort () {
		this(5);
	}
	public MergeSort (int way) {
		n = way;
		q =  new PriorityQueue<ScannedList<Integer>>(n, 
				new Comparator<ScannedList<Integer>>(){
			@Override
			public int compare(ScannedList<Integer> o1, ScannedList<Integer> o2) {
				return o1.head() - o2.head();
			}});
	}
	
	public List<Integer> sort(List<Integer> array) {
		List<List<Integer>> parts = split(array);
		
		for(List<Integer> p: parts) {
			if(p.size() < n) {
				Collections.sort(p);
			} else {
//				System.out.println("before " + p);
				p = sort(p);
//				System.out.println("after " + p);
			}
			q.add(new ScannedList<Integer>(p));
		}
		
		ArrayList<Integer> r = new ArrayList<Integer>(array.size());
		while(!q.isEmpty()){
			ScannedList<Integer> list = q.peek();
			if(!list.complete()) {
//				System.out.println(list.scanNext());
				r.add(list.scanNext());
				q.poll(); // aaa
			}
			if(!list.complete()) {
				q.offer(list); //aaa not good use
			}
		}
		
		return r;
	}
	
	List<List<Integer>> split(List<Integer> array) {
		final int size0 = array.size();
		final int size = size0/n;
		List<List<Integer>> parts = new ArrayList<List<Integer>>(n);
		
		for(int i=0 ; i < n - 1 ; i++) {
			int index = i*size;
			parts.add(copyList(array.subList(index, index+size)));
		}
		int index = n-1;
		parts.add(copyList(array.subList(index, size0)));
		
		return parts;
	}
	
	<T> List<T> copyList(List<T> list) {
		List<T> r = new ArrayList<T>(list.size());
		r.addAll(list);
		return r;
	}
	static class ScannedList<T> {
		List<T> list;
		int index;
		ScannedList(List<T> ls) {
			list = ls;
			index = 0;
		}
		boolean complete() {
			return index >= list.size();
		}
		T scanNext() {
			return list.get(index++);
		}
		T head() {
			return list.get(index);
		}
		
		public String toString(){
			return list + " at " + index;
		}
	}
}
