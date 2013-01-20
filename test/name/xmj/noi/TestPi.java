package name.xmj.noi;

import org.junit.Test;

public class TestPi {
	@Test
	public void testSimple() {
		double area = CalPi.runSimple(100.0);
		System.out.println(area);
	}

	@Test
	public void testSmallDiv() {
		CalPi c = new CalPi(1.0);
		c.smallDiv(5, 3, c.temp);
	}

}
