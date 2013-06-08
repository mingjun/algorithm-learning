package name.xmj.wild;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMinHeap {
	@Test
	public void add() {
		int cap = 5;
		MinHeap h = new MinHeap(cap);
		for(int i=0;i<cap;i++) {
			h.add(cap-i);
		}
		dumpHeap(h);
		checkHeap(h);
	}
	@Test
	public void change() {
		int cap = 10;
		MinHeap h = new MinHeap(cap);
		for(int i=0;i<cap;i++) {
			h.add(cap-i);
		}
		dumpHeap(h);
		for(int i=0;i<cap;i++) {
			h.removeMin();
		}
		
		checkHeap(h);

	}
	void dumpHeap(MinHeap h) {
		System.out.print('[');
		for(int i=0; i < h.last; i++) {
			System.out.print(h.A[i]);
			System.out.print(',');
		}
		if(0 <= h.last) {
			System.out.print(h.A[h.last]);
		}
		System.out.println(']');
	}

	void checkHeap(MinHeap h) {
		for(int i = h.last; i > 0; i--) {
			int pi = h.parentIndex(i);
			assertTrue(h.A[i] >= h.A[pi]);
		}
	}

}
