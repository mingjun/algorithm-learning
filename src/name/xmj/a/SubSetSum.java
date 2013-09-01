package name.xmj.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * DP alg. 
 * complexity O(delta* n) = O(2^d * n * n) = O(2^d * n^2)
 *   where d is bit-width of a number
 *   	   n is the size of the input set
 * @author mingjun
 *
 */
public class SubSetSum {

	int [] set;
	boolean [][] memo;
	int negativeSum;
	int positiveSum;
	public SubSetSum(int [] set) {
		this.set = set;
		buildMemo();
	}
	
	void buildMemo() {
		int sumPositive  = 0;
		int sumNegative = 0;
		for(int e: set) {
			if(e > 0)  sumPositive += e;
			else sumNegative += e;
		}
		int delta = sumPositive - sumNegative + 1;
		memo = new boolean[set.length][];
		for(int i=0;i<set.length; i++) {
			memo[i] = new boolean[delta];
		}
		negativeSum = sumNegative;
		positiveSum = sumPositive;
		
		setMemo(0, set[0], true);
		for(int i=1;i< set.length; i++) {
			int value = set[i];
			int last = i-1;
			// case only set[i]
			setMemo(i, value, true);
			
			for(int sv = sumNegative; sv <= sumPositive; sv ++) {
				if(getMemo(last, sv)) {
					// case include set[i]
					setMemo(i, sv+value, true);
					// case exclude set[i]
					setMemo(i, sv, true);
				}
			}
		}
	}
	boolean getMemo(int position, int sumValue) {
		try {
		return memo[position][sumValue - negativeSum];
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	void setMemo(int position, int sumValue, boolean v) {
		memo[position][sumValue - negativeSum] = v;
	}
	
	public boolean containsSubSetWithSum(int sum) {
		if(sum > positiveSum || sum < negativeSum) {
			return false;
		} else {
			return getMemo(set.length-1, sum);
		}
	}
	public List<Integer> findSubSet(final int sum) {
		if(!containsSubSetWithSum(sum)) {
			return Collections.emptyList();
		} else {
			List<Integer> sub = new ArrayList<Integer>();
			int pos = set.length-1;
			int sumV = sum;
			while(pos > 0) {
				if(getMemo(pos - 1, sum)) { // case exclude set[pos]
					pos --;
				} else if(getMemo(pos - 1, sumV - set[pos])) {// case include set[pos]
					sub.add(set[pos]);
					sumV -= set[pos];
					pos --;
					
				} else if(set[pos] == sumV) { // case only set[pos]
					sub.add(set[pos]);
					break;
				} else { // default case
					break;
				}
			}
			if(set[0] == sumV) {
				sub.add(set[0]);
			}
			Collections.reverse(sub);
			return sub;
		}
	}
	void dump() {
		System.out.printf("range: %d ~ %d \n", negativeSum, positiveSum);
		for(boolean [] array: memo) {
			System.out.println(Arrays.toString(array));
		}
	}
}
