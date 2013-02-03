package name.xmj.noi;

import java.util.List;

/**
 * http://judge.noi.cn/problem?id=1090
 *
 * @author mingjun
 * f1(x1,x2,..,xn) =? b1
 * f2(x1,x2,..,xn) =? b2
 * ...
 * fm(x1,x2,..,xn) =? bm
 *
 * Max(g(x1,x2,..,xn))
 * Min(g(x1,x2,..,xn))
 *
 *TODO
 */
public class LinearPrograming {

	int variableCount;
	List<Equation> constrants;
	TargetFunction  target;
	public LinearPrograming(int n, List<Equation> constrants, TargetFunction target) {
		variableCount = n;
		this.constrants = constrants;
		this.target = target;
	}

	public void run() {
//		int m = constrants.size();
//		int n = variableCount;
		//for each
		// choose n equations out of m

		// solve it (with matrix transformations)

		// calculate the target


	}

	public double [] solve(List<Equation> eqs) {
		return null;
	}
}
/**
 *  f(x1..xn) =? b
 * k1*x1 + .. + kn xn  >= b
 */
class Equation{
	double b;
	double [] k;
	Comparetor op;
}

class TargetFunction {
	double [] k;
	boolean max;
}
enum Comparetor {
	eq, // =
	lt, // <=
	gt; // >=
}
