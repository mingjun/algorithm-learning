package name.xmj.noi;

import org.junit.Test;

public class TestUnitTransform {
	@Test
	public void testNegUnit() {
		UnitTransform ut = new UnitTransform(3);
		System.out.println(ut.toUnit(-2));
	}

}
