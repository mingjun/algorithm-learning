package name.xmj.noi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://judge.noi.cn/problem?id=1020
 * for {n} piece of wood with different Length [L1, L2, .., Ln]
 * plan to cut into {k} equivalent small pieces, with the same Length LL
 *
 * constraints: L1..Ln and LL are all integers
 *
 * calculate the maximized LL
 *
 * ------------------------------------
 * algorithm:
 * partition the number {k} into {n} non-negative integers [a1, a2, ..., an]
 * (SUM ai == k)
 * a1 * LL <= L1
 * a2 * LL <= L2
 * ...
 * an * LL <= L3
 *
 * for each partition, calculate a max LL
 * the global max LL is the optimal solution
 *
 * ------------------------------------
 * can improve with constraints:
 * a1 : a2 : .. : an is near L1 : L2 : .. : Ln
 * case 1: ai > aj        =>      Li >= Lj
 * case 2: lower bound check
 * ...
 *
 * @author mingjun
 *
 */
public class CutWood {
	int [] L; // {n} ints
	int k;
	int LL;

	int lowerBoundLL;
	int checked;
	public CutWood(int [] L, int k) {
		this.L = L;
		this.k = k;
	}

	public void run() {
		checkBounds();
		LL = 0;
		checked = 0;
		partition(k, L.length);
		System.out.print("with  " + checked + "  checked ");
		System.out.println("get max length is " + LL);
	}
	void checkBounds() {
		Arrays.sort(L);
		int sum = 0;
		for(int Li : L) {
			sum += Li;
		}
		int max = L[L.length-1];
		int upBoundLL = sum / k;
		lowerBoundLL = max / k;
		System.out.println("max length is in " + lowerBoundLL + "~" + upBoundLL);
	}



	void partition(int k, int n) {
		//[a1,a2,..,an]
		List<Integer> a = new ArrayList<Integer>();
		partition(k,n,a);
	}

	void partition(int k, int n, List<Integer> a) {
		if(n == 1) {
			a.add(k);
			int someLL = localLL(a);
			System.out.println(a + "\t" + someLL);
			if(someLL > LL) LL = someLL;
			return;
		}

		//[a1,a2,.., ai, ... ?]
		//  constrains ai >= ai-1 (from case 1)
		//  constrains ai * lowerBoundLL <= Li

		// last is a(i-1)
		int i = a.size();
		int last = i > 0 ? a.get(i-1) : 0;

		// ai <= Li/lowerBoundLL
		int bound = (int)Math.ceil(L[i]/(double)lowerBoundLL);

		for(int ai = last; ai<= Math.min(k, bound);ai++) {

			List<Integer> copy = new ArrayList<Integer>();
			copy.addAll(a);
			copy.add(ai);
			partition(k-ai, n-1, copy);
		}
	}

	int localLL(List<Integer> a) {
		checked ++;
		int i;
		for(i=0;i<L.length && a.get(i)==0;i++);
		int LL = L[i] / a.get(i);
		//[a1..an] * LL <= [L1..Ln]
		for(;i<L.length;i++) {
			try {
				int ll = L[i] / a.get(i);  // ll == |_ Li / ai _|
				// ai * ll <= Li
				if(ll <= LL) LL = ll;
			} catch(ArithmeticException e) {
				//divide by zero
				// ignore the ll
			}
		}
		return LL;
	}
}
