package name.xmj.a;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestEditDistance {

	@Test
	public void testSimple() {
		String [][] pairs = {
				{"", ""}, 
				{"","a"},
				{"a", "a"},
				{"a", "abc"},
				{"b", "abc"},
				{"a", "b"},
				{"abc", "adc"},
				{"abcd", "abd"},
				{"abc", "acb"},
				{"abcd", "acdz"},
			};
		for(String [] pair: pairs) {
			int v = EditDistance.distance(pair[0], pair[1]);
			System.out.printf("|%s, %s| = %d \n", pair[0], pair[1], v);
		}
	}
	
	void runTest(String a, String b, int expectValue) {
		int v = EditDistance.distance(a, b);
		System.out.printf("|%s, %s| = %d \n", a, b, v);
		assertEquals(expectValue, v);
	}
	@Test
	public void testAll() {
		// normal case
		runTest("a", "a", 0);
		runTest("a", "b", 1);
		runTest("abc", "b", 2);
		runTest("a", "abc", 2);
		
		// edge case
		runTest("", "", 0);
		runTest("", "a", 1);
		runTest("a", "", 1);
		runTest("", "", 0);
		
		// cover rage: delete
		String str = "abcdefg";
		int len = str.length();
		for(int i=0;i<len;i++) {
			String tail = str.substring(i);
			runTest(str, tail, i);
			runTest(tail, str, i);
			String head = str.substring(0, i);
			runTest(head, str, len - i);
		}
		
		// cover rage: modify
		StringBuilder sb = new StringBuilder(str);
		for(int i=0;i<len;i++) {
			char c = sb.charAt(i);
			sb.setCharAt(i, (char)(c+10));
			runTest(str, sb.toString(), i+1);
			
		}
	}
	
}
