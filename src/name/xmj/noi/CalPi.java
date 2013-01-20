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
	 * area in ¡À0.001  => pi in ¡À 0.001/r/r;
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
//		double tiny = Math.pow(10, correctOrder);
		return correctOrder;
	}

	int [] pi, temp;
	public CalPi(double r) {
		int len = piLength(r) + 1;
		System.out.println(len);
		pi = new int[len];
		temp = new int[len];
	}

	public void run() {
		pi[0]=2;
//		int i=1,j=3;
		smallDiv(2,3, this.temp);
		//TODO MUL  ADD
	}

	public int [] smallDiv(int x, int y, int [] temp) {
		int a = x;
		for(int i=0;i<temp.length; i++){
			int quotient = a /y;
			int remain = a % y;
			temp[i] = quotient;
			a = remain * 10;
		}
		System.out.println(format(temp));
		return this.temp;
	}



	String format(int [] number) {
		return Arrays.toString(number);
	}
}
