package name.xmj.noi;

import org.junit.Test;

public class TestCutWood {

	@Test
	public void testPartition() {
		int k = 10, n = 5;
		CutWood cw = new CutWood(new int[n], k);
		cw.partition(k, n);
	}

	@Test
	public void testCut() {
		final int k = 7;
		int [] L = new int[]{232, 124, 456};
		CutWood cw = new CutWood(L, k);
		cw.run();
	}
}
