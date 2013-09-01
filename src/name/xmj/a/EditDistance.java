package name.xmj.a;

import java.util.Arrays;

public class EditDistance {

	public static int distance(String a, String b) {
		int [][] memo = buildMemo(a.length(), b.length());
		int sizeA = a.length(), sizeB = b.length();
		
		char aa, bb;
		memo[0][0] = 0;  // d("", "") == 0
		for(int i=1; i <= sizeA;i++) {
			memo[i][0] = i; // d("xx..x", "") = length
		}
		for(int j=1;j <= sizeB; j++) {
			memo[0][j] = j; // d("", "xx..x") = length
		}
		
		for(int i=1; i <= sizeA; i++) {
			for(int j=1; j <= sizeB; j++) {
				aa = a.charAt(i-1);
				bb = b.charAt(j-1);
				int v1 = memo[i-1][j] + 1; // delete a[i] or insert b[j^j+1] = aa
				int v2 = memo[i][j-1] + 1; // delete b[j] or insert a[i^i+1] = bb
				int v3 = memo[i-1][j-1] + (aa==bb ? 0 : 1); // modify a[i] or b[j]
				memo[i][j] = min(v1, v2, v3);
			}
		}
//		dump(memo);
		return memo[sizeA][sizeB];
	}
	static int [][] buildMemo(int sizeA, int sizeB) {
		int [][]  memo = new int[sizeA+1][];
		for(int i=0;i <= sizeA; i++) {
			memo[i] = new int[sizeB+1];
		}
		return memo;
	}
	static int min(int x, int ... ys) {
		int m = x;
		for(int y: ys) {
			if(y < m) m = y;
		}
		return m;
	}
	
	static void dump(int [][] memo) {
		for(int [] m: memo) {
			System.out.println(Arrays.toString(m));
		}
	}
	
}
