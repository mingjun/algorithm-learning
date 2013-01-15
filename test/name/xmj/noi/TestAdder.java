package name.xmj.noi;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

public class TestAdder {
	@Test
	public void testToString() {
		Adder adder = new Adder("1234", "456");
		System.out.println(adder);
	}

	static Random r = new Random();
	static String x = String.valueOf(Math.abs(r.nextLong()));
	static String y = String.valueOf(Math.abs(r.nextLong()));

	static  {
		StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
		for(int i=0;i<1000;i++) {
			sb1.append(x);
			sb2.append(y);
		}
		x = sb1.toString();
		y = sb2.toString();
	}
	@Test
	public void testAddPerform() {
		String z = new Adder(x,y).run();
		System.out.println(z.length());
	}

	@Test
	public void testBigIntegerPerform() {
		BigInteger a =  new BigInteger(x);
		BigInteger b =  new BigInteger(y);
		BigInteger z = a.add(b);
		System.out.println(z.toString().length());
	}


}
