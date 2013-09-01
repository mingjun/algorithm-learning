package name.xmj.a;

import java.util.Arrays;
/**
 * O(n*n*log(n))
 * 
 * change binarySearch to hashTable -> O(n*n)
 * @author mingjun
 *
 */
public class FindPythaTriple {

	int [] set;
	int [] square;
	public FindPythaTriple(int [] set) {
		this.set = set;
		
	}
	public void run() {
		Arrays.sort(set);
		int size = set.length;
		square = new int[size];
		for(int i=0;i<size;i++) {
			int v = set[i];
			square[i] = v*v;
		}
		
		
		final int last = size - 1;
		final int llast = size - 2;
		for(int i=0;i<llast;i++) {
			for(int j=i+1;j<last;j++) {
				int k = binarySearch(j+1, last, square[i] + square[j]);
				if(k>=0) {
					System.out.println(set[i] +" "+ set[j] + " " +set[k]);
				}
			}
		}
	}
	
	int binarySearch(int start, int end, int sq) {
		
		if(square[start] == sq) {
			return start;
		}
		if(square[end] == sq) {
			return end;
		}
		if(square[start] > sq || square[end] < sq) {
			return -1;
		}
		
		return binarySearchR(start, end, sq);
	}
	
	int binarySearchR(int start, int end, int sq) {
		if(square[start] == sq) {
			return start;
		}
		if(square[end] == sq) {
			return end;
		}
		if(start + 1 >= end) {
			return -1;
		}
		int mid = (start + end)/2;
		int v = square[mid];
		if(sq == v) {
			return mid;
		} else if(sq > v) {
			return binarySearchR(mid+1, end-1, sq);
		} else {
			return binarySearchR(start+1, mid-1, sq);
		}
	}
	
}
