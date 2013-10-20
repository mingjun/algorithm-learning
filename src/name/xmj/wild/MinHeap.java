package name.xmj.wild;


public class MinHeap {

	final int [] A;
	int last;
	public MinHeap(int[] array, int capacity) {
		int size = Math.max(capacity, array.length);
		A = new int[size];
		System.arraycopy(array, 0, A, 0, array.length);
		last = array.length -1;
		heapify();
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

	public void heapify() {
		int size = last +1;
		for(int i = size/2 - 1; i >= 0; i--) {
			heapifyDown(i);
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
		int indexOfMin;
		int left = leftChildIndex(index);
		int right = rightChildIndex(index);
		if(left <= last && A[left] < A[index]) {
			indexOfMin = left;
		} else {
			indexOfMin = index;
		}
		if(right <= last && A[right] < A[indexOfMin]) {
			indexOfMin = right;
		}
		if(indexOfMin != index) {
			swap(A, indexOfMin, index);
			heapifyDown(indexOfMin);
		}
	}

	static int parentIndex(int index) {
		return (index-1)>>1;
	}
	static int leftChildIndex(int index){
		return (index<<1) + 1;
	}
	static int rightChildIndex(int index){
		return (index<<1) + 2;
	}
	static void swap(int [] array, int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
}
