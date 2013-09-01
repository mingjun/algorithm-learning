package name.xmj.a;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * there are 3 types of brackets {} [] and ()
 * give n1, n2, n3 for brackets, list all valid sequence
 * 
 * @author mingjun
 *
 */
public class BracketSequence {

	// Set of strings
	Set<String> [][][] memo;
	int a, b, c;
	
	static String [] 
			templates_i = {"{%s}", "%s{}", "{}%s"},
			templates_j = {"[%s]", "%s[]", "[]%s"},
			templates_k = {"(%s)", "%s()", "()%s"};
	
	public BracketSequence(int n1, int n2, int n3) {
		a = n1;
		b = n2;
		c = n3;
		buildMemo();
		System.out.println("buildout!");
		Set<String> set;
		memo[0][0][0] = set = Collections.emptySet();
		memo[1][0][0] = set = Collections.singleton("{}");
		memo[0][1][0] = set = Collections.singleton("[]");
		memo[0][0][1] = set = Collections.singleton("()");
		
		for(int i=0;i<a;i++) {
			for(int j=0;j < b; j++) {
				for(int k=0; k< c; k++) {
//					System.out.println(i);
					if(memo[i][j][k] == null) {
						memo[i][j][k] = set = new HashSet<String>();
						if(i>0) mergeSet(set, memo[i-1][j][k], templates_i);
						if(j>0) mergeSet(set, memo[i][j-1][k], templates_j);
						if(k>0) mergeSet(set, memo[i][j][k-1], templates_k);
					}
				}
			}
		}
	}
	
	Set<String> mergeSet(Set<String> target, Set<String> subSet, String[] templates) {
//		System.out.println("size: " + subSet.size());
		for(String sub : subSet) {
			for(String f: templates) {
				target.add(String.format(f, sub));
			}
		}
		return target;
	}
	
	@SuppressWarnings("unchecked")
	void buildMemo() {
		memo = (Set<String> [][][])new Set[a][][];
		for(int i=0;i<a;i++) {
			Set<String> [][] memo_i = memo[i] = (Set<String> [][])new Set [b][];
			for(int j=0;j < b; j++) {
				memo_i[j] =(Set<String> []) new Set[c];

			}
		}
	}
	Set<String> getSequences(int x, int y, int z) {
		return memo[x][y][z];
	}
}
