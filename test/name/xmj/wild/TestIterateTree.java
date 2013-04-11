package name.xmj.wild;

import java.util.Random;

import name.xmj.wild.IterateTree.Tree;
import name.xmj.wild.IterateTree.Node;

import org.junit.Test;

public class TestIterateTree {
	static Tree buildTree(int a0, int ... array) {
		Random r = new Random();
		Node n = new Node(a0);
		for(int x: array) {
			Node p = n;
			Node next = null;
			while(true) {
				boolean toLeft = r.nextBoolean();
				next = toLeft ? p.left :  p.right;
				if(next == null) {
					next = new Node(x);
					if(toLeft) {
						p.left = next;
					} else {
						p.right = next;
					}
					break;
				}
				p = next;
			}
		}
		return new Tree(n);
	}
	
	@Test
	public void run(){
		Tree t = buildTree(1,2,3,4,5,6,7,8);
		IterateTree.printAsList(t);

		IterateTree.printAsList2(t);
	}
}