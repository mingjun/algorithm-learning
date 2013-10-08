package name.xmj.a;

import java.util.ArrayList;
import java.util.List;

/**
 * there are 3 types of brackets {} [] and ()
 * give n1, n2, n3 for brackets, list all valid sequence
 * 
 * @author mingjun
 *
 */
public class BracketSequence {
	/**
	 * method 1: lexcial rules (no some fast)
	 * E ::= () | [] | {}
	 * E ::= (E) | [E] | {E}
	 * E ::= E E
	 */
	//DELETED
	
	/**
	 * method 2: simulate write the valid seq
	 * 
	 */
	List<String> out;
	List<String> getSequences(int x, int y, int z) {
		out = new ArrayList<String>();
		buildR(x, 0, y, 0, z, 0, "");
		return out;
	}
	void buildR(int c1, int d1, int c2, int d2, int c3, int d3, String seq) {
		if(c1 + d1 + c2 + d2 + c3 + d3 == 0) {
			out.add(seq);
		}
		
		if(c1 > 0) {
			buildR(c1-1, d1+1, c2, d2, c3, d3, seq+"{");
		}
		if(c2 > 0) {
			buildR(c1, d1, c2-1, d2+1, c3, d3, seq+"[");
		}
		if(c3 > 0) {
			buildR(c1, d1, c2, d2, c3-1, d3+1, seq+"(");
		}
		
		
		char left = getLeft(seq);
		if(d1 > 0 && left == '{') {
			buildR(c1, d1-1, c2, d2, c3, d3, seq+"}");
		}
		if(d2 > 0 && left == '[') {
			buildR(c1, d1, c2, d2-1, c3, d3, seq+"]");
		}
		if(d3 > 0 && left == '(') {
			buildR(c1, d1, c2, d2, c3, d3-1, seq+")");
		}
	}
	char getLeft(String seq) {
		int r1 = 0;
		int r2 = 0;
		int r3 = 0;
		for(int i=seq.length()-1; i>=0; i--) {
			char c = seq.charAt(i);
			switch(c) {
			case '}':
				r1 ++;
				break;
			case ']':
				r2 ++;
				break;
			case ')':
				r3 ++;
				break;
			case '{':
				r1 --;
				break;
			case '[':
				r2 --;
				break;
			case '(':
				r3 --;
			}
			if(r1 < 0) {
				return '{';
			} else if(r2 < 0) {
				return '[';
			} else if (r3 < 0) {
				return '(';
			}
		}
		return ' ';
	}
}
