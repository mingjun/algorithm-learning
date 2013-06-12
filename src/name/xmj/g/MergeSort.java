package name.xmj.g;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import name.xmj.util.PerformanceTracker;

public class MergeSort {

	final int n;
	PriorityQueue<ScannedList<Integer>> q;
	public MergeSort () {
		this(500);
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
	PerformanceTracker tracker1 = PerformanceTracker.create("head");
	PerformanceTracker tracker2 = PerformanceTracker.create("tail");
	PerformanceTracker tracker3 = PerformanceTracker.create("repeater");
	public List<Integer> sort(List<Integer> array) {
		if(array.size() < n) {
			Collections.sort(array);
			return array;
		}
		tracker1.start();
		List<List<Integer>> parts = split(array);

		for(List<Integer> p: parts) {
			if(p.size() < n) {
				Collections.sort(p);
			} else {
				p = sort(p);
			}
			q.add(new ScannedList<Integer>(p));
		}
		tracker1.end();
		tracker2.start();
		ArrayList<Integer> r = new ArrayList<Integer>(array.size());
		while(!q.isEmpty()){
//			tracker3.start();
			ScannedList<Integer> list = q.peek();
			if(!list.complete()) {
				r.add(list.scanNext());
				q.poll();
			}
			if(!list.complete()) {
				q.offer(list);
			}
//			tracker3.end();
		}
		tracker2.end();

		return r;
	}

	List<List<Integer>> split(List<Integer> array) {
		final int size0 = array.size();
		final int size = size0/n;
		List<List<Integer>> parts = new ArrayList<List<Integer>>(n);

		for(int i=0 ; i < n-1 ; i++) {
			int index = i*size;
			parts.add(copyList(array.subList(index, index+size)));
		}
		int index = (n-1)*size;
		parts.add(copyList(array.subList(index, size0)));
//		System.out.println(parts);
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
