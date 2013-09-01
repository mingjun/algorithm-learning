package name.xmj.a;

import org.junit.Test;

public class TestFindPythaTriple {
	
	@Test
	public void test() {
		FindPythaTriple f = new FindPythaTriple(a2b(1, 20));
		f.run();
	}
	int [] a2b(int a, int b) {
		int size = b - a + 1;
		int[] array = new int[size];
		for(int i=0;i<size;i++) {
			array[i] = a+i;
		}
		return array;
	}
}
