package name.xmj.noi;

import org.junit.Test;

public class TestUnitTransform {
	@Test
	public void testNegUnit() {
		UnitTransform ut = new UnitTransform(3);
		System.out.println(ut.toUnit(-2));
	}

	@Test
	public void testPosUnit() {
		UnitTransform ut = new UnitTransform(3);
		System.out.println(ut.toUnit(2));
	}

	@Test
	public void testAll() {
		log(30000, -2);
		log(-20000, -2);
		log(28800, -16);
		log(-25000, -16);
	}
	void log(int a, int unit) {
		System.out.print(a+"=");
		System.out.print(new UnitTransform(a).toUnit(unit));
		System.out.println("(base"+unit+")");
	}
}
