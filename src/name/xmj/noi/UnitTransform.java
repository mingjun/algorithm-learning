package name.xmj.noi;
/**
 * http://judge.noi.cn/problem?id=1094
 *
 * @author mingjun
 *
 */
public class UnitTransform {
	int number;
	UnitTransform(int n) {
		this.number = n;
	}
	public String toUnit(int unit) {
		StringBuffer sb = new StringBuffer();
		int a = number;
		final int b = unit;
		for(;;) {
			DivResult r = DivResult.div(a, b);
			sb.insert(0, getDigit(r.remainder));
			a = r.quotient;
			if(a == 0) {
				break;
			}
		}
		return sb.toString();
	}

	// n always >= 0
	public char getDigit(int n) {
		if(n <= 9) {
			return (char) ('0' + n);
		} else {
			return (char) ('A' + (n-10));
		}
	}

}
class DivResult {
	int quotient, remainder;
	// remainder would always be non-neg
	public static DivResult div(int dividend, int divisor) {
		int q = dividend / divisor;
		int r = dividend % divisor;
		//let a = dividend, b = divisor
		// a = q*b + r
		if(r < 0) {
			int mode = Math.abs(divisor);
			// a = q*b + r  => a = (q*b-mode)  + (r+mode)
			// => a = (q-b/mode)*b  + (r+mode)
			r += mode;
			q -= divisor/mode;
		}
		DivResult result = new DivResult();
		result.quotient = q;
		result.remainder = r;
//		System.out.println(dividend+"/"+divisor + " = " + q +"..."+r);
		return result;
	}
}