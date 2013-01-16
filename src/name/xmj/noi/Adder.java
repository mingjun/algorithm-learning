package name.xmj.noi;

import java.util.Arrays;

/**
 * http://judge.noi.cn/problem?id=1000
 * advanced version
 *
 * Time complexity:
 *   T(m,n) = O(max{m,n})
 *
 * @author mingjun
 *
 */
public class Adder {

	int [] x;
	int [] y;
	final int mode = 10;
	public Adder(String a, String b) {
		x = new int[a.length()];
		y = new int[b.length()];
		for(int i=0;i<x.length;i++) {
			x[i] = a.charAt(i) - '0';
		}
		for(int i=0;i<y.length;i++) {
			y[i] = b.charAt(i) - '0';
		}
	}
	int [] add() {
		int maxLength = Math.max(x.length, y.length);
		int [] sum = new int[maxLength + 1];
		int i = x.length-1, j = y.length-1, k = sum.length-1;
		int overflow = 0;
		while(k >= 0) {
			int x_i = i >= 0 ? x[i] : 0;
			int y_j = j >= 0 ? y[j] : 0;
			int sss = x_i + y_j + overflow;
			sum[k] = sss % mode;
			overflow = sss / mode;
			i--;
			j--;
			k--;
		}
		return sum;
	}

	String run() {
		return format(add());
	}

	String format(int [] number) {
		return Arrays.toString(number);
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(format(x)).append(" + ").append(format(y));
		sb.append("=").append(run());
		return sb.toString();
	}
}
