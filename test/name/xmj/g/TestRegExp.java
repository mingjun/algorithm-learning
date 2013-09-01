package name.xmj.g;

import org.junit.Test;

public class TestRegExp {

	@Test
	public void testSimple() {
		RegExp r = new RegExp("a*b");
		r.match("abc");
		
		System.out.println("abc".matches("\\w+"));
	}
}
