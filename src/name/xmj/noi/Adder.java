package name.xmj.noi;

import java.util.Arrays;

/**
 * http://judge.noi.cn/problem?id=1000
 * advanced version
 * 
 * @author mingjun
 *
 */
public class Adder {

	int [] x;
	int [] y;
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Arrays.toString(x)).append(" + ").append(Arrays.toString(y));
		return sb.toString();
	}
}
