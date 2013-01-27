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
		System.out.println("5/3 = " + c.format(c.temp));
	}
	
	@Test
	public void testAddIn() {
		CalPi c = new CalPi(100.0);
		c.smallDiv(1, 7, c.temp);
		c.smallDiv(2, 3, c.pi);
		int len = c.pi.length;
		System.out.println("temp = " + c.format(c.temp));
		System.out.println("pi =" + c.format(c.pi));
		System.out.println("addin =" + c.format(c.addIn(c.pi, c.temp, len)));
	}
	
	@Test
	public void testMultipleIn() {
		CalPi c = new CalPi(1.0);
		int [] x = new int[] {0,3,3,3};
		int [] y = new int[] {0,3,3,3};
		int len = x.length -1 ;
		System.out.println("x = " + c.format(x));
		System.out.println("y =" + c.format(y));
		System.out.println("MultipleIn =" + c.format(c.multipleIn(x, y, len)));
	}
	
	@Test
	public void testMultipleInReal() {
		CalPi c = new CalPi(100.0);
		c.smallDiv(1, 7, c.temp);
		c.smallDiv(1, 9, c.pi);
		int len = c.pi.length -1 ;
		System.out.println("x = " + c.format(c.pi));
		System.out.println("y =" + c.format(c.temp));
		System.out.println("MultipleIn =" + c.format(c.multipleIn(c.pi, c.temp, len)));
	}
	
	@Test
	public void testPi() {
		CalPi c = new CalPi(10);
		c.run();
	}

}
