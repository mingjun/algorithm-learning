package name.xmj.g;

import org.junit.Test;

public class TestNumberHamilton {

	NumberHamilton target = new NumberHamilton();
//	@Test
	public void testGetNext() {
		int [] tests = { 0, 1, 2, 3, 15, 16, 17};
		for(int v : tests) {
			System.out.println(v + " next is " + target.getNext1(v));
		}

		for(int v=0;v<32;v++) {
			System.out.println(v + " next is " + target.getNext1(v));
		}
	}

	@Test
	public void testfind() {
		target.init();
		target.buildRelation();
		target.findPath();
	}

}
