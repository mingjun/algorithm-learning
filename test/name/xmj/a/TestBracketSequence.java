package name.xmj.a;

import java.util.List;

import org.junit.Test;

public class TestBracketSequence {

	BracketSequence seq;
	
	@Test
	public void test(){
		seq = new BracketSequence();
		
		run(0,0,0);
		run(1,0,0);
		run(1,1,0);
		run(1,1,1);
		run(2,0,0);
		run(2,2,0);
		run(2,2,2);
		run(3,2,2);
		run(3,3,2);
		run(12,0,0);
	}
	
	void run(int a, int b, int c) {
		List<String>  set = seq.getSequences(a,b,c);
		StringBuilder sb = new StringBuilder();
		
		int max = 6;
		int count = 0;
		for(String s: set) {
			sb.append(" \"").append(s).append("\" ");
			if(count++ > max) {
				sb.append("...").append(set.size());
				break;
			}
		}
		
		
		System.out.printf("%d, %d, %d :>%s<\n", a, b, c, sb);
	}
}
