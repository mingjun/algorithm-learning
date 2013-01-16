package name.xmj.noi;

import java.util.Arrays;

/**
 * http://judge.noi.cn/problem?id=1000
 * related topic
 *
 * Time complexity:
 *   T(m,n) = O(m*n)
 * @author mingjun
 *
 */
public class Multiplier {

	int [] x;
	int [] y;
	int [] z;
	final static int mode = 10;

	public Multiplier(String a, String b) {
		x = new int[a.length()];
		y = new int[b.length()];
		for(int i=0;i<x.length;i++) {
			x[i] = a.charAt(i) - '0';
		}
		for(int i=0;i<y.length;i++) {
			y[i] = b.charAt(i) - '0';
		}
		z = new int[x.length + y.length];
	}
	public String run() {
		multiply(0, x.length, 0, y.length);
		unify(z); // ? why unify need ? any further problem?
		return format(z);
	}
	// n-bit x *  m-bit y
	// T(m,n) = 2T(m/2 , n) + O(1)   ;  T(1, n) = O(n)
	// => T(m, n) = O(m*n)
	void multiply(int x_begin, int x_end, int y_begin, int y_end) {
		if(y_begin + 1 == y_end) {
			multiply(x_begin, x_end, y_begin);
		}
		else {
			int y_mid = (y_begin + y_end)/2;
			multiply(x_begin, x_end, y_begin, y_mid);
			multiply(x_begin, x_end, y_mid, y_end);
		}
	}
	// n-bit x * 1-bit y
	// time complexity O(n);
	void multiply(int x_begin, int x_end, int y_index) {

		int z_index = x_end + y_index;
		int yy = y[y_index];
		for(int i=x_end-1 ;i >= x_begin; i--) {
			int temp = z[z_index] + (x[i]*yy);
			// % and / can be replaced by -
			z[z_index] = (temp % mode);
			z[z_index - 1] += (temp / mode);
			z_index --;
		}
	}

	void unify(int [] number) {
		int overflow = 0;
		for(int i= number.length -1 ; i>= 0; i--) {
			number[i] += overflow;
			overflow = number[i] / mode;
			number[i] %= mode;
		}
	}

	String format(int [] number) {
		return Arrays.toString(number);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(format(x)).append(" * ").append(format(y));
		sb.append("=").append(run());
		return sb.toString();
	}
}
