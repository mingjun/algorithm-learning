package name.xmj.wild;

import java.util.Stack;
/**
 * Iterate and print all element in a binary Tree
 * middle order iterate:
 * (print left subTree); print root; (print right subTree)
 * 
 * @author mingjun
 *
 */
public class IterateTree {
	/**
	 * recursively
	 * @param t
	 */
	static void printAsList(Tree t) {
		printSubTree(t.root);
		System.out.println();
	}
	static void printSubTree(Node root) {
		if(null != root) {
			printSubTree(root.left);
			System.out.print(root.data + " ");
			printSubTree(root.right);
		}
	}

	/**
	 * no recursive but a stack
	 * @param t
	 */
	static void printAsList2(Tree t) {
		printSubTree2(t.root);
		System.out.println();
	}
	static void printSubTree2(Node root) {
		if(null == root) {
			return;
		}
		Stack<Node> path = new Stack<Node>();
		push2LeftLeaf(root, path);
		while(!path.isEmpty()) {
			Node p = path.pop();
			if(!p.hasPrinted) {
				System.out.print(p.data + " ");
				p.hasPrinted = true;
				if(null != p.right) {
					path.push(p);
					push2LeftLeaf(p.right, path);
				}
			}
			// has printed, pop the parent
		}
	}

	static Stack<Node> push2LeftLeaf(Node root, Stack<Node> path) {
		Node n = root;
		while(null != n) {
			path.push(n);
			n = n.left;
		}
		return path;
	}
	
	static class Node {
		int data;
		Node left, right;
		boolean hasPrinted = false; //data structure augment
		public Node(int d){data = d;}
	}

	static class Tree {
		Node root;
		public Tree(Node n){ root = n;}
	}
}
