package name.xmj.noi;

import org.junit.Test;

public class TestSmallestJump {

	@Test
	public void testRun(){
		int [] stones = {2, 3, 5, 6, 7};
		SmallestJump sj = new SmallestJump(10, 2 ,3, stones);
		sj.run();
	}
}
