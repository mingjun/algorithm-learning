package name.xmj.g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberHamilton {
	static final int size = 32;
	Node [] nodes;
	public NumberHamilton() {
		
	}
	public void init() {
		nodes = new Node[size];
		for(int i=0;i<size;i++){ 
			nodes[i] = new Node(i);
		}
	}
	public void buildRelation() {
		for(int i=0;i<size;i++){
			Node n = nodes[i];
			int v = n.value;
			int next1 = getNext1(v);
			if(next1 < size) {
				n.next1 = nodes[next1];
				
				int next2 = next1 | 0x0001;
				if(next2 < size) {
					n.next2 = nodes[next2];
				}
			}
		}
	}
	
	public void findPath() {
		try {
			for(int i=0;i<size;i++){
				Node n = nodes[i];
				int length = findLongestPathLength(n, new Path());
				System.out.println(i+ " longest path " +length);
			}
		} catch (ResultFound e) {
			dump(e.seq);
		}
	}
	
	String dump(int [] seq) {
		System.out.println(Arrays.toString(seq));
		StringBuilder sb = new StringBuilder();
		int first = seq[0];
		int mask = 0x0010;
		for(int i=0;i<4;i++) {
			sb.append((first & mask) == 0 ? "0" : "1");
			mask >>= 1;
		}
		for(int i=0;i<size-4;i++) {
			sb.append((seq[i] & mask) == 0 ? "0" : "1");
		}
		String v = sb.toString();
		System.out.println(v + " -> " + Integer.parseInt(v, 2));
		return "";
	}
	public int findLongestPathLength(Node n, Path existPath) throws ResultFound {
		int m1, m2;
		m1 = m2 = existPath.size();
		if(m1 >= size) {
			throw new ResultFound(existPath.seq());
		}
		
		if(n.next1 != null && !existPath.contains(n.next1)) {
			existPath.add(n.next1);
			m1 = findLongestPathLength(n.next1, existPath);
			existPath.remove();
		}
		if(n.next2 != null && !existPath.contains(n.next2)) {
			existPath.add(n.next2);
			m1 = findLongestPathLength(n.next2, existPath);
			existPath.remove();
		}
		return Math.max(m1, m2);
	}
	
	static final int MASK=0x001E; 
	int getNext1(int v) {
		return (v << 1) & MASK;
	}
	
	
	
	static class Node {
		int value;
		Node next1, next2;
		Node(int v) {
			value = v;
		}
	}
	
	static class Path {
		private List<Node> order = new ArrayList<Node>(size);
		private boolean [] bitMap = new boolean[size];
		void add(Node n) {
			order.add(n);
			bitMap[n.value] = true;
		}
		boolean contains(Node n) {
			return bitMap[n.value];
		}
		void remove() {
			int last = order.size() -1;
			Node n = order.remove(last);
			bitMap[n.value] = false;
		}
		int size() {
			return order.size();
		}
		
		int [] seq() {
			int [] r = new int[size];
			int i = 0;
			for(Node n: order) {
				r[i++] = n.value;
			}
			return r;
		}
		
	}
	static class ResultFound extends Exception{
		private static final long serialVersionUID = -4377800315430838133L;
		int [] seq;
		ResultFound(int [] seq) {
			this.seq = seq;
		}
		
	}
}


