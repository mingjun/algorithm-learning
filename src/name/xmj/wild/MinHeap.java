package name.xmj.wild;

public class MinHeap {

	final int [] A;
	int last;
	public MinHeap(int capacity) {
		int size = capacity;
		A = new int[size];
		last = -1;
	}

	public int getMin() {
		return A[0];
	}

	public void add(int v) {
		A[++last] = v;
		heapifyUp(last);
	}

	public int removeMin() {
		final int v = A[0];
		A[0] = A[last];
		last--;
		heapifyDown(0);
		return v;
	}

	public void heapify(int index) {
		if(index > 0) {
			int newV = A[index];
			int pi = parentIndex(index);
			if( A[pi] > newV) {
				A[index] = A[pi];
				A[pi] = newV;
				heapifyUp(pi);
			} else {
				heapifyDown(index);
			}
		} else {
			heapifyDown(index);
		}
	}

	void heapifyUp(int index) {
		if(index > 0) {
			int newV = A[index];
			int pi = parentIndex(index);
			if( A[pi] > newV) {
				A[index] = A[pi];
				A[pi] = newV;
				heapifyUp(pi);
			}
		}
	}

	void heapifyDown(int index) {
		if(index < last) {
			final int newV = A[index];
			int lci = leftChildIndex(index);
			int rci = rightChildIndex(index);
			if(lci > last) {
				return;
			}
			if(rci > last) {
				if(A[lci] < newV) {
					A[index] = A[lci];
					A[lci] = newV;
					heapifyDown(lci);
				}
				return;
			}

			if( A[lci] < newV && A[rci] < newV ) {
				if(A[lci] < A[rci]) {
					A[index] = A[lci];
					A[lci] = newV;
					heapifyDown(lci);
				} else {
					A[index] = A[rci];
					A[rci] = newV;
					heapifyDown(rci);
				}
			} else if(A[lci] < newV) {
				A[index] = A[lci];
				A[lci] = newV;
				heapifyDown(lci);
			} else if(A[rci] < newV) {
				A[index] = A[rci];
				A[rci] = newV;
				heapifyDown(rci);
			}
		}
	}
	int parentIndex(int index) {
		return (index-1)>>1;
	}
	int leftChildIndex(int index){
		return (index<<1) + 1;
	}
	int rightChildIndex(int index){
		return (index<<1) + 2;
	}
}
