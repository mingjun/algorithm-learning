package name.xmj.g;

import org.junit.Test;

public class TestSmallestPiece {
	SmallestPiece t;

	@Test
	public void testSimple() {
		t = new SmallestPiece(new char[]{'a', 'b', 'c'});
		t.find("abc");
		t.find("abcaaa");
		t.find("zabcaaa");
		t.find("bzcaaa");
		t.find("zazazabzzazzc");
		t.find("zazazabzzzazzabc");
		t.find("zazazabzzzazzz");
	}

	@Test
	public void testReal() {
		t = new SmallestPiece();
		t.find("abcdefghijklmnopqrstuvwxyz");
		t.find("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		t.find("abcdefghij klmnopqrstuvwxyz");
		t.find("abcdefghij klmnopqrstuvwxy");
		t.find("Welcome to the Java.com Help Center, which describes solutions for issues you might encounter when downloading and installing Java on your system. Each month, consumers request millions of Java downloads from our site.");
	}

}
