package name.xmj.noi;

import org.junit.Test;

public class TestMultiplier {

	@Test
	public void testToString() {
		System.out.println(new Multiplier("11", "11"));
		System.out.println(new Multiplier("2", "99"));
		System.out.println(new Multiplier("99", "2"));
		System.out.println(new Multiplier("99", "9"));
		System.out.println(new Multiplier("99", "99"));
	}

	@Test
	public void testPerformance () {
		String x = "9999999999999999999999999999999999999999999999999";
		String y = "9999999999999999999999999999999999999999999999999";
		System.out.println(new Multiplier(x,y));
	}
}
