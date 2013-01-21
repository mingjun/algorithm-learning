package name.xmj.noi;

import java.util.Arrays;

/**
 * http://judge.noi.cn/problem?id=1001
 *
 * simplified r = 1
 * @author mingjun
 *
 */
public class CalPi {
	/**
	 * pi/2 = 1 + 1/3 + 1/3* 2/5 + 1/3* 2/5 * 3/7 + ...
	 * area = pi * r*r;
	 * area in +-0.001  => pi in +-0.001/r/r;
	 */
	public static double runSimple(double r) {
		double tiny = Math.pow(10, -piLength(r));

		double pi = 2;
		int i=1,j=3;
		double iterator = 2/3.0;
		while(i<1000) {
			pi += iterator;
			i++; j += 2;
			iterator *= i/(double)(j);
			if(iterator < tiny) {
				break;
			}
		}
		System.out.println(pi + "\t" +i);
		return pi*r*r;
	}

	public static int piLength(double r) {
		int r_order = (int)Math.ceil(Math.log(r)/Math.log(10));
		int correctOrder = 6 + r_order + r_order;
		return correctOrder;
	}

	int [] pi, iterator, temp;
	final static int mode = 10; //TODO improve performance
	public CalPi(double r) {
		this(piLength(r) + 1);
	}

	public CalPi(int len) {
		pi = new int[len];
		iterator = new int[len];
		temp = new int[len];
	}

	public void run() {
		final int len = pi.length;
		pi[0]=2;
		int i=1,j=3;
		smallDiv(2,3, iterator);
		while(i<1000000) {
			addIn(pi, iterator, len);
			i++; j+=2;
			iterator = multipleIn(iterator, smallDiv(i, j, temp), len-1);
			if(isTiny(iterator)) break;
			
		}
		System.out.println(i+" pi="+format(pi));
	}

	public int [] smallDiv(int x, int y, int [] temp) {
		int a = x;
		for(int i=0;i<temp.length; i++){
			int quotient = a /y;
			int remain = a % y;
			temp[i] = quotient;
			a = remain * 10;
		}
		return this.temp;
	}

	public int [] addIn(int [] x, int [] y, int len) {
		int overflow = 0;
		for(int i=len-1;i>=0; i--) {
			int sum = x[i] + y[i] + overflow;
			x[i] = sum % mode;
			overflow = sum / mode;
		}
		return x;
	}

	/**
	 * 
	 * assumption: x [0,1,2,3,...] means 0.123...
	 * assumption: y [0,3,4,5,...] means 0.345...
	 * the length of x y is (len+1);
	 * 
	 * @param x
	 * @param y
	 * @param len
	 * @return
	 */
	public int [] multipleIn(int [] x, int [] y, int len) {
		int [] product = new int [len*2];
		
		for(int i=len;i>0;i--) {
			for(int j=len;j>0;j--) {
				int ii = len - i;
				int jj = len - j;
				int kk =  ii + jj;
				int k =  product.length -1 - kk;
				int p = x[i] * y[j] + product[k];
				product[k] = p % mode;
				product[k-1] += p / mode;
			}
		}

		for(int i=1; i<= len;i++) {
			x[i] = product[i-1];
		}
		//round
		if(product[len] >= 5) {
			x[len] ++;
		}
		return x;
	}

	boolean isTiny(int [] x) {
		boolean zero = true;
		for(int i=0;i<x.length;i++) {
			zero = zero && x[i] == 0;
		}
		return zero;
	}

	String format(int [] number) {
		return Arrays.toString(number);
	}
}
