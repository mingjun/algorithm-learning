package name.xmj.noi;

import org.junit.Test;

public class TestAdder {
	@Test
	public void testToString() {
		Adder adder = new Adder("123", "456");
		System.out.println(adder);
	}

}
